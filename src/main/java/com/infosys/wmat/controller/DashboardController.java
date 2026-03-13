package com.infosys.wmat.controller;

import com.infosys.wmat.entity.TherapySession;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.OrderRepository;
import com.infosys.wmat.repository.TherapySessionRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired private UserRepository userRepository;
    @Autowired private TherapySessionRepository sessionRepository;
    @Autowired private OrderRepository orderRepository;

    @GetMapping("/user")
    public ResponseEntity<?> getUserDashboard(Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> dashboardData = new HashMap<>();

        // 1. Common User Details
        dashboardData.put("name", user.getName());
        dashboardData.put("email", user.getEmail());
        dashboardData.put("role", user.getRole());

        // 2. LOGIC SPLIT: What to show based on Role?
        if ("PRACTITIONER".equals(user.getRole().toString())) {
            // DOCTOR VIEW: Show sessions they are hosting
            List<TherapySession> doctorSessions = sessionRepository.findByPractitioner(user);
            dashboardData.put("bookings", doctorSessions);
            dashboardData.put("isDoctor", true);
        } else {
            // PATIENT VIEW: Show sessions they are attending
            // Fix: Use findByPatient(user) instead of findByPatientId
            List<TherapySession> patientSessions = sessionRepository.findByPatient(user);
            dashboardData.put("bookings", patientSessions);

            // Patients also see their shopping orders
            dashboardData.put("orders", orderRepository.findByUser(user));
            dashboardData.put("isDoctor", false);
        }

        return ResponseEntity.ok(dashboardData);
    }
}