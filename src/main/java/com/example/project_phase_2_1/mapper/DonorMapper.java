package com.example.project_phase_2_1.mapper;

import com.example.project_phase_2_1.dto.donor.DonorCreateDTO;
import com.example.project_phase_2_1.dto.donor.DonorDTO;
import com.example.project_phase_2_1.dto.donor.DonorInfoDTO;
import com.example.project_phase_2_1.dto.donor.DonorUpdateDTO;
import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataDTO;
import com.example.project_phase_2_1.entity.Donor;
import com.example.project_phase_2_1.entity.Location;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DonorMapper {
    public DonorInfoDTO toInfoDTO(Donor donor, List<Location> locationList, Optional<ExtendedDonorDataDTO> donorDataDTOOptional) {
        DonorInfoDTO dto = new DonorInfoDTO();
        dto.username = donor.username;
        dto.name = donor.name;
        dto.surname = donor.surname;
        dto.email = donor.email;
        dto.phone = donor.phone;
        dto.appointmentList = donor.appointmentList;
        dto.locationList = locationList;
        donorDataDTOOptional.ifPresent(extendedDonorDataDTO -> dto.extendedDonorData = extendedDonorDataDTO);
        return dto;
    }

    public Donor toDonor(DonorCreateDTO dto) {
        Donor donor = new Donor();
        donor.username = dto.username;
        donor.password = dto.password;
        donor.name = dto.name;
        donor.surname = dto.surname;
        donor.email = dto.email;
        donor.phone = dto.phone;
        donor.cnp = dto.cnp;
        return donor;
    }

    public DonorDTO toDonorDTO(Donor donor) {
        DonorDTO dto = new DonorDTO();
        dto.username = donor.username;
        dto.name = donor.name;
        dto.surname = donor.surname;
        dto.email = donor.email;
        dto.phone = donor.phone;
        dto.appointmentList = donor.appointmentList;
        return dto;
    }

    public void updateDonorFromDTO(DonorUpdateDTO dto, Donor donor) {
        if (!dto.password.isEmpty()) {
            donor.password = dto.password;
        }
        if (!dto.name.isEmpty()) {
            donor.name = dto.name;
        }
        if (!dto.surname.isEmpty()) {
            donor.surname = dto.surname;
        }
        if (!dto.email.isEmpty()) {
            donor.email = dto.email;
        }
        if (!dto.phone.isEmpty()) {
            donor.phone = dto.phone;
        }
    }
}
