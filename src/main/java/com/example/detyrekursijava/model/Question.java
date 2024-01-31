package com.example.detyrekursijava.model;

import java.util.List;

public class Question {
    private int id;
    private int quizId;
    private int index;
    private String name;
    private List<Option> options;

    public Question(int id, int quizId, int index, String name, List<Option> options) {
        this.id = id;
        this.quizId = quizId;
        this.index = index;
        this.name = name;
        this.options = options;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
