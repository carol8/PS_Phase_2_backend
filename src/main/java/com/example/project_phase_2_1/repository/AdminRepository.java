package com.example.project_phase_2_1.repository;

import com.example.project_phase_2_1.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
