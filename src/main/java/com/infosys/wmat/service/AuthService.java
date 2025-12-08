package com.infosys.wmat.service;

import com.infosys.wmat.dto.RegisterRequest;
import com.infosys.wmat.entity.PractitionerProfile;
import com.infosys.wmat.entity.Role;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.PractitionerProfileRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PractitionerProfileRepository practitionerProfileRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setBio(request.getBio());

        User savedUser = userRepository.save(user);

        // If user is a Practitioner, auto-create a profile
        if (request.getRole() == Role.PRACTITIONER) {
            PractitionerProfile profile = new PractitionerProfile();
            profile.setUser(savedUser);
            profile.setSpecialization(request.getSpecialization());
            profile.setVerified(false); // Milestone 1 Requirement: Verification pending
            practitionerProfileRepository.save(profile);
        }

        return savedUser;
    }
}
