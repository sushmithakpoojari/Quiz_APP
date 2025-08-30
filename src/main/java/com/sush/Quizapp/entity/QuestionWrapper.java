package com.sush.Quizapp.entity;

import lombok.Data;

@Data
public class QuestionWrapper {


    private Integer id;
    private String questiontitle;
    private String option1;
    private String option2;
    private String answer;
    private String category;

    public QuestionWrapper(Integer id, String questiontitle, String option1, String option2, String answer, String category) {
        this.id = id;
        this.questiontitle = questiontitle;
        this.option1 = option1;
        this.option2 = option2;
        this.answer = answer;
        this.category = category;
    }

    public QuestionWrapper(Integer id, String questiontitle, String option1, String answer) {
        this.id = id;
        this.questiontitle = questiontitle;
        this.option1 = option1;
        this.answer = answer;
    }
}
