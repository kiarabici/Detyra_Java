package com.example.detyrekursigreisialba.model;

public class Option {
    private int id;
    private int questionId;
    private String value;
    private boolean isAnswer;

    public Option(int questionId, String value, boolean isAnswer) {
        this.questionId = questionId;
        this.value = value;
        this.isAnswer = isAnswer;
    }

    public boolean getAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getId() {
        return id;
    }

    public Option() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
