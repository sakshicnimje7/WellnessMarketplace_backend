package com.infosys.wmat.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecommendationService {

    // A simple knowledge base for our "AI"
    // In a real startup, this would be a Python model, but for this project,
    // a keyword-based logic engine is perfect and much faster.
    public Map<String, String> analyzeSymptoms(String input) {
        String symptom = input.toLowerCase();
        Map<String, String> suggestion = new HashMap<>();

        if (symptom.contains("back") || symptom.contains("spine") || symptom.contains("posture")) {
            suggestion.put("therapy", "Chiropractic Adjustment");
            suggestion.put("product", "Bamboo Yoga Mat");
            suggestion.put("reason", "Chiropractic care aligns the spine, while a yoga mat helps with stretching exercises.");
        }
        else if (symptom.contains("stress") || symptom.contains("anxiety") || symptom.contains("sleep")) {
            suggestion.put("therapy", "Meditation & Yoga");
            suggestion.put("product", "Herbal Tea");
            suggestion.put("reason", "Meditation reduces cortisol levels. Herbal tea promotes relaxation before sleep.");
        }
        else if (symptom.contains("muscle") || symptom.contains("pain") || symptom.contains("sore")) {
            suggestion.put("therapy", "Physiotherapy");
            suggestion.put("product", "Meditation Cushion");
            suggestion.put("reason", "Physiotherapy targets muscle recovery. A cushion helps maintain posture during rest.");
        }
        else if (symptom.contains("skin") || symptom.contains("face") || symptom.contains("glow")) {
            suggestion.put("therapy", "Ayurveda");
            suggestion.put("product", "Herbal Oils");
            suggestion.put("reason", "Ayurvedic treatments use natural oils to rejuvenate skin health.");
        }
        else {
            suggestion.put("therapy", "General Wellness Checkup");
            suggestion.put("product", "Vitamin Supplements");
            suggestion.put("reason", "We couldn't detect a specific issue, but staying active is always good!");
        }

        return suggestion;
    }
}