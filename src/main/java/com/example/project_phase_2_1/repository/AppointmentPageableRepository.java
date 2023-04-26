package com.example.project_phase_2_1.repository;

import com.example.project_phase_2_1.entity.Appointment;
import com.example.project_phase_2_1.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface AppointmentPageableRepository extends PagingAndSortingRepository<Appointment, UUID> {
    Page<Appointment> findAllByLocation(Location location, Pageable pageable);
}
