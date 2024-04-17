package com.example.quizz_game;

public class QuestionModel {
    private int questionId;
    private String questionName;
    private String questionCorrectAnswer;
    private String questionOptionA;
    private String questionOptionB;
    private String questionOptionC;
    private String questionOptionD;

    public QuestionModel(){

    }

    public String getQuestionCorrectAnswer() {
        return questionCorrectAnswer;
    }

    public void setQuestionCorrectAnswer(String questionCorrectAnswer) {
        this.questionCorrectAnswer = questionCorrectAnswer;
    }

    public String getQuestionOptionA() {
        return questionOptionA;
    }

    public void setQuestionOptionA(String questionOptionA) {
        this.questionOptionA = questionOptionA;
    }

    public String getQuestionOptionB() {
        return questionOptionB;
    }

    public void setQuestionOptionB(String questionOptionB) {
        this.questionOptionB = questionOptionB;
    }

    public String getQuestionOptionC() {
        return questionOptionC;
    }

    public void setQuestionOptionC(String questionOptionC) {
        this.questionOptionC = questionOptionC;
    }

    public String getQuestionOptionD() {
        return questionOptionD;
    }

    public void setQuestionOptionD(String questionOptionD) {
        this.questionOptionD = questionOptionD;
    }

    public QuestionModel(int questionId, String questionName, String questionCorrectAnswer, String questionOptionA, String questionOptionB, String questionOptionC, String questionOptionD) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.questionCorrectAnswer = questionCorrectAnswer;
        this.questionOptionA = questionOptionA;
        this.questionOptionB = questionOptionB;
        this.questionOptionC = questionOptionC;
        this.questionOptionD = questionOptionD;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }
}
