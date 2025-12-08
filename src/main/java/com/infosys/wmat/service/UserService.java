package com.infosys.wmat.service;

import com.infosys.wmat.dto.UserProfileResponse;
import com.infosys.wmat.entity.PractitionerProfile;
import com.infosys.wmat.entity.Role;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.PractitionerProfileRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PractitionerProfileRepository practitionerRepository;

    public UserProfileResponse getUserProfile(String email) {
        // 1. Fetch the basic User
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Map to Response DTO
        UserProfileResponse response = new UserProfileResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setBio(user.getBio());

        // 3. If Practitioner, fetch extra profile details
        if (user.getRole() == Role.PRACTITIONER) {
            // Find the profile linked to this user
            // Note: In a real app, you might need a custom query: findByUserId
            // For now, we will filter manually or add a method to Repository
            PractitionerProfile profile = practitionerRepository.findAll().stream()
                    .filter(p -> p.getUser().getId().equals(user.getId()))
                    .findFirst()
                    .orElse(null);

            if (profile != null) {
                response.setSpecialization(profile.getSpecialization());
                response.setVerified(profile.isVerified());
            }
        }

        return response;
    }
}