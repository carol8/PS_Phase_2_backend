package com.example.project_phase_2_1.mapper;

import com.example.project_phase_2_1.dto.doctor.*;
import com.example.project_phase_2_1.entity.Appointment;
import com.example.project_phase_2_1.entity.Doctor;
import com.example.project_phase_2_1.entity.Location;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DoctorMapper {
    public DoctorInfoDTO toInfoDTO(Doctor doctor, List<Appointment> appointmentList) {
        DoctorInfoDTO dto = new DoctorInfoDTO();
        dto.name = doctor.name;
        dto.surname = doctor.surname;
        dto.appointmentList = appointmentList;
        return dto;
    }

    public Doctor toDoctor(DoctorCreateDTO dto, Location location) {
        Doctor doctor = new Doctor();
        doctor.username = dto.username;
        doctor.password = dto.password;
        doctor.name = dto.name;
        doctor.surname = dto.surname;
        doctor.email = dto.email;
        doctor.cnp = dto.cnp;
        doctor.location = location;
        return doctor;
    }

    public DoctorDTO toDoctorDTO(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.username = doctor.username;
        dto.name = doctor.name;
        dto.surname = doctor.surname;
        dto.email = doctor.email;
        dto.cnp = doctor.cnp;
        return dto;
    }

    public DoctorListDTO toDoctorListDTO(List<Doctor> doctorList) {
        DoctorListDTO dto = new DoctorListDTO();
        dto.doctorList = doctorList;
        return dto;
    }

    public void updateDoctorFromDTO(DoctorUpdateDTO dto, Optional<Location> location, Doctor doctor) {
        if (!dto.password.isEmpty()) {
            doctor.password = dto.password;
        }
        if (!dto.name.isEmpty()) {
            doctor.name = dto.name;
        }
        if (!dto.surname.isEmpty()) {
            doctor.surname = dto.surname;
        }
        if (!dto.email.isEmpty()) {
            doctor.email = dto.email;
        }
        if (!dto.cnp.isEmpty()) {
            doctor.cnp = dto.cnp;
        }
        location.ifPresent(value -> doctor.location = value);
    }
}
