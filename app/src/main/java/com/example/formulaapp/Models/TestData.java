package com.example.formulaapp.Models;

import java.io.Serializable;
import java.util.List;

public class TestData implements Serializable {
    private String header;
    private boolean isFinal;
    private double points;
    private double totalQuestionsNumber;
    private List<Question> questionList;
    private User user;
    private int count;

    public TestData(String header, boolean isFinal, double points, double totalQuestionsNumber, List<Question> questionList, User user, int count) {
        this.header = header;
        this.isFinal = isFinal;
        this.points = points;
        this.totalQuestionsNumber = totalQuestionsNumber;
        this.questionList = questionList;
        this.user = user;
        this.count = count;
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

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getTotalQuestionsNumber() {
        return totalQuestionsNumber;
    }

    public void setTotalQuestionsNumber(double totalQuestionsNumber) {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
