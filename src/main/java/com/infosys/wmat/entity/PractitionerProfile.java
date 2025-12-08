package com.infosys.wmat.entity;

import jakarta.persistence.*;

@Entity
public class PractitionerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String specialization;

    private boolean verified = false;

    private Double rating = 0.0;

    // --- Manual Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}