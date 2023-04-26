package com.example.project_phase_2_1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "app_donor")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@username")
public class Donor extends User {
    public String name, surname;
    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
    @JsonBackReference
    public List<Appointment> appointmentList;
}
