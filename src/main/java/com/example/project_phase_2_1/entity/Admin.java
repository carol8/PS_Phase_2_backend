package com.example.project_phase_2_1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_admin")
public class Admin extends User {
    public String name, surname;
}
