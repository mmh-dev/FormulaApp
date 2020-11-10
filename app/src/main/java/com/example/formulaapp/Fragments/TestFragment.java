package com.example.formulaapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.formulaapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaibhavlakhera.circularprogressview.CircularProgressView;

public class TestFragment extends Fragment {
    CircularProgressView progressBar;
    TextView questions_passed, questions_total, points,question_text;
    Button variant1, variant2, variant3, variant4, next;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    int categoryAndIsFinal, questions;



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
        questions_passed = view.findViewById(R.id.questions_passed);
        variant1 = view.findViewById(R.id.question_1_btn);
        variant2 = view.findViewById(R.id.question_2_btn);
        variant3 = view.findViewById(R.id.question_3_btn);
        variant4 = view.findViewById(R.id.question_4_btn);
        next = view.findViewById(R.id.next_question_btn);
        reference = FirebaseDatabase.getInstance().getReference("Questions");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Bundle bundle1 = new Bundle();
            Bundle bundle2 = new Bundle();
            bundle1 = bundle.getBundle("categoryAndIsFinal");
            bundle2 = bundle.getBundle("questions");
            categoryAndIsFinal = bundle1.getInt("categoryAndIsFinal");
            questions = bundle2.getInt("questions");
        }

        return view;
    }
}