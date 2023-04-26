package com.example.project_phase_2_1.mapper;

import com.example.project_phase_2_1.dto.user.UserAuthenticateDTO;
import com.example.project_phase_2_1.dto.user.UserDTO;
import com.example.project_phase_2_1.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserAuthenticateDTO userAuthenticateDTO) {
        User user = new User();
        user.username = userAuthenticateDTO.username;
        user.password = userAuthenticateDTO.password;
        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.username = user.username;
        return userDTO;
    }
}
