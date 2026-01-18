package com.infosys.wmat.controller;

import com.infosys.wmat.dto.PractitionerResponse;
import com.infosys.wmat.dto.PractitionerUpdateRequest; // <--- Import for the DTO
import com.infosys.wmat.dto.UserProfileResponse;
import com.infosys.wmat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // <--- This imports PutMapping, RequestBody, etc.

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Get My Profile
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(Principal principal) {
        String email = principal.getName();
        return ResponseEntity.ok(userService.getUserProfile(email));
    }

    // 2. Get All Practitioners (For Therapies Page)
    @GetMapping("/practitioners")
    public ResponseEntity<List<PractitionerResponse>> getAllPractitioners() {
        return ResponseEntity.ok(userService.getAllPractitioners());
    }

    // 3. Update Practitioner Profile (New Method)
    @PutMapping("/practitioner/update")
    public ResponseEntity<?> updateProfile(@RequestBody PractitionerUpdateRequest request, Principal principal) {
        userService.updatePractitionerProfile(principal.getName(), request);
        return ResponseEntity.ok("Profile updated successfully");
    }
}