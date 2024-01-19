package com.example.detyrekursigreisialba.model;

public class UserAnswer {
    private int quizId;
    private String answer;
    private boolean correct;

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public UserAnswer(int quizId, String answer, boolean correct) {
        this.quizId = quizId;
        this.answer = answer;
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
