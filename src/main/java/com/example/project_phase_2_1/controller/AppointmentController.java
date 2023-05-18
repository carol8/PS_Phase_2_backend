package com.example.project_phase_2_1.controller;

import com.example.project_phase_2_1.dto.appointment.AppointmentCreateDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentListDTO;
import com.example.project_phase_2_1.dto.appointment.AppointmentUpdateDTO;
import com.example.project_phase_2_1.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments/locations/{locationUuid}")
    ResponseEntity<AppointmentListDTO> getAppointments(@PathVariable UUID locationUuid,
                                                       @RequestParam Optional<String> date,
                                                       @RequestParam Optional<Integer> pageNumber,
                                                       @RequestParam Optional<Integer> pageSize) {
        Optional<AppointmentListDTO> appointmentsListDTOOptional = appointmentService.getAppointmentList(locationUuid, date, pageNumber, pageSize);
        return appointmentsListDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/appointments/donors/{username}")
    ResponseEntity<AppointmentListDTO> getAppointments(@PathVariable String username) {
        Optional<AppointmentListDTO> appointmentListDTOOptional = appointmentService.getDonorAppointmentList(username);
        return appointmentListDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/appointments")
    ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentCreateDTO dto) {
        Optional<AppointmentDTO> appointmentDTOOptional = appointmentService.createAppointment(dto);
        return appointmentDTOOptional
                .map(appointmentDTO -> ResponseEntity.status(HttpStatus.CREATED).body(appointmentDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PatchMapping("/appointments/{uuid}")
    ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable UUID uuid, @RequestBody AppointmentUpdateDTO dto) {
        Optional<AppointmentDTO> appointmentDTOOptional = appointmentService.updateAppointment(uuid, dto);
        return appointmentDTOOptional
                .map(appointmentDTO -> ResponseEntity.status(HttpStatus.OK).body(appointmentDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/appointments/{uuid}")
    ResponseEntity<AppointmentDTO> deleteAppointment(@PathVariable UUID uuid, @RequestParam(name = "current_date") String currentDate) {
        Optional<AppointmentDTO> appointmentDTOOptional = appointmentService.deleteAppointment(uuid, currentDate);
        return appointmentDTOOptional
                .map(appointmentDTO -> ResponseEntity.status(HttpStatus.OK).body(appointmentDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
