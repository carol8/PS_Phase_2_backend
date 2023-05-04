package com.example.project_phase_2_1.dto.appointment;

import com.example.project_phase_2_1.entity.Donor;
import com.example.project_phase_2_1.entity.Location;

import java.time.LocalDate;
import java.util.UUID;

public class AppointmentDTO {
    public UUID uuid;
    public LocalDate date;
    public boolean isValid, emailNotificationsEnabled, smsNotificationsEnabled;
    public Donor donor;
    public Location location;
}
