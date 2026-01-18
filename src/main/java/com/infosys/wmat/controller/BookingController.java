package com.infosys.wmat.controller;

import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.UserRepository;
import com.infosys.wmat.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired private BookingService bookingService;
    @Autowired private UserRepository userRepository; // Added to lookup ID from email

    @PostMapping("/book")
    public ResponseEntity<?> bookSession(@RequestBody Map<String, String> payload, Principal principal) {
        try {
            // 1. Get the Patient's ID using their Email
            String email = principal.getName();
            User patient = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Long patientId = patient.getId();

            // 2. Parse the Input Data
            // "practitionerId" comes as a String in the JSON map, so we parse it
            Long practitionerId = Long.parseLong(String.valueOf(payload.get("practitionerId")));

            // "date" comes as a String (e.g., "2025-12-30T10:00"), so we parse it to LocalDateTime
            String dateStr = payload.get("date");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr);

            // 3. Call Service with correct Types (Long, Long, LocalDateTime)
            bookingService.bookSession(patientId, practitionerId, dateTime);

            return ResponseEntity.ok("Booking Successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}