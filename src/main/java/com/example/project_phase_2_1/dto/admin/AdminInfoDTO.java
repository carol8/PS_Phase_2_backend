package com.example.project_phase_2_1.dto.admin;

import com.example.project_phase_2_1.entity.Doctor;
import com.example.project_phase_2_1.entity.Location;

import java.util.List;

public class AdminInfoDTO {
    public String username, name, surname;
    public List<Doctor> doctorList;
    public List<Location> locationList;
}
