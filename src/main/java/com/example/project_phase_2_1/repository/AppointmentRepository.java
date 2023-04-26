package com.example.project_phase_2_1.repository;

import com.example.project_phase_2_1.entity.Appointment;
import com.example.project_phase_2_1.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findAllByLocation(Location location);

    List<Appointment> findAllByLocationAndDate(Location location, LocalDate localDate);


}
