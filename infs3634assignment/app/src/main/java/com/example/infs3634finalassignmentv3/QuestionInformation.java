package com.example.infs3634finalassignmentv3;

public class QuestionInformation {
    //The Question Information class sets up the definition of a question along with its attributes and functions.
    private int q_id;
    private String question;
    private String answer;

    public QuestionInformation(int q_id, String question, String answer) {
        this.q_id = q_id;
        this.question = question;
        this.answer = answer;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
