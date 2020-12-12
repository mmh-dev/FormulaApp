package com.example.formulaapp.Fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.formulaapp.Models.Question;
import com.example.formulaapp.Models.TestData;
import com.example.formulaapp.Models.User;
import com.example.formulaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaibhavlakhera.circularprogressview.CircularProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestFragment extends Fragment {
    CircularProgressView progressBar;
    TextView questions_passed, questions_total, points, question_text;
    TextView variant1, variant2, variant3, variant4;
    Button next, submit;
    String header;
    boolean isFinal;
    double totalPoints, totalQuestionsNumber;
    int count, selectedVariant;
    List<Question> questionList = new ArrayList<>();
    List<Question> rawQuestionList = new ArrayList<>();
    User user;
    String chosenAnswer;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();

        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        Fragment fragment;
                        if (isFinal){
                            fragment = new MainMenuFragment();
                        }
                        else {
                            fragment = new SecondMenuFragment();
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("header", header);
                        fragment.setArguments(bundle);
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
                                .replace(R.id.fragment_container, fragment).
                                addToBackStack("MainMenu").commit();
                        return true;
                    }
                }
                return false;
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

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
        submit = view.findViewById(R.id.submit_btn);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            TestData testData = (TestData) bundle.getSerializable("testData");
            header = testData.getHeader();
            isFinal = testData.isFinal();
            totalPoints = testData.getPoints();
            totalQuestionsNumber = testData.getTotalQuestionsNumber();
            rawQuestionList = testData.getQuestionList();
            user = testData.getUser();
            count = testData.getCount();
            count++;
        }

        getActivity().setTitle(header);
        questionList.addAll(rawQuestionList);

        questions_passed.setText(String.valueOf(count));
        questions_total.setText(String.valueOf((int) totalQuestionsNumber));
        points.setText(String.valueOf((int) totalPoints));
        progressBar.setProgress(((int) (totalPoints / totalQuestionsNumber * 100)), true);
        question_text.setText(questionList.get(0).getQuestionText());
        variant1.setText(questionList.get(0).getAnswer1());
        variant2.setText(questionList.get(0).getAnswer2());
        variant3.setText(questionList.get(0).getAnswer3());
        variant4.setText(questionList.get(0).getAnswer4());

        if (count >= totalQuestionsNumber) {
            next.setVisibility(View.GONE);
            submit.setVisibility(View.VISIBLE);
        }

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
                if (chosenAnswer.equals(questionList.get(0).getCorrectAnswer())) {
                    switch (selectedVariant) {
                        case 1:
                            variant1.setBackgroundResource(R.drawable.btn_green_variant);
                            totalPoints++;
                            break;
                        case 2:
                            variant2.setBackgroundResource(R.drawable.btn_green_variant);
                            totalPoints++;
                            break;
                        case 3:
                            variant3.setBackgroundResource(R.drawable.btn_green_variant);
                            totalPoints++;
                            break;
                        case 4:
                            variant4.setBackgroundResource(R.drawable.btn_green_variant);
                            totalPoints++;
                            break;
                    }
                    if (isFinal) {
                        user.setTotalPoints((int) totalPoints);
                    } else {
                        switch (questionList.get(0).getCategory()) {
                            case "Двигатель":
                                user.setDvigatel((int) totalPoints);
                                break;
                            case "Трансмиссия":
                                user.setTransmissiya((int) totalPoints);
                                break;
                            case "Подвеска":
                                user.setPodveska((int) totalPoints);
                                break;
                            case "Рулевое управление":
                                user.setRul((int) totalPoints);
                                break;
                            case "Система охлаждения":
                                user.setOhlazhdeniye((int) totalPoints);
                                break;
                            case "Система зажигания":
                                user.setZajiganiye((int) totalPoints);
                                break;
                            case "Топливная система":
                                user.setToplivo((int) totalPoints);
                                break;
                            case "Тормозная система":
                                user.setTormoz((int) totalPoints);
                                break;
                            case "Электрооборудование":
                                user.setElectro((int) totalPoints);
                                break;
                            case "Датчики":
                                user.setDatchiki((int) totalPoints);
                                break;
                            case "Кузов":
                                user.setKuzov((int) totalPoints);
                                break;
                            case "Салон":
                                user.setSalon((int) totalPoints);
                                break;
                            case "Масла":
                                user.setMasla((int) totalPoints);
                                break;
                        }
                    }
                    progressBar.setProgress(((int) (totalPoints / totalQuestionsNumber * 100)), true);
                    points.setText(String.valueOf((int) totalPoints));
                    Toast.makeText(getActivity(), (getString(R.string.correct_answer) + "!"), Toast.LENGTH_LONG).show();
                } else {
                    switch (selectedVariant) {
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
                    if (variant1.getText().toString().equals(questionList.get(0).getCorrectAnswer())) {
                        variant1.setBackgroundResource(R.drawable.btn_green_variant);
                    } else if (variant2.getText().toString().equals(questionList.get(0).getCorrectAnswer())) {
                        variant2.setBackgroundResource(R.drawable.btn_green_variant);
                    } else if (variant3.getText().toString().equals(questionList.get(0).getCorrectAnswer())) {
                        variant3.setBackgroundResource(R.drawable.btn_green_variant);
                    } else {
                        variant4.setBackgroundResource(R.drawable.btn_green_variant);
                    }
                    Toast.makeText(getActivity(), (getString(R.string.corrent_answer) + questionList.get(0).getCorrectAnswer() + "!"), Toast.LENGTH_LONG).show();
                }
                questionList.remove(0);
                TestFragment testFragment = new TestFragment();
                TestData testData = new TestData(header, isFinal, totalPoints, totalQuestionsNumber, questionList, user, count);
                Bundle bundle = new Bundle();
                bundle.putSerializable("testData", testData);
                testFragment.setArguments(bundle);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left)
                                .replace(R.id.fragment_container, testFragment).commit();
                    }
                }, 1000);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosenAnswer.equals(questionList.get(0).getCorrectAnswer())) {
                    switch (selectedVariant) {
                        case 1:
                            variant1.setBackgroundResource(R.drawable.btn_green_variant);
                            totalPoints++;
                            break;
                        case 2:
                            variant2.setBackgroundResource(R.drawable.btn_green_variant);
                            totalPoints++;
                            break;
                        case 3:
                            variant3.setBackgroundResource(R.drawable.btn_green_variant);
                            totalPoints++;
                            break;
                        case 4:
                            variant4.setBackgroundResource(R.drawable.btn_green_variant);
                            totalPoints++;
                            break;
                    }
                    if (isFinal) {
                        user.setTotalPoints((int) totalPoints);
                    } else {
                        switch (questionList.get(0).getCategory()) {
                            case "Двигатель":
                                user.setDvigatel((int) totalPoints);
                                break;
                            case "Трансмиссия":
                                user.setTransmissiya((int) totalPoints);
                                break;
                            case "Подвеска":
                                user.setPodveska((int) totalPoints);
                                break;
                            case "Рулевое управление":
                                user.setRul((int) totalPoints);
                                break;
                            case "Система охлаждения":
                                user.setOhlazhdeniye((int) totalPoints);
                                break;
                            case "Система зажигания":
                                user.setZajiganiye((int) totalPoints);
                                break;
                            case "Топливная система":
                                user.setToplivo((int) totalPoints);
                                break;
                            case "Тормозная система":
                                user.setTormoz((int) totalPoints);
                                break;
                            case "Электрооборудование":
                                user.setElectro((int) totalPoints);
                                break;
                            case "Датчики":
                                user.setDatchiki((int) totalPoints);
                                break;
                            case "Кузов":
                                user.setKuzov((int) totalPoints);
                                break;
                            case "Салон":
                                user.setSalon((int) totalPoints);
                                break;
                            case "Масла":
                                user.setMasla((int) totalPoints);
                                break;
                        }
                    }
                    progressBar.setProgress(((int) (totalPoints / totalQuestionsNumber * 100)), true);
                    points.setText(String.valueOf((int) totalPoints));
                    Toast.makeText(getActivity(), (getString(R.string.correct_answer) + "!"), Toast.LENGTH_LONG).show();
                } else {
                    switch (selectedVariant) {
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
                    if (variant1.getText().toString().equals(questionList.get(0).getCorrectAnswer())) {
                        variant1.setBackgroundResource(R.drawable.btn_green_variant);
                    } else if (variant2.getText().toString().equals(questionList.get(0).getCorrectAnswer())) {
                        variant2.setBackgroundResource(R.drawable.btn_green_variant);
                    } else if (variant3.getText().toString().equals(questionList.get(0).getCorrectAnswer())) {
                        variant3.setBackgroundResource(R.drawable.btn_green_variant);
                    } else {
                        variant4.setBackgroundResource(R.drawable.btn_green_variant);
                    }
                    Toast.makeText(getActivity(), (getString(R.string.corrent_answer) + questionList.get(0).getCorrectAnswer() + "!"), Toast.LENGTH_LONG).show();
                }
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User u = snapshot.getValue(User.class);
                        if (u != null) {
                            reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), getString(R.string.data_saved_on_server), Toast.LENGTH_SHORT).show();
                                        new Timer().schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                getFragmentManager().beginTransaction()
                                                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left)
                                                        .replace(R.id.fragment_container, new MainMenuFragment()).commit();
                                            }
                                        }, 1000);
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        return view;
    }
}