package com.example.project_phase_2_1.controller;

import com.example.project_phase_2_1.dto.donor.DonorCreateDTO;
import com.example.project_phase_2_1.dto.donor.DonorDTO;
import com.example.project_phase_2_1.dto.donor.DonorUpdateDTO;
import com.example.project_phase_2_1.service.DonorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class DonorController {
    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @GetMapping("/donors/{username}")
    ResponseEntity<DonorDTO> getDonor(@PathVariable String username) {
        Optional<DonorDTO> donorDTOOptional = donorService.getDonor(username);
        return donorDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/donors")
    ResponseEntity<DonorDTO> createDonor(@RequestBody DonorCreateDTO dto) {
        Optional<DonorDTO> donorDTOOptional = donorService.createDonor(dto);
        return donorDTOOptional
                .map(donorDTO -> ResponseEntity.status(HttpStatus.CREATED).body(donorDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PatchMapping("/donors/{username}")
    ResponseEntity<DonorDTO> updateDonor(@PathVariable String username, @RequestBody DonorUpdateDTO dto) {
        Optional<DonorDTO> donorDTOOptional = donorService.updateDonor(username, dto);
        return donorDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/donors/{username}")
    ResponseEntity<DonorDTO> deleteDonor(@PathVariable String username) {
        Optional<DonorDTO> donorDTOOptional = donorService.deleteDonor(username);
        return donorDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
