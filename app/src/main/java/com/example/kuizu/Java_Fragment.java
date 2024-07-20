package com.example.kuizu;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kuizu.databinding.FragmentJavaBinding;

import com.example.kuizu.model.QuizModel;


import java.util.ArrayList;
import java.util.Objects;


public class Java_Fragment extends Fragment {
    FragmentJavaBinding binding;
    ArrayList<QuizModel> list = new ArrayList<>();
    private int position = 0;
    int right = 0;
    private static String answer = null;
    int allQuestion;
    String listsize;
    String positionNo;
    QuizModel quizModel;


    public Java_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJavaBinding.inflate(getLayoutInflater());
        LoadQuestion();
        EnableOption();
        ClearOption();

        binding.nextBtn.setOnClickListener(v -> {

            position++;
            LoadQuestion();
            EnableOption();
            ClearOption();
            checkNext();
        });
        return binding.getRoot();
    }

    private void checkNext() {

        if(position == allQuestion){
            binding.nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    loadFragment2(new Result_Fragment());
                }

                private void loadFragment2(Fragment fragment) {

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.wrapper1, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
            list.clear();
            position = 0;

        }
    }

    private void ClearOption() {

        binding.Option1.setBackgroundResource(R.drawable.normal_color);
        binding.Option2.setBackgroundResource(R.drawable.normal_color);
        binding.Option3.setBackgroundResource(R.drawable.normal_color);
        binding.Option4.setBackgroundResource(R.drawable.normal_color);
        binding.nextBtn.setBackgroundResource(R.drawable.disable_color);
    }

    private void EnableOption() {

        binding.Option1.setEnabled(true);
        binding.Option2.setEnabled(true);
        binding.Option3.setEnabled(true);
        binding.Option4.setEnabled(true);
        binding.nextBtn.setEnabled(false);
    }

    @SuppressLint("SetTextI18n")
    private void LoadQuestion() {
        list.add(new QuizModel("Which of the following is a valid way to declare a Java array?","A: int arr = new int[5]","B: int arr[] = new int[5]","C: int[] arr = new int(5);","D:  arr int[] = new int[5];","B: int arr[] = new int[5]"));
        list.add(new QuizModel("Which of the following methods is used to get the length of a string in Java?","A: length()","B: size()","C: getSize()","D: getLength()","A: length()"));
        list.add(new QuizModel("Which of the following is not a valid Java keyword?","A: static","B: final","C: private","D: include","D: include"));
        list.add(new QuizModel("Which of the following loops will execute at least once?","A: for","B: while","C: do-while","D: foreach","C: do-while"));
        list.add(new QuizModel("Which keyword is used to define a class in Java?","A: function","B: define","C: class","D: struct","C: class"));
        list.add(new QuizModel("Which of the following is used to create an instance of a class in Java?","A: constructor","B: object","C: new","D: instance","C: new"));
        list.add(new QuizModel("Which of the following is a wrapper class for a primitive data type in Java?","A: Integer","B: int","C: Char","D: String","A: Integer"));
        list.add(new QuizModel("Which method must be implemented by a class implementing the Runnable interface?","A: run()","B: start()","C: execute()","D: main()","A: run()"));
        list.add(new QuizModel("Which of the following is a checked exception in Java?","A: ArrayIndexOutOfBoundsException","B: NullPointerException","C: IOException","D: ArithmeticException","C: IOException"));
        list.add(new QuizModel("What is the size of an int variable in Java?","A: 8 bits","B: 16 bits","C: 32 bits","D: 64 bits","C: 32 bits"));


        allQuestion = 10;
        listsize = String.valueOf(allQuestion);
        binding.totalQ.setText("/"+listsize);

        if(position != allQuestion){
            positionNo = String.valueOf(position+1);
            binding.Qnumber.setText(positionNo);
        }else {
            positionNo = String.valueOf(position);
            binding.Qnumber.setText(positionNo);
        }

        quizModel = list.get(position);
        answer = quizModel.getCorrect_ans();

        binding.questionCont.setText(quizModel.getQuestion());
        binding.Option1.setText(quizModel.getOp1());
        binding.Option2.setText(quizModel.getOp2());
        binding.Option3.setText(quizModel.getOp3());
        binding.Option4.setText(quizModel.getOp4());

        optionCheckUp();


    }

    private void optionCheckUp() {

        binding.Option1.setOnClickListener(v ->{

            if(Objects.equals(quizModel.getOp1(),quizModel.getCorrect_ans())){
                right++;
                binding.Option1.setBackgroundResource(R.drawable.right_color);
                binding.Option1.setTextColor(getContext().getColor(R.color.white));

            }else {
                ShowRightAns();
                binding.Option1.setBackgroundResource(R.drawable.wrong_color);
                binding.Option1.setTextColor(getContext().getColor(R.color.white));

            }   
            DisableOption();
            binding.nextBtn.setBackgroundResource(R.drawable.next);
        });

        binding.Option2.setOnClickListener(v ->{

            if(Objects.equals(quizModel.getOp2(),quizModel.getCorrect_ans())){
                right++;
                binding.Option2.setBackgroundResource(R.drawable.right_color);
                binding.Option2.setTextColor(getContext().getColor(R.color.white));

            }else {
                ShowRightAns();
                binding.Option2.setBackgroundResource(R.drawable.wrong_color);
                binding.Option2.setTextColor(getContext().getColor(R.color.white));

            }
            DisableOption();
            binding.nextBtn.setBackgroundResource(R.drawable.next);
        });

        binding.Option3.setOnClickListener(v ->{

            if(Objects.equals(quizModel.getOp3(),quizModel.getCorrect_ans())){
                right++;
                binding.Option3.setBackgroundResource(R.drawable.right_color);
                binding.Option3.setTextColor(getContext().getColor(R.color.white));

            }else {
                ShowRightAns();
                binding.Option3.setBackgroundResource(R.drawable.wrong_color);
                binding.Option3.setTextColor(getContext().getColor(R.color.white));

            }
            DisableOption();
            binding.nextBtn.setBackgroundResource(R.drawable.next);
        });

        binding.Option4.setOnClickListener(v ->{

            if(Objects.equals(quizModel.getOp4(),quizModel.getCorrect_ans())){
                right++;
                binding.Option4.setBackgroundResource(R.drawable.right_color);
                binding.Option4.setTextColor(getContext().getColor(R.color.white));

            }else {
                ShowRightAns();
                binding.Option4.setBackgroundResource(R.drawable.wrong_color);
                binding.Option4.setTextColor(getContext().getColor(R.color.white));

            }
            DisableOption();
            binding.nextBtn.setBackgroundResource(R.drawable.next);
        });
    }

    private void DisableOption() {

        binding.Option1.setEnabled(false);
        binding.Option2.setEnabled(false);
        binding.Option3.setEnabled(false);
        binding.Option4.setEnabled(false);
        binding.nextBtn.setEnabled(true);
    }

    private void ShowRightAns() {

        if(Objects.equals(quizModel.getOp1(),quizModel.getCorrect_ans())){

            binding.Option1.setBackgroundResource(R.drawable.right_color);
            binding.Option1.setTextColor(getContext().getColor(R.color.white));


        }else if(Objects.equals(quizModel.getOp2(),quizModel.getCorrect_ans())){

            binding.Option2.setBackgroundResource(R.drawable.right_color);
            binding.Option2.setTextColor(getContext().getColor(R.color.white));


        }else if(Objects.equals(quizModel.getOp3(),quizModel.getCorrect_ans())){

            binding.Option3.setBackgroundResource(R.drawable.right_color);
            binding.Option3.setTextColor(getContext().getColor(R.color.white));

        }else if(Objects.equals(quizModel.getOp4(),quizModel.getCorrect_ans())){

            binding.Option4.setBackgroundResource(R.drawable.right_color);
            binding.Option3.setTextColor(getContext().getColor(R.color.white));

        }



    }
}