package com.infosys.wmat.service;

import com.infosys.wmat.entity.Answer;
import com.infosys.wmat.entity.Question;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.AnswerRepository;
import com.infosys.wmat.repository.QuestionRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ForumService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    // 1. Get All Questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAllByOrderByCreatedAtDesc();
    }

    // 2. Post a Question
    public Question postQuestion(Long userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Question q = new Question();
        q.setContent(content);
        q.setUser(user); // Correctly linking User entity
        q.setCreatedAt(LocalDateTime.now()); // Correctly setting timestamp

        return questionRepository.save(q);
    }

    // 3. Post an Answer (THIS WAS MISSING)
    public Answer postAnswer(Long userId, Long questionId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Answer answer = new Answer();
        answer.setContent(content);
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setCreatedAt(LocalDateTime.now());

        return answerRepository.save(answer);
    }
}