package com.infosys.wmat.controller;

import com.infosys.wmat.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/analyze")
    public ResponseEntity<Map<String, String>> getRecommendation(@RequestBody Map<String, String> payload) {
        String symptom = payload.get("symptom");
        // Call our logic engine
        Map<String, String> result = recommendationService.analyzeSymptoms(symptom);
        return ResponseEntity.ok(result);
    }
}