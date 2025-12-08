package com.infosys.wmat.controller;

import com.infosys.wmat.dto.UserProfileResponse;
import com.infosys.wmat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(Principal principal) {
        // Principal.getName() returns the email (because we set it as the subject in JWT)
        String email = principal.getName();
        return ResponseEntity.ok(userService.getUserProfile(email));
    }
}