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
    ResponseEntity<AppointmentListDTO> getB(@PathVariable UUID locationUuid,
                              @RequestParam Optional<String> date,
                              @RequestParam Optional<Integer> pageNumber,
                              @RequestParam Optional<Integer> pageSize){
        Optional<AppointmentListDTO> appointmentsListDTOOptional = appointmentService.getAppointmentList(locationUuid, date, pageNumber, pageSize);
        return appointmentsListDTOOptional
                .map(appointmentListDTO -> ResponseEntity.status(HttpStatus.CREATED).body(appointmentListDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/appointments")
    ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentCreateDTO dto) {
        Optional<AppointmentDTO> appointmentDTOOptional = appointmentService.createAppointment(dto);
        return appointmentDTOOptional
                .map(appointmentDTO -> ResponseEntity.status(HttpStatus.CREATED).body(appointmentDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PatchMapping("/appointments")
    ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentUpdateDTO dto) {
        Optional<AppointmentDTO> appointmentDTOOptional = appointmentService.updateAppointment(dto);
        return appointmentDTOOptional
                .map(appointmentDTO -> ResponseEntity.status(HttpStatus.OK).body(appointmentDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/appointments")
    ResponseEntity<AppointmentDTO> deleteAppointment(@RequestParam(name = "uuid") String uuid) {
        Optional<AppointmentDTO> appointmentDTOOptional = appointmentService.deleteAppointment(uuid);
        return appointmentDTOOptional
                .map(appointmentDTO -> ResponseEntity.status(HttpStatus.OK).body(appointmentDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
