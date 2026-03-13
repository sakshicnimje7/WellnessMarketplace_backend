package com.infosys.wmat.service;

import com.infosys.wmat.dto.PractitionerResponse;
import com.infosys.wmat.dto.PractitionerUpdateRequest; // Ensure this DTO exists
import com.infosys.wmat.dto.UserProfileResponse;
import com.infosys.wmat.entity.PractitionerProfile;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.PractitionerProfileRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PractitionerProfileRepository practitionerProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 1. SAVE USER
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // 2. FIND BY EMAIL
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // 3. GET ALL PRACTITIONERS
    public List<PractitionerResponse> getAllPractitioners() {
        return practitionerProfileRepository.findAll().stream()
                .map(profile -> new PractitionerResponse(
                        profile.getUser().getId(),
                        profile.getUser().getName(),
                        profile.getSpecialization(),
                        profile.getBio(),
                        profile.getRating(),
                        profile.isVerified()
                ))
                .collect(Collectors.toList());
    }

    // 4. GET USER PROFILE
    public UserProfileResponse getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfileResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    // 5. UPDATE PRACTITIONER PROFILE (New Method)
    public void updatePractitionerProfile(String email, PractitionerUpdateRequest request) {
        // 1. Find User (Login info)
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Find Profile (Doctor info)
        PractitionerProfile profile = practitionerProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Practitioner Profile not found"));

        // 3. Update Fields (Only if they are not empty)
        if (request.getName() != null && !request.getName().isEmpty()) {
            user.setName(request.getName());
        }
        if (request.getSpecialization() != null && !request.getSpecialization().isEmpty()) {
            profile.setSpecialization(request.getSpecialization());
        }
        if (request.getBio() != null && !request.getBio().isEmpty()) {
            profile.setBio(request.getBio());
        }

        // 4. Save Both
        userRepository.save(user);
        practitionerProfileRepository.save(profile);
    }
}