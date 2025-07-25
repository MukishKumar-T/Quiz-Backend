package org.example.dto;

public class QuizAttemptDTO {
    private int id;
    private int score;
    private String quizTitle;

    public QuizAttemptDTO(int id, int score, String quizTitle) {
        this.id = id;
        this.score = score;
        this.quizTitle = quizTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }
} 