package org.example.service;

import org.example.model.Answer;
import org.example.model.Question;
import org.example.repository.AnswerRepository;
import org.example.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepo;

    @Autowired
    private QuestionRepository questionRepo;

    public Answer addAnswerToQuestion(Integer questionId, Answer answer) {
        Question question = questionRepo.findById(questionId).orElseThrow();
        answer.setQuestion(question);
//        System.out.println("Answer received: " + answer.isCorrect());
        return answerRepo.save(answer);
    }

    public Answer updateAnswer(Integer id, Answer updated) {
        Answer a = answerRepo.findById(id).orElseThrow();
        a.setAnswerText(updated.getAnswerText());
        a.setCorrect(updated.isCorrect());
        return answerRepo.save(a);
    }

    public void deleteAnswer(Integer id) {
        answerRepo.deleteById(id);
    }

    public List<Answer> getAnswersByQuestion(Integer questionId) {
        return answerRepo.findByQuestionId(questionId);
    }
}

