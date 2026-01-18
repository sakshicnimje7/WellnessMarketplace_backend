package com.infosys.wmat.controller;

import com.infosys.wmat.entity.Answer;
import com.infosys.wmat.entity.Question;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.UserRepository;
import com.infosys.wmat.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/forum")
public class ForumController {

    @Autowired private ForumService forumService;
    @Autowired private UserRepository userRepository;

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return forumService.getAllQuestions();
    }

    // 2. Post a question (Fixing Payload Issue)
    @PostMapping("/ask")
    public Question postQuestion(@RequestBody Map<String, String> payload, Principal principal) {
        String content = payload.get("content"); // Frontend sends "content"
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return forumService.postQuestion(user.getId(), content);
    }

    // 3. Post an Answer
    @PostMapping("/{questionId}/answer")
    public Answer postAnswer(@PathVariable Long questionId, @RequestBody Map<String, String> payload, Principal principal) {
        String content = payload.get("content");
        String email = principal.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        return forumService.postAnswer(user.getId(), questionId, content);
    }
}