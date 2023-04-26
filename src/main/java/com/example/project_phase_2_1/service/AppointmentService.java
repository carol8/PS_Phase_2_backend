package com.example.project_phase_2_1.service;

import com.example.project_phase_2_1.dto.appointment.AppointmentCreateDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentListDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentUpdateDTO;

import java.util.Optional;

public interface AppointmentService {
    Optional<AppointmentListDTO> getAppointmentList(String locationUuid, String todayDate);

    Optional<AppointmentListDTO> getAppointmentList(String locationUuid, int pageNumber, int pageSize);

    Optional<AppointmentDTO> createAppointment(AppointmentCreateDTO dto);

    Optional<AppointmentDTO> updateAppointment(AppointmentUpdateDTO dto);

    Optional<AppointmentDTO> deleteAppointment(String uuid);
}
