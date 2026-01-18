package com.infosys.wmat.controller;

import com.infosys.wmat.entity.PractitionerProfile; // Import this
import com.infosys.wmat.entity.Role;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.PractitionerProfileRepository; // Import this
import com.infosys.wmat.service.UserService;
import com.infosys.wmat.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserService userService;
    @Autowired private PractitionerProfileRepository practitionerProfileRepository; // Add this

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // 1. Save the User
        User savedUser = userService.saveUser(user);

        // 2. IF the user is a PRACTITIONER, create an empty profile for them automatically
        if (savedUser.getRole() == Role.PRACTITIONER) {
            PractitionerProfile profile = new PractitionerProfile();
            profile.setUser(savedUser);
            profile.setSpecialization("General Practitioner"); // Default value
            profile.setVerified(false);
            profile.setRating(0.0);
            practitionerProfileRepository.save(profile);
        }

        return "User registered successfully";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String token = jwtUtil.generateToken(userDetails);
        return Map.of("token", token);
    }
}