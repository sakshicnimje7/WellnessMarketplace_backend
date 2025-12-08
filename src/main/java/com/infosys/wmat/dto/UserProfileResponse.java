package com.infosys.wmat.dto;

import com.infosys.wmat.entity.Role;

public class UserProfileResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private String bio;
    // Extra fields for Practitioners
    private String specialization;
    private boolean verified;

    // --- Manual Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }
}