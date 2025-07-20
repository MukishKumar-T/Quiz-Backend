package org.example.controller;

import org.example.model.Question;
import org.example.repository.QuestionRepository;
import org.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    QuestionRepository questionRepo;

    @PostMapping("/quiz/{quizId}")
    public ResponseEntity<Question> addQuestionToQuiz(@PathVariable Integer quizId, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestionToQuiz(quizId, question));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(id, question));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<Optional<Question>> getQuestionsByQuizId(@PathVariable Integer quizId) {
        return ResponseEntity.ok(questionService.getQuestionsByQuiz(quizId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestionsById(@PathVariable Integer id) {
        return ResponseEntity.ok(questionRepo.findById(id));
    }
}
