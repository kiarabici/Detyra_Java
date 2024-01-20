package com.example.detyrekursigreisialba.model;

public class UserAnswer {
    private int id;
    private int resultId;
    private int optionId;

    public UserAnswer() {
    }

    public UserAnswer(int resultId, int optionId) {
        this.resultId = resultId;
        this.optionId = optionId;
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
