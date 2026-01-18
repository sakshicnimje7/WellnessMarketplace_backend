package com.infosys.wmat.dto;

public class PractitionerResponse {
    private Long id;
    private String name;
    private String specialization;
    private String bio;
    private Double rating;
    private boolean verified;

    // --- CONSTRUCTOR ---
    public PractitionerResponse(Long id, String name, String specialization, String bio, Double rating, boolean verified) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.bio = bio;
        this.rating = rating;
        this.verified = verified;
    }

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }
}