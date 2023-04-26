package com.example.project_phase_2_1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "app_location")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@uuid")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    public UUID uuid;
    public String name, address;
    public LocalTime openingTime, closingTime;
    public int maximumDailyDonations;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonBackReference
    public List<Appointment> appointmentList;
}