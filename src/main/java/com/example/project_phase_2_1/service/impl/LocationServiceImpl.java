package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.location.LocationDTO;
import com.example.project_phase_2_1.entity.Location;
import com.example.project_phase_2_1.mapper.LocationMapper;
import com.example.project_phase_2_1.repository.LocationRepository;
import com.example.project_phase_2_1.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public Optional<LocationDTO> getLocation(String uuid) {
        Optional<Location> locationOptional = locationRepository.findById(UUID.fromString(uuid));
        return locationOptional.map(locationMapper::toDTO);
    }
}
