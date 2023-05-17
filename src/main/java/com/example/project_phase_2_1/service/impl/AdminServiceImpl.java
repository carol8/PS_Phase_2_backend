package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.admin.AdminDTO;
import com.example.project_phase_2_1.entity.Admin;
import com.example.project_phase_2_1.mapper.AdminMapper;
import com.example.project_phase_2_1.repository.AdminRepository;
import com.example.project_phase_2_1.repository.DoctorRepository;
import com.example.project_phase_2_1.repository.LocationRepository;
import com.example.project_phase_2_1.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminRepository adminRepository, DoctorRepository doctorRepository, LocationRepository locationRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public Optional<AdminDTO> getAdminInfo(String username) {
        Optional<Admin> admin = adminRepository.findById(username);
        return admin.map(adminMapper::toDTO);
    }
}
