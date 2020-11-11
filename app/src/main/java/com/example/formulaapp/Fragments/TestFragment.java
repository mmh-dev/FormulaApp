package com.example.formulaapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulaapp.Models.Question;
import com.example.formulaapp.Models.TestData;
import com.example.formulaapp.Models.User;
import com.example.formulaapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaibhavlakhera.circularprogressview.CircularProgressView;

import java.util.ArrayList;
import java.util.List;

public class TestFragment extends Fragment {
    CircularProgressView progressBar;
    TextView questions_passed, questions_total, points,question_text;
    Button variant1, variant2, variant3, variant4, next;
    String header;
    boolean isFinal;
    int totalPoints, totalQuestionsNumber, count, selectedVariant;
    List<Question> questionList = new ArrayList<>();
    User user;
    String chosenAnswer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setTotal(100);
        questions_passed = view.findViewById(R.id.questions_passed);
        questions_total = view.findViewById(R.id.questions_total);
        points = view.findViewById(R.id.points);
        question_text = view.findViewById(R.id.question_text);
        variant1 = view.findViewById(R.id.question_1_btn);
        variant2 = view.findViewById(R.id.question_2_btn);
        variant3 = view.findViewById(R.id.question_3_btn);
        variant4 = view.findViewById(R.id.question_4_btn);
        next = view.findViewById(R.id.next_question_btn);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            TestData testData = (TestData) bundle.getSerializable("testData");
            header = testData.getHeader();
            isFinal = testData.isFinal();
            totalPoints = testData.getPoints();
            totalQuestionsNumber = testData.getTotalQuestionsNumber();
            questionList = testData.getQuestionList();
            user = testData.getUser();
        }

        getActivity().setTitle(header);

        questions_passed.setText(String.valueOf(count+1));
        if (totalQuestionsNumber >= 20){
            questions_total.setText(String.valueOf(20));
        }else {
            questions_total.setText(String.valueOf(totalQuestionsNumber));
        }
        points.setText(String.valueOf(totalPoints));

        question_text.setText(questionList.get(0).getQuestionText());
        variant1.setText(questionList.get(0).getAnswer1());
        variant2.setText(questionList.get(0).getAnswer2());
        variant3.setText(questionList.get(0).getAnswer3());
        variant4.setText(questionList.get(0).getAnswer4());

        variant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variant1.setBackgroundResource(R.drawable.btn_yello_variant);
                variant2.setBackgroundResource(R.drawable.btn_blue_google);
                variant3.setBackgroundResource(R.drawable.btn_blue_google);
                variant4.setBackgroundResource(R.drawable.btn_blue_google);
                chosenAnswer = questionList.get(0).getAnswer1();
                selectedVariant = 1;
            }
        });

        variant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variant2.setBackgroundResource(R.drawable.btn_yello_variant);
                variant1.setBackgroundResource(R.drawable.btn_blue_google);
                variant3.setBackgroundResource(R.drawable.btn_blue_google);
                variant4.setBackgroundResource(R.drawable.btn_blue_google);
                chosenAnswer = questionList.get(0).getAnswer2();
                selectedVariant = 2;
            }
        });

        variant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variant3.setBackgroundResource(R.drawable.btn_yello_variant);
                variant1.setBackgroundResource(R.drawable.btn_blue_google);
                variant2.setBackgroundResource(R.drawable.btn_blue_google);
                variant4.setBackgroundResource(R.drawable.btn_blue_google);
                chosenAnswer = questionList.get(0).getAnswer3();
                selectedVariant = 3;
            }
        });
        variant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variant4.setBackgroundResource(R.drawable.btn_yello_variant);
                variant1.setBackgroundResource(R.drawable.btn_blue_google);
                variant2.setBackgroundResource(R.drawable.btn_blue_google);
                variant3.setBackgroundResource(R.drawable.btn_blue_google);
                chosenAnswer = questionList.get(0).getAnswer4();
                selectedVariant = 4;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosenAnswer.equals(questionList.get(0).getCorrectAnswer())){
                    switch (selectedVariant){
                        case 1:
                            variant1.setBackgroundResource(R.drawable.btn_green_variant);
                            break;
                        case 2:
                            variant2.setBackgroundResource(R.drawable.btn_green_variant);
                            break;
                        case 3:
                            variant3.setBackgroundResource(R.drawable.btn_green_variant);
                            break;
                        case 4:
                            variant4.setBackgroundResource(R.drawable.btn_green_variant);
                            break;
                    }
                    Toast.makeText(getActivity(), (getString(R.string.correct_answer) + "!"), Toast.LENGTH_LONG).show();
                }
                else {
                    switch (selectedVariant){
                        case 1:
                            variant1.setBackgroundResource(R.drawable.btn_red_variant);
                            break;
                        case 2:
                            variant2.setBackgroundResource(R.drawable.btn_red_variant);
                            break;
                        case 3:
                            variant3.setBackgroundResource(R.drawable.btn_red_variant);
                            break;
                        case 4:
                            variant4.setBackgroundResource(R.drawable.btn_red_variant);
                            break;
                    }
                    if (variant1.getText().toString().equals(questionList.get(0).getCorrectAnswer())){
                        variant1.setBackgroundResource(R.drawable.btn_green_variant);
                    }
                    else if (variant2.getText().toString().equals(questionList.get(0).getCorrectAnswer())){
                        variant2.setBackgroundResource(R.drawable.btn_green_variant);
                    }
                    else if (variant3.getText().toString().equals(questionList.get(0).getCorrectAnswer())){
                        variant3.setBackgroundResource(R.drawable.btn_green_variant);
                    }
                    else {
                        variant4.setBackgroundResource(R.drawable.btn_green_variant);
                    }
                    Toast.makeText(getActivity(), (getString(R.string.corrent_answer) + questionList.get(0).getCorrectAnswer() + "!"), Toast.LENGTH_LONG).show();
                }
            }
        });



        return view;
    }
}