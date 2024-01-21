package com.example.detyrekursigreisialba.model;

public class UserAnswer {
    private int id;
    private int resultId;
    private int questionId;
    private int optionId;

    public UserAnswer() {
    }

    public UserAnswer(int resultId, int questionId, int optionId) {
        this.resultId = resultId;
        this.questionId = questionId;
        this.optionId = optionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
