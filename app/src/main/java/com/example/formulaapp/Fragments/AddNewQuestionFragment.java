package com.example.formulaapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.formulaapp.Models.Question;
import com.example.formulaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class AddNewQuestionFragment extends Fragment {

    EditText question_body, answer1, answer2, answer3, answer4;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    Button save_question_btn, cancel_btn;
    Task<Void> reference;
    String header, answer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_question, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            header = bundle.getString("header");
        }
        getActivity().setTitle(header);

        question_body = view.findViewById(R.id.question_body);
        answer1 = view.findViewById(R.id.answer1);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);
        answer4 = view.findViewById(R.id.answer4);
        checkBox1 = view.findViewById(R.id.checkBox1);
        checkBox2 = view.findViewById(R.id.checkBox2);
        checkBox3 = view.findViewById(R.id.checkBox3);
        checkBox4 = view.findViewById(R.id.checkBox4);
        save_question_btn = view.findViewById(R.id.save_question_btn);
        cancel_btn = view.findViewById(R.id.cancel_btn);


        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox1.isChecked()){
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    answer = answer1.getText().toString();
                }
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox2.isChecked()){
                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    answer = answer2.getText().toString();
                }
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox3.isChecked()){
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox4.setChecked(false);
                    answer = answer3.getText().toString();
                }
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox4.isChecked()){
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    answer = answer4.getText().toString();
                }
            }
        });

        save_question_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionBody_txt = question_body.getText().toString();
                String answer1_txt = answer1.getText().toString();
                String answer2_txt = answer2.getText().toString();
                String answer3_txt = answer3.getText().toString();
                String answer4_txt = answer4.getText().toString();
                if (TextUtils.isEmpty(questionBody_txt) || TextUtils.isEmpty(answer1_txt) || TextUtils.isEmpty(answer2_txt)|| TextUtils.isEmpty(answer3_txt) || TextUtils.isEmpty(answer4_txt)){
                    Toast.makeText(getContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }
                else {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("category", header);
                    map.put("questionText", questionBody_txt);
                    map.put("answer1", answer1_txt);
                    map.put("answer2", answer2_txt);
                    map.put("answer3", answer3_txt);
                    map.put("answer4", answer4_txt);
                    map.put("correctAnswer", answer);
                    reference = FirebaseDatabase.getInstance().getReference().child("Questions").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getContext(), getString(R.string.question_is_saved), Toast.LENGTH_SHORT).show();
                                getFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left)
                                        .replace(R.id.fragment_container, new EditTestFragment()).commit();
                            }

                        }
                    });
                }
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, new EditTestFragment()).commit();
            }
        });

        return view;
    }
}