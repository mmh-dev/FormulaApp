package com.example.formulaapp.Models;

import java.io.Serializable;
import java.util.List;

public class TestData implements Serializable {
    private String header;
    private boolean isFinal;
    private int points;
    private int totalQuestionsNumber;
    private List<Question> questionList;
    private User user;

    public TestData(String header, boolean isFinal, int points, int totalQuestionsNumber, List<Question> questionList, User user) {
        this.header = header;
        this.isFinal = isFinal;
        this.points = points;
        this.totalQuestionsNumber = totalQuestionsNumber;
        this.questionList = questionList;
        this.user = user;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTotalQuestionsNumber() {
        return totalQuestionsNumber;
    }

    public void setTotalQuestionsNumber(int totalQuestionsNumber) {
        this.totalQuestionsNumber = totalQuestionsNumber;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
