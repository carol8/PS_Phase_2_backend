package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.user.UserAuthenticateDTO;
import com.example.project_phase_2_1.dto.user.UserDTO;
import com.example.project_phase_2_1.dto.user.UserRoleDTO;
import com.example.project_phase_2_1.entity.User;
import com.example.project_phase_2_1.enums.UserRole;
import com.example.project_phase_2_1.mapper.UserMapper;
import com.example.project_phase_2_1.repository.AdminRepository;
import com.example.project_phase_2_1.repository.DoctorRepository;
import com.example.project_phase_2_1.repository.DonorRepository;
import com.example.project_phase_2_1.repository.UserRepository;
import com.example.project_phase_2_1.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final DonorRepository donorRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           AdminRepository adminRepository,
                           DoctorRepository doctorRepository,
                           DonorRepository donorRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
        this.donorRepository = donorRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDTO> authenticateUser(UserAuthenticateDTO dto) {
        User user = userMapper.toUser(dto);
        Optional<User> foundUser = userRepository.findById(user.username);
        if (foundUser.isPresent() && foundUser.get().password.equals(dto.password)) {
            return foundUser.map(userMapper::toDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public UserRoleDTO getUserRole(UserDTO dto) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        if (adminRepository.existsById(dto.username)) {
            userRoleDTO.userRole = UserRole.ADMIN;
        } else if (doctorRepository.existsById(dto.username)) {
            userRoleDTO.userRole = UserRole.DOCTOR;
        } else if (donorRepository.existsById(dto.username)) {
            userRoleDTO.userRole = UserRole.DONOR;
        } else {
            userRoleDTO.userRole = UserRole.NONE;
        }
        return userRoleDTO;
    }
}
