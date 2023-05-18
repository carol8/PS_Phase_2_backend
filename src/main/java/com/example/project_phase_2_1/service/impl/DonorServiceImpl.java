package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.donor.DonorCreateDTO;
import com.example.project_phase_2_1.dto.donor.DonorDTO;
import com.example.project_phase_2_1.dto.donor.DonorUpdateDTO;
import com.example.project_phase_2_1.entity.Donor;
import com.example.project_phase_2_1.mapper.DonorMapper;
import com.example.project_phase_2_1.repository.DonorRepository;
import com.example.project_phase_2_1.service.DonorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {
    private final DonorRepository donorRepository;
    private final DonorMapper donorMapper;

    public DonorServiceImpl(DonorRepository donorRepository, DonorMapper donorMapper) {
        this.donorRepository = donorRepository;
        this.donorMapper = donorMapper;
    }

    @Override
    public Optional<DonorDTO> getDonor(String username) {
        Optional<Donor> donorOptional = donorRepository.findById(username);
        return donorOptional.map(donorMapper::toDTO);
    }

    @Override
    public Optional<DonorDTO> createDonor(DonorCreateDTO dto) {
        if (donorRepository.existsById(dto.username)) {
            return Optional.empty();
        } else {
            Donor donor = donorRepository.save(donorMapper.toDonor(dto));
            return Optional.of(donorMapper.toDTO(donor));
        }
    }

    @Override
    public Optional<DonorDTO> updateDonor(String username, DonorUpdateDTO dto) {
        Optional<Donor> donorOptional = donorRepository.findById(username);
        if (donorOptional.isPresent()) {
            Donor donor = donorOptional.get();
            donorMapper.updateDonorFromDTO(dto, donor);
            donorRepository.save(donor);
            return Optional.of(donorMapper.toDTO(donor));
        }
        return Optional.empty();
    }

    @Override
    public Optional<DonorDTO> deleteDonor(String username) {
        Optional<Donor> donorOptional = donorRepository.findById(username);
        if (donorOptional.isPresent()) {
            Donor donor = donorOptional.get();
            donorRepository.delete(donor);
            return Optional.of(donorMapper.toDTO(donor));
        }
        return Optional.empty();
    }
}
