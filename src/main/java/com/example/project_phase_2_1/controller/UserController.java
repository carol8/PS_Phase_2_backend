package com.example.project_phase_2_1.controller;

import com.example.project_phase_2_1.dto.user.UserAuthenticateDTO;
import com.example.project_phase_2_1.dto.user.UserDTO;
import com.example.project_phase_2_1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    ResponseEntity<?> authenticateUser(@RequestBody UserAuthenticateDTO dto) {
        Optional<UserDTO> userDTO = userService.authenticateUser(dto);
        if (userDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserRole(userDTO.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
