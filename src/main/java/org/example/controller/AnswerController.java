package org.example.controller;

import org.example.model.Answer;
import org.example.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/question/{questionId}")
    public ResponseEntity<Answer> addAnswerToQuestion(@PathVariable Integer questionId, @RequestBody Answer answer) {
        return ResponseEntity.ok(answerService.addAnswerToQuestion(questionId, answer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Integer id, @RequestBody Answer answer) {
        return ResponseEntity.ok(answerService.updateAnswer(id, answer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Integer id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Answer>> getAnswersByQuestion(@PathVariable Integer questionId) {
        return ResponseEntity.ok(answerService.getAnswersByQuestion(questionId));
    }
}
