package com.infosys.wmat.controller;

import com.infosys.wmat.dto.ReviewRequest;
import com.infosys.wmat.entity.Review;
import com.infosys.wmat.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private com.infosys.wmat.repository.UserRepository userRepository;

    // 1. Add a Review
    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewRequest request, Principal principal) {
        String email = principal.getName();
        Long userId = userRepository.findByEmail(email).get().getId();

        return ResponseEntity.ok(reviewService.addReview(userId, request));
    }

    // 2. Get Reviews for a Product
    @GetMapping("/{productId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getProductReviews(productId));
    }
}