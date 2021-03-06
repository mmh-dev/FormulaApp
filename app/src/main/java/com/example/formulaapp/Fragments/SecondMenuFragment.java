package com.example.formulaapp.Fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.Adapters.PagesAdapter;
import com.example.formulaapp.Models.Question;
import com.example.formulaapp.Models.TestData;
import com.example.formulaapp.Models.User;
import com.example.formulaapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SecondMenuFragment extends Fragment {

    PagesAdapter adapter;
    List<String> pagesList = new ArrayList<>();
    RecyclerView recyclerView;
    Button start_test_btn;
    String header;
    FirebaseUser firebaseUser;
    DatabaseReference referenceUser, referenceQuestion;
    User user;
    List<Question> questionList = new ArrayList<>();
    double totalQuestionsNumber = 0;
    ValueEventListener listener;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();

        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left)
                                .replace(R.id.fragment_container, new MainMenuFragment()).commit();
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
        View view = inflater.inflate(R.layout.fragment_second_menu, container, false);
        recyclerView = view.findViewById(R.id.second_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        start_test_btn = view.findViewById(R.id.start_test_btn);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            header = bundle.getString("header");
        }
        getActivity().setTitle(header);
        fillList(header);

        adapter = new PagesAdapter(pagesList, 2, getContext());
        recyclerView.setAdapter(adapter);

        referenceUser = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        listener = referenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        referenceQuestion = FirebaseDatabase.getInstance().getReference("Questions");
        listener = referenceQuestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question q = dataSnapshot.getValue(Question.class);
                    assert q != null;
                    if (q.getCategory() != null && q.getCategory().equals(header)) {
                        questionList.add(q);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Collections.shuffle(questionList);

        start_test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionList.size() == 0){
                    Toast.makeText(getActivity(), R.string.no_test_questions, Toast.LENGTH_SHORT).show();
                }
                else {
                    TestFragment testFragment = new TestFragment();
                    if (questionList.size() >= 20) {
                        totalQuestionsNumber = 20;
                    } else {
                        totalQuestionsNumber = questionList.size();
                    }
                    TestData testData = new TestData(header, false, 0, totalQuestionsNumber, questionList, user, 0);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("testData", testData);
                    testFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left)
                            .replace(R.id.fragment_container, testFragment).
                            addToBackStack("SecondMenu").commit();
                }
            }
        });

        adapter.setOnItemClickListener(new PagesAdapter.RecycleOnClickListener() {
            @Override
            public void onItemClick(int position) {
                ArticleFragment articleFragment = new ArticleFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", pagesList.get(position));
                articleFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, articleFragment).
                        addToBackStack("SecondMenu").commit();
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });
        return view;
    }

    private void fillList(String header) {
        pagesList.clear();
        switch (header) {
            case "Двигатель":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.engine));
                break;
            case "Трансмиссия":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.gearbox));
                break;
            case "Подвеска":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.transmission));
                break;
            case "Рулевое управление":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.steering_wheel));
                break;
            case "Система охлаждения":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.cooling));
                break;
            case "Система зажигания":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.igniting));
                break;
            case "Топливная система":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.fuel));
                break;
            case "Тормозная система":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.brakes));
                break;
            case "Электрооборудование":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.electric));
                break;
            case "Датчики":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.sensors));
                break;
            case "Кузов":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.car_body));
                break;
            case "Салон":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.salon));
                break;
            case "Масла":
                Collections.addAll(pagesList, getResources().getStringArray(R.array.oil));
                break;
        }
    }
}