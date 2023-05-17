package com.example.project_phase_2_1.repository;

import com.example.project_phase_2_1.entity.Appointment;
import com.example.project_phase_2_1.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findAllByDate(LocalDate localDate);
    List<Appointment> findAllByLocation(Location location);
    Page<Appointment> findAllByLocation(Location location, Pageable pageable);
    Page<Appointment> findAllByLocationAndDate(Location location, LocalDate localDate, Pageable pageable);
}
