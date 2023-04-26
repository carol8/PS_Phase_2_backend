package com.example.project_phase_2_1.service;

import com.example.project_phase_2_1.dto.doctor.DoctorCreateDTO;
import com.example.project_phase_2_1.dto.doctor.DoctorDTO;
import com.example.project_phase_2_1.dto.doctor.DoctorUpdateDTO;

import java.util.Optional;

public interface DoctorService {
    Optional<DoctorDTO> getDoctorInfo(String username);

    Optional<DoctorDTO> createDoctor(DoctorCreateDTO dto);

    Optional<DoctorDTO> updateDoctor(DoctorUpdateDTO dto);

    Optional<DoctorDTO> deleteDoctor(String username);
}
