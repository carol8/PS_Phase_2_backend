package com.example.project_phase_2_1.controller;

import com.example.project_phase_2_1.dto.location.LocationDTO;
import com.example.project_phase_2_1.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    ResponseEntity<LocationDTO> getLocation(@RequestParam(name = "uuid") String uuid) {
        Optional<LocationDTO> locationDTOOptional = locationService.getLocation(uuid);
        return locationDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
