package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.admin.AdminInfoDTO;
import com.example.project_phase_2_1.entity.Admin;
import com.example.project_phase_2_1.entity.Doctor;
import com.example.project_phase_2_1.entity.Location;
import com.example.project_phase_2_1.mapper.AdminMapper;
import com.example.project_phase_2_1.repository.AdminRepository;
import com.example.project_phase_2_1.repository.DoctorRepository;
import com.example.project_phase_2_1.repository.LocationRepository;
import com.example.project_phase_2_1.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final LocationRepository locationRepository;
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminRepository adminRepository, DoctorRepository doctorRepository, LocationRepository locationRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
        this.locationRepository = locationRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public Optional<AdminInfoDTO> getAdminInfo(String username) {
        Optional<Admin> admin = adminRepository.findById(username);
        if (admin.isPresent()) { //TODO desparte pe call-uri
            List<Doctor> doctorList = doctorRepository.findAll();
            List<Location> locationList = locationRepository.findAll();
            return Optional.of(adminMapper.toInfoDTO(admin.get(), doctorList, locationList));
        } else {
            return Optional.empty();
        }
    }
}
