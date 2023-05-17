package com.example.project_phase_2_1.controller;

import com.example.project_phase_2_1.dto.location.LocationDTO;
import com.example.project_phase_2_1.dto.location.LocationListDTO;
import com.example.project_phase_2_1.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    ResponseEntity<LocationListDTO> getLocations(){
        return ResponseEntity.ok(locationService.getLocations());
    }

    @GetMapping("/locations/{uuid}")
    ResponseEntity<LocationDTO> getLocation(@PathVariable UUID uuid) {
        Optional<LocationDTO> locationDTOOptional = locationService.getLocation(uuid);
        return locationDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/locations/doctors/{username}")
    ResponseEntity<LocationDTO> getDoctorLocation(@PathVariable String username){
        Optional<LocationDTO> locationDTOOptional = locationService.getDoctorLocation(username);
        return locationDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
