package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.donor.DonorCreateDTO;
import com.example.project_phase_2_1.dto.donor.DonorDTO;
import com.example.project_phase_2_1.dto.donor.DonorInfoDTO;
import com.example.project_phase_2_1.dto.donor.DonorUpdateDTO;
import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataDTO;
import com.example.project_phase_2_1.entity.Donor;
import com.example.project_phase_2_1.entity.Location;
import com.example.project_phase_2_1.mapper.DonorMapper;
import com.example.project_phase_2_1.repository.DonorRepository;
import com.example.project_phase_2_1.repository.LocationRepository;
import com.example.project_phase_2_1.service.DonorService;
import com.example.project_phase_2_1.service.ExtendedDonorDataService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {
    private final DonorRepository donorRepository;
    private final LocationRepository locationRepository;
    private final DonorMapper donorMapper;
    private final ExtendedDonorDataService extendedDonorDataService;

    public DonorServiceImpl(DonorRepository donorRepository,
                            LocationRepository locationRepository,
                            DonorMapper donorMapper,
                            ExtendedDonorDataService extendedDonorDataService) {
        this.donorRepository = donorRepository;
        this.locationRepository = locationRepository;
        this.donorMapper = donorMapper;
        this.extendedDonorDataService = extendedDonorDataService;
    }

    @Override
    public Optional<DonorInfoDTO> getDonorInfo(String username) {
        Optional<Donor> donorOptional = donorRepository.findById(username);
        if (donorOptional.isPresent()) {
            List<Location> locationList = locationRepository.findAll();
            Donor donor = donorOptional.get();
            Optional<ExtendedDonorDataDTO> dataDTO = extendedDonorDataService.getExtendedDonorData(donor.cnp);
            return Optional.of(donorMapper.toInfoDTO(donor, locationList, dataDTO));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DonorDTO> createDonor(DonorCreateDTO dto) {
        if (donorRepository.existsById(dto.username)) {
            return Optional.empty();
        } else {
            Donor donor = donorRepository.save(donorMapper.toDonor(dto));
            return Optional.of(donorMapper.toDonorDTO(donor));
        }
    }

    @Override
    public Optional<DonorDTO> updateDonor(DonorUpdateDTO dto) {
        Optional<Donor> donorOptional = donorRepository.findById(dto.username);
        if (donorOptional.isPresent()) {
            Donor donor = donorOptional.get();
            donorMapper.updateDonorFromDTO(dto, donor);
            donorRepository.save(donor);
            return Optional.of(donorMapper.toDonorDTO(donor));
        }
        return Optional.empty();
    }

    @Override
    public Optional<DonorDTO> deleteDonor(String username) {
        Optional<Donor> donorOptional = donorRepository.findById(username);
        if (donorOptional.isPresent()) {
            Donor donor = donorOptional.get();
            donorRepository.delete(donor);
            return Optional.of(donorMapper.toDonorDTO(donor));
        }
        return Optional.empty();
    }
}
