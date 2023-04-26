package com.example.project_phase_2_1.repository;

import com.example.project_phase_2_1.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
}
