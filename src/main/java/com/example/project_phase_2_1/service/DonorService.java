package com.example.project_phase_2_1.service;

import com.example.project_phase_2_1.dto.donor.DonorCreateDTO;
import com.example.project_phase_2_1.dto.donor.DonorDTO;
import com.example.project_phase_2_1.dto.donor.DonorInfoDTO;
import com.example.project_phase_2_1.dto.donor.DonorUpdateDTO;

import java.util.Optional;

public interface DonorService {
    Optional<DonorInfoDTO> getDonorInfo(String username);

    Optional<DonorDTO> createDonor(DonorCreateDTO dto);

    Optional<DonorDTO> updateDonor(DonorUpdateDTO dto);

    Optional<DonorDTO> deleteDonor(String username);
}