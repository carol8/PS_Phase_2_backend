package com.example.project_phase_2_1.mapper;

import com.example.project_phase_2_1.dto.admin.AdminDTO;
import com.example.project_phase_2_1.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public AdminDTO toDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.username = admin.username;
        adminDTO.name = admin.name;
        adminDTO.surname = admin.surname;
        return adminDTO;
    }
}
