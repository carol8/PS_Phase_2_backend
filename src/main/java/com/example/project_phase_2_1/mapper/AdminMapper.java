package com.example.project_phase_2_1.mapper;

import com.example.project_phase_2_1.dto.admin.AdminInfoDTO;
import com.example.project_phase_2_1.entity.Admin;
import com.example.project_phase_2_1.entity.Doctor;
import com.example.project_phase_2_1.entity.Location;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminMapper {
    public AdminInfoDTO toInfoDTO(Admin admin, List<Doctor> doctorList, List<Location> locationList) {
        AdminInfoDTO adminInfoDTO = new AdminInfoDTO();
        adminInfoDTO.username = admin.username;
        adminInfoDTO.name = admin.name;
        adminInfoDTO.surname = admin.surname;
        adminInfoDTO.doctorList = doctorList;
        adminInfoDTO.locationList = locationList;
        return adminInfoDTO;
    }
}
