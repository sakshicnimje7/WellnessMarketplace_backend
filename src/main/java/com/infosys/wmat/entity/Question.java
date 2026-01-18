package com.infosys.wmat.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    // --- MISSING FIELDS ADDED BELOW ---

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Links the question to the user who asked it

    private LocalDateTime createdAt; // Stores the time of the post

    // --- CONSTRUCTORS ---
    public Question() {
        this.createdAt = LocalDateTime.now(); // Auto-set time when created
    }

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Helper to get user name safely for the frontend
    public String getUserName() {
        return user != null ? user.getName() : "Anonymous";
    }
}