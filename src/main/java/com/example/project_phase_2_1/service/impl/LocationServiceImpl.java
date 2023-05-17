package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.location.LocationDTO;
import com.example.project_phase_2_1.dto.location.LocationListDTO;
import com.example.project_phase_2_1.entity.Doctor;
import com.example.project_phase_2_1.entity.Location;
import com.example.project_phase_2_1.mapper.LocationMapper;
import com.example.project_phase_2_1.repository.DoctorRepository;
import com.example.project_phase_2_1.repository.LocationRepository;
import com.example.project_phase_2_1.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final DoctorRepository doctorRepository;
    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationRepository locationRepository, DoctorRepository doctorRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.doctorRepository = doctorRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public LocationListDTO getLocations() {
        return locationMapper.toLocationListDTO(locationRepository.findAll());
    }

    @Override
    public Optional<LocationDTO> getLocation(UUID uuid) {
        Optional<Location> locationOptional = locationRepository.findById(uuid);
        return locationOptional.map(locationMapper::toDTO);
    }

    @Override
    public Optional<LocationDTO> getDoctorLocation(String username) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(username);
        return doctorOptional.map(doctor -> locationMapper.toDTO(doctor.location));
    }
}
