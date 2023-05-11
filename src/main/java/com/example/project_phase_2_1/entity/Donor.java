package com.example.project_phase_2_1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "app_donor")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@username")
public class Donor extends User {
    public String name, surname, email, phone, cnp;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
    @JsonBackReference
    public List<Appointment> appointmentList;
}
