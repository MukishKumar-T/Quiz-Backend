package org.example.service;

import org.example.dto.QuizDTO;
import org.example.model.Question;
import org.example.model.Quiz;
import org.example.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepo;

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
}
