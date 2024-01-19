package com.example.detyrekursigreisialba.model;

import java.util.List;

public class Result {
    private int quizId;
    private String username;
    private List<UserAnswer> userAnswers;

    public Result(int quizId, String username, List<UserAnswer> answers) {
        this.quizId = quizId;
        this.username = username;
        this.userAnswers = answers;
    }

    public Result() {}

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getUsername() {
        return username;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
