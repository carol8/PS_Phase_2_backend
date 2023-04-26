package com.example.project_phase_2_1.service;

import com.example.project_phase_2_1.dto.location.LocationDTO;

import java.util.Optional;

public interface LocationService {
    Optional<LocationDTO> getLocation(String uuid);
}
