package com.example.project_phase_2_1.controller;

import com.example.project_phase_2_1.dto.doctor.DoctorCreateDTO;
import com.example.project_phase_2_1.dto.doctor.DoctorDTO;
import com.example.project_phase_2_1.dto.doctor.DoctorListDTO;
import com.example.project_phase_2_1.dto.doctor.DoctorUpdateDTO;
import com.example.project_phase_2_1.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    ResponseEntity<DoctorListDTO> getDoctors() {
        DoctorListDTO doctorListDTO = doctorService.getDoctors();
        return ResponseEntity.ok(doctorListDTO);
    }

    @GetMapping("/doctors/{username}")
    ResponseEntity<DoctorDTO> getDoctor(@PathVariable String username) {
        Optional<DoctorDTO> doctorInfoDTOOptional = doctorService.getDoctor(username);
        return doctorInfoDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/doctors")
    ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorCreateDTO dto) {
        Optional<DoctorDTO> doctorInfoDTOOptional = doctorService.createDoctor(dto);
        return doctorInfoDTOOptional
                .map(doctorDTO -> ResponseEntity.status(HttpStatus.CREATED).body(doctorDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PatchMapping("/doctors/{username}")
    ResponseEntity<DoctorDTO> updateDoctor(@PathVariable String username, @RequestBody DoctorUpdateDTO dto) {
        Optional<DoctorDTO> doctorInfoDTOOptional = doctorService.updateDoctor(username, dto);
        return doctorInfoDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/doctors/{username}")
    ResponseEntity<DoctorDTO> deleteDoctor(@PathVariable String username) {
        Optional<DoctorDTO> doctorInfoDTOOptional = doctorService.deleteDoctor(username);
        return doctorInfoDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
