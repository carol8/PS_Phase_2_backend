package com.example.project_phase_2_1.repository;

import com.example.project_phase_2_1.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
}
