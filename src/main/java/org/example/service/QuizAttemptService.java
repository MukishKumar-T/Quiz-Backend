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
        List<QuizAttempt> attempts = quizAttemptRepo.findByUser_UserName(userName);
        
        return attempts.stream()
            .map(a -> {
                Double averageScore = a.getQuiz() != null ? 
                    quizAttemptRepo.findAverageScoreByQuizId(a.getQuiz().getId()) : 
                    null;
                    
                return new QuizAttemptDTO(
                    a.getId(), 
                    a.getScore(), 
                    a.getQuiz() != null ? a.getQuiz().getTitle() : "-",
                    a.getQuiz() != null ? a.getQuiz().getCategory() : null,
                    averageScore != null ? averageScore.intValue() : null,
                    a.getQuiz() != null ? a.getQuiz().getId() : null
                );
            })
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
