package com.example.project_phase_2_1.service;

import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataCreateDTO;
import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataDTO;
import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataUpdateDTO;

import java.util.Optional;

public interface ExtendedDonorDataService {
    Optional<ExtendedDonorDataDTO> getExtendedDonorData(String cnp);

    Optional<ExtendedDonorDataDTO> createExtendedDonorData(ExtendedDonorDataCreateDTO dto);

    Optional<ExtendedDonorDataDTO> updateOrCreateExtendedDonorData(String cnp, ExtendedDonorDataUpdateDTO dto);

    Optional<ExtendedDonorDataDTO> deleteExtendedDonorData(String cnp);
}
