package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.appointment.AppointmentCreateDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentListDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentUpdateDTO;
import com.example.project_phase_2_1.entity.Appointment;
import com.example.project_phase_2_1.entity.Donor;
import com.example.project_phase_2_1.entity.Location;
import com.example.project_phase_2_1.mapper.AppointmentMapper;
import com.example.project_phase_2_1.repository.AppointmentPageableRepository;
import com.example.project_phase_2_1.repository.AppointmentRepository;
import com.example.project_phase_2_1.repository.DonorRepository;
import com.example.project_phase_2_1.repository.LocationRepository;
import com.example.project_phase_2_1.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentPageableRepository appointmentPageableRepository;
    private final DonorRepository donorRepository;
    private final LocationRepository locationRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  AppointmentPageableRepository appointmentPageableRepository,
                                  DonorRepository donorRepository,
                                  LocationRepository locationRepository,
                                  AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentPageableRepository = appointmentPageableRepository;
        this.donorRepository = donorRepository;
        this.locationRepository = locationRepository;
        this.appointmentMapper = appointmentMapper;
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
            Page<Appointment> appointmentPage = appointmentPageableRepository.findAllByLocation(locationOptional.get(), PageRequest.of(pageNumber, pageSize, Sort.by("date")));
            return Optional.of(appointmentMapper.toAppointmentListDTO(appointmentPage));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AppointmentDTO> createAppointment(AppointmentCreateDTO dto) {
        LocalDate date = LocalDate.parse(dto.date);
        Optional<Donor> donor = donorRepository.findById(dto.donor);
        Optional<Location> location = locationRepository.findById(UUID.fromString(dto.location));//TODO
        if (donor.isPresent() && location.isPresent()) {
            Appointment appointment = appointmentRepository.save(appointmentMapper.toAppointment(date, donor.get(), location.get()));
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
