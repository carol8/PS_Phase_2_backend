package com.example.project_phase_2_1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_doctor")
public class Doctor extends User {
    public String name, surname, email, cnp;

    @OneToOne
    @JoinColumn(name = "location_uuid", referencedColumnName = "uuid")
    public Location location;
}
