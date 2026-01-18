package com.infosys.wmat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class BookingRequest {
    private Long practitionerId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // <--- MUST HAVE THIS
    private LocalDateTime date;

    private String notes;

    // Getters and Setters...
    public Long getPractitionerId() { return practitionerId; }
    public void setPractitionerId(Long practitionerId) { this.practitionerId = practitionerId; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}