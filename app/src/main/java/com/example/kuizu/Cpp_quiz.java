package com.example.kuizu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Cpp_quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp_quiz);
        Button start_quiz_button;


        start_quiz_button = findViewById(R.id.start_quiz_button);

        start_quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Cpp_quiz.this, cpp_startt.class);
                startActivity(next);
                finish();
            }
        });

    }
}