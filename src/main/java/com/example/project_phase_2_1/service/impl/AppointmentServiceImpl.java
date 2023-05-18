package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.components.Mail;
import com.example.project_phase_2_1.components.MailBuilderRegistry;
import com.example.project_phase_2_1.components.Sms;
import com.example.project_phase_2_1.components.SmsBuilderRegistry;
import com.example.project_phase_2_1.dto.appointment.AppointmentCreateDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentListDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentUpdateDTO;
import com.example.project_phase_2_1.entity.Appointment;
import com.example.project_phase_2_1.entity.Donor;
import com.example.project_phase_2_1.entity.Location;
import com.example.project_phase_2_1.enums.MessageType;
import com.example.project_phase_2_1.mapper.AppointmentMapper;
import com.example.project_phase_2_1.repository.AppointmentRepository;
import com.example.project_phase_2_1.repository.DonorRepository;
import com.example.project_phase_2_1.repository.LocationRepository;
import com.example.project_phase_2_1.service.AppointmentService;
import com.example.project_phase_2_1.service.MessageSender;
import jakarta.mail.MessagingException;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DonorRepository donorRepository;
    private final LocationRepository locationRepository;
    private final AppointmentMapper appointmentMapper;
    private final MailBuilderRegistry mailBuilderRegistry;
    private final SmsBuilderRegistry smsBuilderRegistry;
    private final SenderFactory senderFactory;
    private final TaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  DonorRepository donorRepository,
                                  LocationRepository locationRepository,
                                  AppointmentMapper appointmentMapper,
                                  MailBuilderRegistry mailBuilderRegistry,
                                  SmsBuilderRegistry smsBuilderRegistry,
                                  SenderFactory senderFactory) {
        this.appointmentRepository = appointmentRepository;
        this.donorRepository = donorRepository;
        this.locationRepository = locationRepository;
        this.appointmentMapper = appointmentMapper;
        this.mailBuilderRegistry = mailBuilderRegistry;
        this.smsBuilderRegistry = smsBuilderRegistry;
        this.senderFactory = senderFactory;
    }

    @Override
    public Optional<AppointmentListDTO> getAppointmentList(UUID locationUuid, Optional<String> todayDate, Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        LocalDate today = null;
        int pNum = 0, pSize = Integer.MAX_VALUE;
        if (todayDate.isPresent()) {
            today = LocalDate.parse(todayDate.get());
        }

        if (pageNumber.isPresent()) {
            pNum = pageNumber.get();
        }

        if (pageSize.isPresent()) {
            pSize = pageSize.get();
        }

        Optional<Location> locationOptional = locationRepository.findById(locationUuid);
        if (locationOptional.isPresent()) {
            Page<Appointment> appointmentPage;
            if (todayDate.isPresent()) {
                appointmentPage = appointmentRepository.findAllByLocationAndDate(locationOptional.get(), today, PageRequest.of(pNum, pSize, Sort.by("date")));
            } else {
                appointmentPage = appointmentRepository.findAllByLocation(locationOptional.get(), PageRequest.of(pNum, pSize, Sort.by("date")));
            }
            return Optional.of(appointmentMapper.toAppointmentListDTO(appointmentPage));
        }

        return Optional.empty();
    }

    @Override
    public Optional<AppointmentListDTO> getDonorAppointmentList(String username) {
        Optional<Donor> donorOptional = donorRepository.findById(username);
        return donorOptional.map(donor -> appointmentMapper.toAppointmentListDTO(donor.appointmentList));
    }

    @Override
    public Optional<AppointmentDTO> createAppointment(AppointmentCreateDTO dto) {
        MessageSender mailSender = senderFactory.createMailSender();
        MessageSender smsSender = senderFactory.createSmsSender();

        LocalDate date = LocalDate.parse(dto.date);
        Optional<Donor> donorOptional = donorRepository.findById(dto.donor);
        Optional<Location> locationOptional = locationRepository.findById(UUID.fromString(dto.location));
        if (donorOptional.isPresent() && locationOptional.isPresent()) {
            Location location = locationOptional.get();
            List<Appointment> appointmentList = appointmentRepository.findAllByLocation(location);
            List<Appointment> appointmentsAtDate = appointmentList.stream()
                    .filter(appointment -> appointment.date.equals(date))
                    .toList();
            if (appointmentsAtDate.size() < location.maximumDailyDonations) {
                Appointment appointment = appointmentRepository.save(appointmentMapper.toAppointment(dto, date, donorOptional.get(), locationOptional.get()));
                Donor donor = donorOptional.get();
                if (Boolean.parseBoolean(dto.emailNotificationsEnabled)) {
                    asyncTaskExecutor.execute(() -> {
                        Mail mail = mailBuilderRegistry.getById(MessageType.CONFIRMATION).clone()
                                .setRecipient(donor.email)
                                .setDonorName(donor.name)
                                .setAppointmentDate(DateTimeFormatter.ISO_LOCAL_DATE.format(date))
                                .getResult(MessageType.CONFIRMATION);
                        try {
                            mailSender.send(mail);
                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                if (Boolean.parseBoolean(dto.smsNotificationsEnabled)) {
                    asyncTaskExecutor.execute(() -> {
                        Sms sms = smsBuilderRegistry.getById(MessageType.CONFIRMATION).clone()
                                .setRecipient(donor.phone)
                                .setDonorName(donor.name)
                                .setAppointmentDate(DateTimeFormatter.ISO_LOCAL_DATE.format(date))
                                .getResult(MessageType.CONFIRMATION);
                        try {
                            smsSender.send(sms);
                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                return Optional.of(appointmentMapper.toAppointmentDTO(appointment));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<AppointmentDTO> updateAppointment(UUID uuid, AppointmentUpdateDTO dto) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(uuid);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            Optional<Donor> newDonor = Optional.empty();
            if (dto.donor != null && !dto.donor.isEmpty()) {
                newDonor = donorRepository.findById(dto.donor);
            }
            Optional<Location> newLocation = Optional.empty();
            if (dto.location != null && !dto.location.isEmpty()) {
                newLocation = locationRepository.findById(UUID.fromString(dto.location));
            }
            appointmentMapper.updateAppointmentFromDTO(dto, newDonor, newLocation, appointment);
            appointmentRepository.save(appointment);
            return Optional.of(appointmentMapper.toAppointmentDTO(appointment));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AppointmentDTO> deleteAppointment(UUID uuid, String currentDate) {
        LocalDate date = LocalDate.parse(currentDate);
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(uuid);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            if(appointment.date.isEqual(date) || appointment.date.isAfter(date)) {
                appointmentRepository.delete(appointment);
                return Optional.of(appointmentMapper.toAppointmentDTO(appointment));
            }
        }
        return Optional.empty();
    }
}

