package com.example.kuizu;

import java.io.Serializable;
import java.util.List;

public class question_data implements Serializable {

    private final String questionText;
    private final List<String> options;
    private final int correctAnswerIndex;

    public question_data(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
