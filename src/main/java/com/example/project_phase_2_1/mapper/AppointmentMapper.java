package com.example.project_phase_2_1.mapper;

import com.example.project_phase_2_1.dto.appointment.AppointmentCreateDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentListDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentUpdateDTO;
import com.example.project_phase_2_1.entity.Appointment;
import com.example.project_phase_2_1.entity.Donor;
import com.example.project_phase_2_1.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class AppointmentMapper {
    public Appointment toAppointment(AppointmentCreateDTO dto, LocalDate date, Donor donor, Location location) {
        Appointment appointment = new Appointment();
        appointment.date = date;
        appointment.isValid = false;
        appointment.result = "";
        appointment.donor = donor;
        appointment.location = location;
        appointment.emailNotificationsEnabled = Boolean.parseBoolean(dto.emailNotificationsEnabled);
        appointment.smsNotificationsEnabled = Boolean.parseBoolean(dto.smsNotificationsEnabled);
        return appointment;
    }

    public AppointmentDTO toAppointmentDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.uuid = appointment.uuid;
        dto.date = appointment.date;
        dto.isValid = appointment.isValid;
        dto.result = appointment.result;
        dto.donor = appointment.donor;
        dto.location = appointment.location;
        dto.emailNotificationsEnabled = appointment.emailNotificationsEnabled;
        dto.smsNotificationsEnabled = appointment.smsNotificationsEnabled;
        return dto;
    }

    public AppointmentListDTO toAppointmentListDTO(List<Appointment> appointmentList) {
        AppointmentListDTO dto = new AppointmentListDTO();
        dto.appointmentList = appointmentList;
        return dto;
    }

    public AppointmentListDTO toAppointmentListDTO(Page<Appointment> appointmentPage) {
        AppointmentListDTO dto = new AppointmentListDTO();
        dto.rowCount = (int) appointmentPage.getTotalElements();
        dto.appointmentList = appointmentPage.toList();
        return dto;
    }

    public void updateAppointmentFromDTO(AppointmentUpdateDTO dto, Optional<Donor> donorOptional, Optional<Location> locationOptional, Appointment appointment) {
        if (dto.date != null && !dto.date.isEmpty()) {
            appointment.date = LocalDate.parse(dto.date);
        }
        if (dto.isValid != null && !dto.isValid.isEmpty()) {
            appointment.isValid = Boolean.parseBoolean(dto.isValid);
        }
        if (dto.result != null && !dto.result.isEmpty()) {
            appointment.result = dto.result;
        }
        if (dto.smsNotificationsEnabled != null) {
            appointment.emailNotificationsEnabled = Boolean.parseBoolean(dto.emailNotificationsEnabled);
        }
        if (dto.smsNotificationsEnabled != null) {
            appointment.smsNotificationsEnabled = Boolean.parseBoolean(dto.smsNotificationsEnabled);
        }
        donorOptional.ifPresent(donor -> appointment.donor = donor);
        locationOptional.ifPresent(location -> appointment.location = location);
    }
}
