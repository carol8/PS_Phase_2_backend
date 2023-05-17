package com.example.project_phase_2_1.mapper;

import com.example.project_phase_2_1.dto.location.LocationDTO;
import com.example.project_phase_2_1.dto.location.LocationListDTO;
import com.example.project_phase_2_1.entity.Location;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationMapper {
    public LocationDTO toDTO(Location location) {
        LocationDTO dto = new LocationDTO();
        dto.uuid = location.uuid;
        dto.name = location.name;
        dto.address = location.address;
        dto.openingTime = location.openingTime;
        dto.closingTime = location.closingTime;
        dto.maximumDailyDonations = location.maximumDailyDonations;
        return dto;
    }

    public LocationListDTO toLocationListDTO(List<Location> locationList) {
        LocationListDTO locationListDTO = new LocationListDTO();
        locationListDTO.locationList = locationList;
        return locationListDTO;
    }
}
