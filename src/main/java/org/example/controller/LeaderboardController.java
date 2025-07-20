package org.example.controller;

import org.example.model.LeaderboardDTO;
import org.example.repository.QuizAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    @Autowired
    private QuizAttemptRepository quizAttemptRepo;

    @GetMapping
    public List<LeaderboardDTO> getLeaderboard() {
        return quizAttemptRepo.getLeaderboard();
    }
}
