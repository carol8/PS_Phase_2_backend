package com.example.project_phase_2_1.controller;

import com.example.project_phase_2_1.dto.admin.AdminInfoDTO;
import com.example.project_phase_2_1.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
//@RequestMapping()
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admins")
    ResponseEntity<AdminInfoDTO> getAdminInfo(@RequestParam(name = "username") String username) {
        Optional<AdminInfoDTO> adminInfoDTOOptional = adminService.getAdminInfo(username);
        return adminInfoDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
