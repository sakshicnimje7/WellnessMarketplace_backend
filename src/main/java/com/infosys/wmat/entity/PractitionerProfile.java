package com.infosys.wmat.entity;

import jakarta.persistence.*;

@Entity
public class PractitionerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String specialization;
    private Double rating;
    private String bio;

    // --- FIXED HERE ---
    // 1. Renamed to 'verified' to match the database error exactly.
    // 2. Set to 'false' so it is never null.
    private boolean verified = false;

    public PractitionerProfile() {
        // Double safety: ensure it's false on creation
        this.verified = false;
        this.rating = 0.0;
    }

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    // Standard Boolean Getter
    public boolean isVerified() { return verified; }

    // This matches what your AuthService calls
    public void setVerified(boolean verified) { this.verified = verified; }
}