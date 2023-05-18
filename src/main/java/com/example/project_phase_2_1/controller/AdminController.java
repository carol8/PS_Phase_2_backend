package com.example.project_phase_2_1.controller;

import com.example.project_phase_2_1.dto.admin.AdminDTO;
import com.example.project_phase_2_1.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
//@RequestMapping()
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admins/{username}")
    ResponseEntity<AdminDTO> getAdmin(@PathVariable String username) {
        Optional<AdminDTO> adminDTOOptional = adminService.getAdminInfo(username);
        return adminDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
