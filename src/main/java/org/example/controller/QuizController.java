package org.example.controller;

import org.example.dto.QuizDTO;
import org.example.model.Question;
import org.example.model.Quiz;
import org.example.model.User;
import org.example.repository.QuestionRepository;
import org.example.repository.QuizRepository;
import org.example.repository.UserRepository;
import org.example.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

 @Autowired
    private QuestionRepository questionRepo;
    
    @Autowired
    private QuizService quizService;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Quiz> getApprovedQuizzes() {
        return quizService.getApprovedQuizzes();
    }

    @GetMapping("/quizId/{id}")
    public List<Question> getQuiz(@PathVariable int id){
        return questionRepo.findByQuizId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable int id) {
        Quiz quiz = quizService.getQuizById(id);
        if (quiz != null) {
            return ResponseEntity.ok(quiz);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contributor/{userId}")
    public ResponseEntity<List<Quiz>> getQuizzesByContributor(@PathVariable Integer userId) {
        List<Quiz> quizzes = quizService.getQuizzesByContributor(userId);
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/submit")
    public ResponseEntity<Quiz> submitQuizForApproval(@RequestBody QuizDTO quizDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = userRepository.findByUserName(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        return ResponseEntity.ok(quizService.submitQuizForApproval(quizDTO, currentUser.getUserId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Integer id, @RequestBody Quiz updatedQuiz) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        quiz.setTitle(updatedQuiz.getTitle());
        quiz.setCategory(updatedQuiz.getCategory());
        return ResponseEntity.ok(quizRepository.save(quiz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Integer id) {
        quizRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

