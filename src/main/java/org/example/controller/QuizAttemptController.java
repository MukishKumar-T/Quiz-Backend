package org.example.controller;

import org.example.model.QuizAttempt;
import org.example.service.QuizAttemptService;
import org.example.dto.QuizAttemptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizAttempt")
public class QuizAttemptController {

    @Autowired
    QuizAttemptService quizAttemptService;

    @GetMapping("/")
    private List<QuizAttempt> getQuizAttempts(){
        return quizAttemptService.getQuizAttempts();
    }

    @GetMapping("/user/{userName}")
    public List<QuizAttemptDTO> getQuizAttemptsByUser(@PathVariable String userName) {
        return quizAttemptService.getQuizAttemptsByUser(userName);
    }

    @PostMapping("/updateScore/{userName}/{quizId}/{score}")
    public String updateScore(@PathVariable String userName, @PathVariable int quizId, @PathVariable int score){
        return quizAttemptService.updateScore(userName, quizId, score);
    }
}
