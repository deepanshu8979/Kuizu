package com.example.kuizu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;


public class Question_Fragment extends Fragment {

    private static final String ARG_QUESTION = "question";
    private question_data question;
    private CountDownTimer timer;
    private OnNextQuestionListener listener;
    private TextView tvTimer;

    public interface OnNextQuestionListener {
        void onNextQuestion();
    }

    public static Question_Fragment newInstance(int question) {
        Question_Fragment fragment = new Question_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUESTION, (Serializable) question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = (question_data) getArguments().getSerializable(ARG_QUESTION);
        }
        if (getActivity() instanceof OnNextQuestionListener) {
            listener = (OnNextQuestionListener) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_, container, false);

        TextView tvQuestion = view.findViewById(R.id.tv_question);
        RadioGroup rgOptions = view.findViewById(R.id.rg_options);
        RadioButton rbOption1 = view.findViewById(R.id.rb_option1);
        RadioButton rbOption2 = view.findViewById(R.id.rb_option2);
        RadioButton rbOption3 = view.findViewById(R.id.rb_option3);
        RadioButton rbOption4 = view.findViewById(R.id.rb_option4);
        Button btnNext = view.findViewById(R.id.btn_next);
        tvTimer = view.findViewById(R.id.tv_timer);

        tvQuestion.setText(question.getQuestionText());
        rbOption1.setText(question.getOptions().get(0));
        rbOption2.setText(question.getOptions().get(1));
        rbOption3.setText(question.getOptions().get(2));
        rbOption4.setText(question.getOptions().get(3));

        btnNext.setOnClickListener(v -> {
            if (listener != null) {
                listener.onNextQuestion();
            }
        });

        startTimer();

        return view;
    }

    private void startTimer() {
        timer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                tvTimer.setText(String.valueOf(secondsRemaining));
            }

            @Override
            public void onFinish() {
                if (listener != null) {
                    listener.onNextQuestion();
                }
            }
        }.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timer != null) {
            timer.cancel();
        }
    }
}