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

@CrossOrigin
@RestController
// TODO
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // appointments/location/{locationId}?date=
    @GetMapping("/appointments/today")
    ResponseEntity<AppointmentListDTO> getTodayAppointments(@RequestParam(name = "locationUuid") String locationUuid, @RequestParam(name = "todayDate") String todayDate) {
        Optional<AppointmentListDTO> appointmentsListDTOOptional = appointmentService.getAppointmentList(locationUuid, todayDate);
        return appointmentsListDTOOptional
                .map(appointmentListDTO -> ResponseEntity.status(HttpStatus.CREATED).body(appointmentListDTO)) //TODO
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/appointments/all")
    ResponseEntity<AppointmentListDTO> getTodayAppointments(@RequestParam(name = "locationUuid") String locationUuid,
                                                            @RequestParam(name = "pageNumber") int pageNumber,
                                                            @RequestParam(name = "pageSize") int pageSize) {
        Optional<AppointmentListDTO> appointmentsListDTOOptional = appointmentService.getAppointmentList(locationUuid, pageNumber, pageSize);
        return appointmentsListDTOOptional
                .map(appointmentListDTO -> ResponseEntity.status(HttpStatus.CREATED).body(appointmentListDTO)) //TODO
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
