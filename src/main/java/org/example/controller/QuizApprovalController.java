package org.example.controller;

import org.example.model.Quiz;
import org.example.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/quiz-approvals")
public class QuizApprovalController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/pending")
    public ResponseEntity<List<Quiz>> getPendingQuizzes() {
        List<Quiz> pendingQuizzes = quizService.getPendingQuizzes();
        return ResponseEntity.ok(pendingQuizzes);
    }

    @PostMapping("/{quizId}/approve")
    public ResponseEntity<Quiz> approveQuiz(@PathVariable Integer quizId) {
        Quiz approvedQuiz = quizService.approveQuiz(quizId);
        return ResponseEntity.ok(approvedQuiz);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> rejectQuiz(@PathVariable Integer quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.noContent().build();
    }
}
