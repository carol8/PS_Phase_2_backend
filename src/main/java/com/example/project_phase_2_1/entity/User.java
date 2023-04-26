package com.example.project_phase_2_1.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "app_user")
public class User {
    @Id
    public String username;
    public String password;
}
