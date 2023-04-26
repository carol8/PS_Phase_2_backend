package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.doctor.DoctorCreateDTO;
import com.example.project_phase_2_1.dto.doctor.DoctorDTO;
import com.example.project_phase_2_1.dto.doctor.DoctorUpdateDTO;
import com.example.project_phase_2_1.entity.Doctor;
import com.example.project_phase_2_1.entity.Location;
import com.example.project_phase_2_1.mapper.DoctorMapper;
import com.example.project_phase_2_1.repository.AppointmentRepository;
import com.example.project_phase_2_1.repository.DoctorRepository;
import com.example.project_phase_2_1.repository.LocationRepository;
import com.example.project_phase_2_1.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final LocationRepository locationRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorMapper doctorMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             LocationRepository locationRepository,
                             AppointmentRepository appointmentRepository,
                             DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.locationRepository = locationRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public Optional<DoctorDTO> getDoctorInfo(String username) {
        Optional<Doctor> doctor = doctorRepository.findById(username);
        return doctor.map(doctorMapper::toDoctorDTO);
    }

    @Override
    public Optional<DoctorDTO> createDoctor(DoctorCreateDTO dto) {
        if (!doctorRepository.existsById(dto.username)) {
            Optional<Location> location = locationRepository.findById(UUID.fromString(dto.locationUuid));
            if (location.isPresent()) {
                Doctor doctor = doctorRepository.save(doctorMapper.toDoctor(dto, location.get()));
                return Optional.of(doctorMapper.toDoctorDTO(doctor));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<DoctorDTO> updateDoctor(DoctorUpdateDTO dto) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(dto.username);
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            Optional<Location> location = Optional.empty();
            if (!dto.locationUuid.isEmpty()) {
                location = locationRepository.findById(UUID.fromString(dto.locationUuid));
            }
            doctorMapper.updateDoctorFromDTO(dto, location, doctor);
            doctorRepository.save(doctor);
            return Optional.of(doctorMapper.toDoctorDTO(doctor));
        }
        return Optional.empty();
    }

    @Override
    public Optional<DoctorDTO> deleteDoctor(String username) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(username);
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            doctorRepository.delete(doctor);
            return Optional.of(doctorMapper.toDoctorDTO(doctor));
        }
        return Optional.empty();
    }
}
