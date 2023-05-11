package com.example.project_phase_2_1.dto.donor;

import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataDTO;
import com.example.project_phase_2_1.entity.Appointment;
import com.example.project_phase_2_1.entity.Location;

import java.util.List;

public class DonorInfoDTO {
    public String username, name, surname, email, phone;
    public ExtendedDonorDataDTO extendedDonorData;
    public List<Location> locationList;
    public List<Appointment> appointmentList;
}
