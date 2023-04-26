package com.example.project_phase_2_1.repository;

import com.example.project_phase_2_1.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, String> {
}
