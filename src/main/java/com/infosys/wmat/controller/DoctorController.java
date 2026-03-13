package com.infosys.wmat.controller;

import com.infosys.wmat.entity.TherapySession;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.TherapySessionRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired private TherapySessionRepository sessionRepository;
    @Autowired private UserRepository userRepository;

    // 1. Get My Appointments (For the Doctor)
    @GetMapping("/appointments")
    public List<TherapySession> getMyAppointments(Principal principal) {
        String email = principal.getName();
        User doctor = userRepository.findByEmail(email).orElseThrow();

        // FIX: Use findByPractitioner(doctor) instead of findByPractitionerId(id)
        return sessionRepository.findByPractitioner(doctor);
    }

    // 2. Mark Appointment as Completed
    @PutMapping("/appointment/{id}/complete")
    public ResponseEntity<?> markComplete(@PathVariable Long id) {
        TherapySession session = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setStatus("COMPLETED");
        sessionRepository.save(session);

        return ResponseEntity.ok("Session marked as completed.");
    }
}