package com.example.project_phase_2_1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "app_appointment")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@uuid")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    public UUID uuid;

    public LocalDate date;
    public boolean isValid, emailNotificationsEnabled, smsNotificationsEnabled;
    public String result; //TODO update all dtos

    @ManyToOne
    @JoinColumn(name = "donor_username", nullable = false)
    @JsonManagedReference
    public Donor donor;

    @ManyToOne
    @JoinColumn(name = "location_uuid", nullable = false)
    @JsonManagedReference
    public Location location;
}
