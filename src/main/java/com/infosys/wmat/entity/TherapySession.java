package com.infosys.wmat.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TherapySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // OLD: private Long patientId;
    // NEW: Proper Relationship
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    // OLD: private Long practitionerId;
    // NEW: Proper Relationship matches 'findByPractitioner'
    @ManyToOne
    @JoinColumn(name = "practitioner_id")
    private User practitioner;

    private LocalDateTime date;
    private String status; // SCHEDULED, COMPLETED, CANCELLED

    public TherapySession() {}

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getPatient() { return patient; }
    public void setPatient(User patient) { this.patient = patient; }

    public User getPractitioner() { return practitioner; }
    public void setPractitioner(User practitioner) { this.practitioner = practitioner; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}