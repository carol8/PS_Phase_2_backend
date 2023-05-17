package com.example.project_phase_2_1.controller;

import com.example.project_phase_2_1.dto.user.UserAuthenticateDTO;
import com.example.project_phase_2_1.dto.user.UserDTO;
import com.example.project_phase_2_1.dto.user.UserRoleDTO;
import com.example.project_phase_2_1.entity.User;
import com.example.project_phase_2_1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/{username}")
    ResponseEntity<UserRoleDTO> authenticateUser(@PathVariable String username, @RequestBody UserAuthenticateDTO dto) {
        Optional<UserDTO> userDTO = userService.authenticateUser(username, dto);
        return userDTO
                .map(value -> ResponseEntity.status(HttpStatus.OK).body(userService.getUserRole(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
