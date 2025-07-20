package org.example.service;

import org.example.model.Question;
import org.example.model.Quiz;
import org.example.repository.QuestionRepository;
import org.example.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private QuizRepository quizRepo;

    public Question addQuestionToQuiz(Integer quizId, Question question) {
        Quiz quiz = quizRepo.findById(quizId).orElseThrow();
        question.setQuiz(quiz);
        return questionRepo.save(question);
    }

    public Question updateQuestion(Integer id, Question updated) {
        Question q = questionRepo.findById(id).orElseThrow();
        q.setQuestionText(updated.getQuestionText());
        return questionRepo.save(q);
    }

    public void deleteQuestion(Integer id) {
        questionRepo.deleteById(id);
    }

    public List<Question> getQuestionsByQuizId(Integer quizId) {
        return questionRepo.findByQuizId(quizId);
    }
    public Optional<Question> getQuestionsByQuiz(Integer id) {
        return questionRepo.findById(id);
    }
}
