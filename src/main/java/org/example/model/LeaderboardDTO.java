package org.example.model;

public class LeaderboardDTO {
    private String userName;
    private long totalScore;
    private long quizzesAttempted;

    public LeaderboardDTO(String userName, long totalScore, long quizzesAttempted) {
        this.userName = userName;
        this.totalScore = totalScore;
        this.quizzesAttempted = quizzesAttempted;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public long getQuizzesAttempted() {
        return quizzesAttempted;
    }
}
