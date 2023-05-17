package com.example.project_phase_2_1.service;

import com.example.project_phase_2_1.dto.admin.AdminDTO;

import java.util.Optional;

public interface AdminService {
    Optional<AdminDTO> getAdminInfo(String username);
}
