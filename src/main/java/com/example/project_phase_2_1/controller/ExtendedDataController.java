package com.example.project_phase_2_1.controller;

import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataDTO;
import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataUpdateDTO;
import com.example.project_phase_2_1.service.ExtendedDonorDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class ExtendedDataController {
    private final ExtendedDonorDataService extendedDonorDataService;

    public ExtendedDataController(ExtendedDonorDataService extendedDonorDataService){
        this.extendedDonorDataService = extendedDonorDataService;
    }
    @GetMapping("/donors/extendedData")
    ResponseEntity<ExtendedDonorDataDTO> getExtendedDonorInfo(@RequestParam(name = "cnp") String cnp){
        Optional<ExtendedDonorDataDTO> extendedDonorDataDTOOptional = extendedDonorDataService.getExtendedDonorData(cnp);
        return extendedDonorDataDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/donors/extendedData")
    ResponseEntity<ExtendedDonorDataDTO> putExtendedDonorInfo(@RequestBody ExtendedDonorDataUpdateDTO dto){
        Optional<ExtendedDonorDataDTO> extendedDonorDataDTOOptional = extendedDonorDataService.updateExtendedDonorData(dto);
        return extendedDonorDataDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
