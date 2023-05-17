package com.example.project_phase_2_1.service;

import com.example.project_phase_2_1.dto.appointment.AppointmentCreateDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentListDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentUpdateDTO;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentService {
    Optional<AppointmentListDTO> getAppointmentList(UUID locationUuid, Optional<String> date, Optional<Integer> pageNumber, Optional<Integer> pageSize);
    Optional<AppointmentListDTO> getDonorAppointmentList(String username);
    Optional<AppointmentDTO> createAppointment(AppointmentCreateDTO dto);
    Optional<AppointmentDTO> updateAppointment(UUID uuid, AppointmentUpdateDTO dto);
    Optional<AppointmentDTO> deleteAppointment(UUID uuid);
}
