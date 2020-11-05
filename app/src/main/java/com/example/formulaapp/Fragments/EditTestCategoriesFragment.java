package com.example.formulaapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.formulaapp.Adapters.MainMenuAdapter;
import com.example.formulaapp.Models.MenuBullet;
import com.example.formulaapp.Models.Question;
import com.example.formulaapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditTestCategoriesFragment extends Fragment {

    List<MenuBullet> menuBulletList = new ArrayList<>();
    RecyclerView recyclerView;
    MainMenuAdapter adapter;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        getActivity().setTitle(getString(R.string.edit_test));
        fillMenuList();

        adapter = new MainMenuAdapter(menuBulletList, getContext(), 1);
        recyclerView = view.findViewById(R.id.main_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainMenuAdapter.RecycleOnClickListener() {
            @Override
            public void onItemClick(int position) {
                EditTestFragment editTestFragment = new EditTestFragment();
                Bundle bundle = new Bundle();
                bundle.putString("header", menuBulletList.get(position).getHeader());
                editTestFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, editTestFragment).
                        addToBackStack("EditTestMainMenu").commit();
            }
        });
        return view;
    }

    private void fillMenuList() {
        menuBulletList.clear();
        String[] manCats = getResources().getStringArray(R.array.main_categories);
        String[] descCats = getResources().getStringArray(R.array.main_categories_desc);
        int[] count = new int[13];
        menuBulletList.add(new MenuBullet(manCats[0], descCats[0], R.drawable.engine, count[0]));
        menuBulletList.add(new MenuBullet(manCats[1], descCats[1], R.drawable.gearbox, count[1]));
        menuBulletList.add(new MenuBullet(manCats[2], descCats[2], R.drawable.transmission, count[2]));
        menuBulletList.add(new MenuBullet(manCats[3], descCats[3], R.drawable.steering_wheel, count[3]));
        menuBulletList.add(new MenuBullet(manCats[4], descCats[4], R.drawable.cooling, count[4]));
        menuBulletList.add(new MenuBullet(manCats[5], descCats[5], R.drawable.igniting, count[5]));
        menuBulletList.add(new MenuBullet(manCats[6], descCats[6], R.drawable.fuel,count[6]));
        menuBulletList.add(new MenuBullet(manCats[7], descCats[7], R.drawable.brakes, count[7]));
        menuBulletList.add(new MenuBullet(manCats[8], descCats[8], R.drawable.electric, count[8]));
        menuBulletList.add(new MenuBullet(manCats[9], descCats[9], R.drawable.sensors, count[9]));
        menuBulletList.add(new MenuBullet(manCats[10], descCats[10], R.drawable.car_body, count[10]));
        menuBulletList.add(new MenuBullet(manCats[11], descCats[11], R.drawable.salon, count[11]));
        menuBulletList.add(new MenuBullet(manCats[12], descCats[12], R.drawable.oil, count[12]));

        reference = FirebaseDatabase.getInstance().getReference("Questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Question question = dataSnapshot.getValue(Question.class);
                    if (question.getCategory().equals("Двигатель")) {
                        count[0]++;
                    }else if (question.getCategory().equals("Трансмиссия")) {
                        count[1]++;
                    }else  if (question.getCategory().equals("Подвеска")) {
                        count[2]++;
                    }else  if (question.getCategory().equals("Рулевое управление")) {
                        count[3]++;
                    }else  if (question.getCategory().equals("Система охлаждения")) {
                        count[4]++;
                    }else  if (question.getCategory().equals("Система зажигания")) {
                        count[5]++;
                    } else if (question.getCategory().equals("Топливная система")) {
                        count[6]++;
                    }else  if (question.getCategory().equals("Тормозная система")) {
                        count[7]++;
                    }else  if (question.getCategory().equals("Электрооборудование")) {
                        count[8]++;
                    }else  if (question.getCategory().equals("Датчики")) {
                        count[9]++;
                    }else  if (question.getCategory().equals("Кузов")) {
                        count[10]++;
                    }else  if (question.getCategory().equals("Салон")) {
                        count[11]++;
                    } else {
                        count[12]++;
                    }
                    }
                menuBulletList.get(0).setQuestions(count[0]);
                menuBulletList.get(1).setQuestions(count[1]);
                menuBulletList.get(2).setQuestions(count[2]);
                menuBulletList.get(3).setQuestions(count[3]);
                menuBulletList.get(4).setQuestions(count[4]);
                menuBulletList.get(5).setQuestions(count[5]);
                menuBulletList.get(6).setQuestions(count[6]);
                menuBulletList.get(7).setQuestions(count[7]);
                menuBulletList.get(8).setQuestions(count[8]);
                menuBulletList.get(9).setQuestions(count[9]);
                menuBulletList.get(10).setQuestions(count[10]);
                menuBulletList.get(11).setQuestions(count[11]);
                menuBulletList.get(12).setQuestions(count[12]);

                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}