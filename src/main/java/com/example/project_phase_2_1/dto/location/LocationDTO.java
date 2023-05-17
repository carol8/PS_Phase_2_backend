package com.example.project_phase_2_1.dto.location;

import com.example.project_phase_2_1.entity.Appointment;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class LocationDTO {
    public UUID uuid;
    public String name, address;
    public LocalTime openingTime, closingTime;
    public int maximumDailyDonations;
}
