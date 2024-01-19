package com.example.detyrekursigreisialba.model;

import java.util.List;

public class Question {
    private int id;
    private int quizId;
    private int index;
    private String name;
    private String answer;
    private List<String> options;

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Question() {

    }

    public Question(int quizId, int index, String name, List<String> options, String rightAnswer) {
        this.quizId = quizId;
        this.index = index;
        this.name = name;
        this.options = options;
        this.answer = rightAnswer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
