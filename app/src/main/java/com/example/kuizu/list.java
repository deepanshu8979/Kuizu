package com.example.kuizu;

import java.util.ArrayList;

public class list {
    private final ArrayList <question_data> questions;

    public list() {
        questions = new ArrayList<>();
    }

    // Method to add a question to the list
    public void add(question_data question) {
        questions.add(question);
    }

    // Method to get a question by index
    public int get(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index).getCorrectAnswerIndex();
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    // Method to get the size of the list
    public int size() {
        return questions.size();
    }
}
