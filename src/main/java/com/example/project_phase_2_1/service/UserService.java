package com.example.project_phase_2_1.service;

import com.example.project_phase_2_1.dto.user.UserAuthenticateDTO;
import com.example.project_phase_2_1.dto.user.UserDTO;
import com.example.project_phase_2_1.dto.user.UserRoleDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> authenticateUser(String username, UserAuthenticateDTO dto);

    UserRoleDTO getUserRole(UserDTO dto);
}
