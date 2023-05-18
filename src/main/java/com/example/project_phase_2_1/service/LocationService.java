package com.example.project_phase_2_1.service;

import com.example.project_phase_2_1.dto.location.LocationDTO;
import com.example.project_phase_2_1.dto.location.LocationListDTO;

import java.util.Optional;
import java.util.UUID;

public interface LocationService {
    LocationListDTO getLocations();

    Optional<LocationDTO> getLocation(UUID uuid);

    Optional<LocationDTO> getDoctorLocation(String username);
}
