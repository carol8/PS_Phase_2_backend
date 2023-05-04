package com.example.project_phase_2_1.service.impl;

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
import com.example.project_phase_2_1.components.Mail;
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
    public Optional<AppointmentListDTO> getAppointmentList(String locationUuid, String todayDate) {
        LocalDate today = LocalDate.parse(todayDate);
        Optional<Location> locationOptional = locationRepository.findById(UUID.fromString(locationUuid));
        if (locationOptional.isPresent()) {
            List<Appointment> appointmentList = appointmentRepository.findAllByLocationAndDate(locationOptional.get(), today);
            return Optional.of(appointmentMapper.toAppointmentListDTO(appointmentList));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AppointmentListDTO> getAppointmentList(String locationUuid, int pageNumber, int pageSize) {
        Optional<Location> locationOptional = locationRepository.findById(UUID.fromString(locationUuid));
        if (locationOptional.isPresent()) {
            Page<Appointment> appointmentPage = appointmentRepository.findAllByLocation(locationOptional.get(), PageRequest.of(pageNumber, pageSize, Sort.by("date")));
            return Optional.of(appointmentMapper.toAppointmentListDTO(appointmentPage));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AppointmentDTO> createAppointment(AppointmentCreateDTO dto) {
        LocalDate date = LocalDate.parse(dto.date);
        Optional<Donor> donorOptional = donorRepository.findById(dto.donor);
        Optional<Location> locationOptional = locationRepository.findById(UUID.fromString(dto.location));//TODO securizare prin prevenirea crearii de mai multe appointments decat e posibil pentru data respectiva
        if (donorOptional.isPresent() && locationOptional.isPresent()) {
            Appointment appointment = appointmentRepository.save(appointmentMapper.toAppointment(dto, date, donorOptional.get(), locationOptional.get()));
            Donor donor = donorOptional.get();
            if(Boolean.parseBoolean(dto.emailNotificationsEnabled)) {
                asyncTaskExecutor.execute(() -> {
                    Mail mail = mailBuilderRegistry.getById(MessageType.CONFIRMATION).clone()
                            .setRecipient(donor.email)
                            .setDonorName(donor.name)
                            .setAppointmentDate(DateTimeFormatter.ISO_LOCAL_DATE.format(date))
                            .getResult(MessageType.CONFIRMATION);
                    try {
                        senderFactory.createSender(mail).send();
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            if(Boolean.parseBoolean(dto.smsNotificationsEnabled)){
                asyncTaskExecutor.execute(() -> {
                    Sms sms = smsBuilderRegistry.getById(MessageType.CONFIRMATION).clone()
                            .setRecipient(donor.phone)
                            .setDonorName(donor.name)
                            .setAppointmentDate(DateTimeFormatter.ISO_LOCAL_DATE.format(date))
                            .getResult(MessageType.CONFIRMATION);
                    try {
                        senderFactory.createSender(sms).send();
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            return Optional.of(appointmentMapper.toAppointmentDTO(appointment));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AppointmentDTO> updateAppointment(AppointmentUpdateDTO dto) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(UUID.fromString(dto.uuid));
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            Optional<Donor> newDonor = Optional.empty();
            if (dto.donor != null && !dto.donor.isEmpty()) {
                newDonor = donorRepository.findById(dto.donor);
            }
            Optional<Location> newLocation = Optional.empty();
            if (dto.location != null && !dto.location.isEmpty()) { //TODO
                newLocation = locationRepository.findById(UUID.fromString(dto.location));
            }
            appointmentMapper.updateAppointmentFromDTO(dto, newDonor, newLocation, appointment);
            appointmentRepository.save(appointment);
            return Optional.of(appointmentMapper.toAppointmentDTO(appointment));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AppointmentDTO> deleteAppointment(String uuid) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(UUID.fromString(uuid));
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            appointmentRepository.delete(appointment);
            return Optional.of(appointmentMapper.toAppointmentDTO(appointment));
        }
        return Optional.empty();
    }
}
