package com.infosys.wmat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    // 1. Initiate Payment
    @PostMapping("/initiate")
    public ResponseEntity<?> initiatePayment(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("transactionId", "TXN" + System.currentTimeMillis());
        response.put("status", "PENDING");
        response.put("amount", payload.get("amount"));
        response.put("gatewayUrl", "https://secure-payment-gateway.com/pay");
        return ResponseEntity.ok(response);
    }

    // 2. Confirm Payment (Simulates webhook)
    @PostMapping("/confirm")
    public ResponseEntity<?> confirmPayment(@RequestBody Map<String, String> payload) {
        return ResponseEntity.ok("Payment " + payload.get("transactionId") + " verified successfully.");
    }

    // 3. View Payment History
    @GetMapping("/history")
    public ResponseEntity<?> getPaymentHistory() {
        // Return dummy history
        Map<String, Object> record1 = new HashMap<>();
        record1.put("transactionId", "TXN123456789");
        record1.put("amount", 45.99);
        record1.put("date", LocalDateTime.now().minusDays(2));
        record1.put("status", "COMPLETED");

        return ResponseEntity.ok(java.util.Collections.singletonList(record1));
    }
}