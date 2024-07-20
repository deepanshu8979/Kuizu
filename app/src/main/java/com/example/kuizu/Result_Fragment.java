package com.example.kuizu;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kuizu.databinding.FragmentResultBinding;


public class Result_Fragment extends Fragment {


        FragmentResultBinding binding;
    int right, allQuestion;

    public Result_Fragment() {
        // Required empty public constructor
    }

    public Result_Fragment(int right, int allQuestion) {
        this.right = right;
        this.allQuestion = allQuestion;




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        binding = FragmentResultBinding.inflate(getLayoutInflater());
        String rightScore = String.valueOf(right);
        String wrongScore = String.valueOf(allQuestion-right);
        String totalScore = String.valueOf(allQuestion);

        binding.correct.setText(rightScore+"Correct");
        binding.correct.setText(wrongScore+ "Wrong");
        binding.correct.setText("You got the" +rightScore +"out of" +totalScore);



        binding.homeBtn.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), Main_Menu.class);
            getActivity().startActivity(intent);

        });

        binding.replay.setOnClickListener(v -> {

            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new Java_Fragment()).addToBackStack(null).commit();
        });
        return binding.getRoot();
    }
}