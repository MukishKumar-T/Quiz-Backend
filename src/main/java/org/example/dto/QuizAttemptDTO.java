package org.example.dto;

public class QuizAttemptDTO {
    private int id;
    private int score;
    private String quizTitle;
    private String quizCategory;
    private Integer averageScore;

    public QuizAttemptDTO(int id, int score, String quizTitle, String quizCategory, Integer averageScore) {
        this.id = id;
        this.score = score;
        this.quizTitle = quizTitle;
        this.quizCategory = quizCategory != null ? quizCategory : "General";
        this.averageScore = averageScore;
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

    public String getQuizCategory() {
        return quizCategory;
    }

    public void setQuizCategory(String quizCategory) {
        this.quizCategory = quizCategory;
    }

    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }
}