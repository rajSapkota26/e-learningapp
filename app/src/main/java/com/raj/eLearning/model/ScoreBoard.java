package com.raj.eLearning.model;

public class ScoreBoard {
    private int id;
    private String userId;
    private int score;

    public ScoreBoard() {
    }

    public ScoreBoard(int id, String userId, int score) {
        this.id = id;
        this.userId = userId;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
