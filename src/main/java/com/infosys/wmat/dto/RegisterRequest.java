package com.infosys.wmat.dto;

import com.infosys.wmat.entity.Role;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
    private String bio;
    private String specialization;

    // --- Manual Getters and Setters ---

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
}