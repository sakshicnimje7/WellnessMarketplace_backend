package com.infosys.wmat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

@RestController
@RequestMapping("/api/external")
public class ExternalAPIController {

    // 1. FDA Drug Warnings
    @GetMapping("/fda/warnings")
    public ResponseEntity<?> getFDAWarnings() {
        Map<String, String> warning = new HashMap<>();
        warning.put("drug", "Ibuprofen");
        warning.put("warning", "Risk of stomach bleeding with prolonged use.");
        warning.put("source", "OpenFDA API");
        return ResponseEntity.ok(Arrays.asList(warning));
    }

    // 2. WHO Guidelines
    @GetMapping("/who/guidelines")
    public ResponseEntity<?> getWHOGuidelines() {
        Map<String, String> guide = new HashMap<>();
        guide.put("topic", "Physical Activity");
        guide.put("recommendation", "Adults should do at least 150 minutes of moderate-intensity activity per week.");
        guide.put("source", "WHO Global Health Observatory");
        return ResponseEntity.ok(Arrays.asList(guide));
    }
}