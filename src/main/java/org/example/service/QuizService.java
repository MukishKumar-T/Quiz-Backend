package org.example.service;

import org.example.dto.QuizDTO;
import org.example.model.Quiz;
import org.example.model.User;
import org.example.repository.QuizRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepo;
    
    @Autowired
    private UserRepository userRepository;

    public Quiz createQuiz(QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setId(quizDTO.getId());
        quiz.setTitle(quizDTO.getTitle());
        quiz.setCategory(quizDTO.getCategory());
        quiz.setQuestions(quizDTO.getQuestions());
        return quizRepo.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }

    public Quiz updateQuiz(Integer id, Quiz updated) {
        Quiz quiz = quizRepo.findById(id).orElseThrow();
        quiz.setTitle(updated.getTitle());
        quiz.setCategory(updated.getCategory());
        return quizRepo.save(quiz);
    }

    public void deleteQuiz(Integer id) {
        quizRepo.deleteById(id);
    }
    
    @Transactional
    public Quiz submitQuizForApproval(QuizDTO quizDTO, int userId) {
        User contributor = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDTO.getTitle());
        quiz.setCategory(quizDTO.getCategory());
        quiz.setApproved(false);
        quiz.setContributor(contributor);
        
        // Save the quiz first to generate an ID
        Quiz savedQuiz = quizRepo.save(quiz);
        
        // Set the quiz reference for all questions and answers
        if (quizDTO.getQuestions() != null) {
            quizDTO.getQuestions().forEach(question -> {
                question.setQuiz(savedQuiz);
                if (question.getAnswers() != null) {
                    question.getAnswers().forEach(answer -> answer.setQuestion(question));
                }
            });
            savedQuiz.setQuestions(quizDTO.getQuestions());
            return quizRepo.save(savedQuiz);
        }
        
        return savedQuiz;
    }
    
    public Quiz approveQuiz(Integer quizId) {
        Quiz quiz = quizRepo.findById(quizId)
            .orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setApproved(true);
        return quizRepo.save(quiz);
    }
    
    public List<Quiz> getPendingQuizzes() {
        return quizRepo.findByApprovedFalse();
    }
    
    public List<Quiz> getApprovedQuizzes() {
        return quizRepo.findByApprovedTrue();
    }
    
    public List<Quiz> getQuizzesByContributor(Integer userId) {
        return quizRepo.findByContributorUserId(userId);
    }
    
    public Quiz getQuizById(Integer id) {
        return quizRepo.findById(id).orElse(null);
    }
}
