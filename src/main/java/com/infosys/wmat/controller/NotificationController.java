package com.infosys.wmat.controller;

import com.infosys.wmat.entity.Notification;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.NotificationRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired private NotificationRepository notificationRepository;
    @Autowired private UserRepository userRepository;

    // 1. Get My Notifications
    @GetMapping
    public List<Notification> getUserNotifications(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }

    // 2. Get Unread Count
    @GetMapping("/unread-count")
    public long getUnreadCount(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        return notificationRepository.countByUserAndIsReadFalse(user);
    }

    // 3. Mark as Read
    @PutMapping("/{id}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long id) {
        Notification n = notificationRepository.findById(id).orElseThrow();
        n.setRead(true);
        notificationRepository.save(n);
        return ResponseEntity.ok("Marked as read");
    }

    // 4. Send Test Notification (For Demo)
    @PostMapping("/send")
    public Notification sendNotification(@RequestBody Map<String, String> payload, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        Notification n = new Notification();
        n.setUser(user);
        n.setType("SYSTEM_ALERT");
        n.setMessage(payload.get("message"));
        return notificationRepository.save(n);
    }
}