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
    int[] count = new int[13];

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
        reference = FirebaseDatabase.getInstance().getReference("Questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Question question = dataSnapshot.getValue(Question.class);
                    if (question != null) {
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
                }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        menuBulletList.add(new MenuBullet(manCats[0], descCats[0], R.drawable.engine, null, count[0]));
        menuBulletList.add(new MenuBullet(manCats[1], descCats[1], R.drawable.gearbox, null, count[1]));
        menuBulletList.add(new MenuBullet(manCats[2], descCats[2], R.drawable.transmission, null, count[2]));
        menuBulletList.add(new MenuBullet(manCats[3], descCats[3], R.drawable.steering_wheel, null, count[3]));
        menuBulletList.add(new MenuBullet(manCats[4], descCats[4], R.drawable.cooling, null, count[4]));
        menuBulletList.add(new MenuBullet(manCats[5], descCats[5], R.drawable.igniting, null, count[5]));
        menuBulletList.add(new MenuBullet(manCats[6], descCats[6], R.drawable.fuel, null, count[6]));
        menuBulletList.add(new MenuBullet(manCats[7], descCats[7], R.drawable.brakes, null, count[7]));
        menuBulletList.add(new MenuBullet(manCats[8], descCats[8], R.drawable.electric, null, count[8]));
        menuBulletList.add(new MenuBullet(manCats[9], descCats[9], R.drawable.sensors, null, count[9]));
        menuBulletList.add(new MenuBullet(manCats[10], descCats[10], R.drawable.car_body, null, count[10]));
        menuBulletList.add(new MenuBullet(manCats[11], descCats[11], R.drawable.salon, null, count[11]));
        menuBulletList.add(new MenuBullet(manCats[12], descCats[12], R.drawable.oil, null, count[12]));
    }
}