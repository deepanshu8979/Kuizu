package com.example.kuizu;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.Arrays;


public class cpp_startt extends AppCompatActivity implements Question_Fragment.OnNextQuestionListener {

    private int currentQuestionIndex = 0;
    private list questionList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp_startt);

        questionList = new list();
        loadQuestions();

        if (savedInstanceState == null) {
            loadQuestionFragment(currentQuestionIndex);
        }
    }
    private void loadQuestions() {
        questionList.add(new question_data("What is the capital of France?", Arrays.asList("Paris", "Berlin", "Rome", "Madrid"), 0));
        // Add 19 more questions here...
        Log.d(TAG, "Questions loaded: " + questionList.size());
    }

    private void loadQuestionFragment(int index) {
        if (index < questionList.size()) {
            Fragment questionFragment = Question_Fragment.newInstance(questionList.get(index));
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, questionFragment);
            transaction.commit();
        } else {
            Toast.makeText(this, "Error",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNextQuestion() {
        currentQuestionIndex++;
        loadQuestionFragment(currentQuestionIndex);
    }
 }
