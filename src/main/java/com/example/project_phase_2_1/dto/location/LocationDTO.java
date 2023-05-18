package com.example.project_phase_2_1.dto.location;

import java.time.LocalTime;
import java.util.UUID;

public class LocationDTO {
    public UUID uuid;
    public String name, address;
    public LocalTime openingTime, closingTime;
    public int maximumDailyDonations;
}
