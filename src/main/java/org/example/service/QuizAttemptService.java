package org.example.service;

import org.example.model.Quiz;
import org.example.model.QuizAttempt;
import org.example.model.User;
import org.example.repository.QuizAttemptRepository;
import org.example.repository.QuizRepository;
import org.example.repository.UserRepository;
import org.example.dto.QuizAttemptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizAttemptService {

    @Autowired
    QuizAttemptRepository quizAttemptRepo;

    @Autowired
    QuizRepository quizRepo;

    @Autowired
    UserRepository userRepo;

    public List<QuizAttempt> getQuizAttempts(){
        return quizAttemptRepo.findAll();
    }

    public List<QuizAttemptDTO> getQuizAttemptsByUser(String userName) {
        return quizAttemptRepo.findByUser_UserName(userName)
            .stream()
            .map(a -> new QuizAttemptDTO(a.getId(), a.getScore(), a.getQuiz() != null ? a.getQuiz().getTitle() : "-"))
            .collect(Collectors.toList());
    }

    public String updateScore(String userName, int quizId, int score) {
        User user = userRepo.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Optional<QuizAttempt> optionalAttempt = quizAttemptRepo.findByUserAndQuiz(user, quiz);

        QuizAttempt quizAttempt = optionalAttempt.orElseGet(() -> {
            QuizAttempt newAttempt = new QuizAttempt();
            newAttempt.setUser(user);
            newAttempt.setQuiz(quiz);
            return newAttempt;
        });

        quizAttempt.setScore(score);
        quizAttemptRepo.save(quizAttempt);

        return "Score Updated!";
    }

}
