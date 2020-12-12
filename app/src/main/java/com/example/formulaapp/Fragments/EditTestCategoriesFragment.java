package com.example.formulaapp.Fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Objects;

public class EditTestCategoriesFragment extends Fragment {

    List<MenuBullet> menuBulletList = new ArrayList<>();
    RecyclerView recyclerView;
    MainMenuAdapter adapter;
    DatabaseReference reference;

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
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        getActivity().setTitle(getString(R.string.edit_test));
        reference = FirebaseDatabase.getInstance().getReference("Questions");
        recyclerView = view.findViewById(R.id.main_menu);
        menuBulletList.clear();
        String[] manCats = getResources().getStringArray(R.array.main_categories);
        String[] descCats = getResources().getStringArray(R.array.main_categories_desc);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int[] count = new int[13];
                menuBulletList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question question = dataSnapshot.getValue(Question.class);
                    if (question.getCategory() != null){
                        switch (Objects.requireNonNull(question).getCategory()) {
                            case "Двигатель":
                                count[0]++;
                                break;
                            case "Трансмиссия":
                                count[1]++;
                                break;
                            case "Подвеска":
                                count[2]++;
                                break;
                            case "Рулевое управление":
                                count[3]++;
                                break;
                            case "Система охлаждения":
                                count[4]++;
                                break;
                            case "Система зажигания":
                                count[5]++;
                                break;
                            case "Топливная система":
                                count[6]++;
                                break;
                            case "Тормозная система":
                                count[7]++;
                                break;
                            case "Электрооборудование":
                                count[8]++;
                                break;
                            case "Датчики":
                                count[9]++;
                                break;
                            case "Кузов":
                                count[10]++;
                                break;
                            case "Салон":
                                count[11]++;
                                break;
                            default:
                                count[12]++;
                                break;
                        }
                    }

                }
                menuBulletList.add(new MenuBullet(manCats[0], descCats[0], R.drawable.engine, null, count[0], null));
                menuBulletList.add(new MenuBullet(manCats[1], descCats[1], R.drawable.gearbox, null, count[1], null));
                menuBulletList.add(new MenuBullet(manCats[2], descCats[2], R.drawable.transmission, null, count[2], null));
                menuBulletList.add(new MenuBullet(manCats[3], descCats[3], R.drawable.steering_wheel, null, count[3], null));
                menuBulletList.add(new MenuBullet(manCats[4], descCats[4], R.drawable.cooling, null, count[4], null));
                menuBulletList.add(new MenuBullet(manCats[5], descCats[5], R.drawable.igniting, null, count[5], null));
                menuBulletList.add(new MenuBullet(manCats[6], descCats[6], R.drawable.fuel, null, count[6], null));
                menuBulletList.add(new MenuBullet(manCats[7], descCats[7], R.drawable.brakes, null, count[7], null));
                menuBulletList.add(new MenuBullet(manCats[8], descCats[8], R.drawable.electric, null, count[8], null));
                menuBulletList.add(new MenuBullet(manCats[9], descCats[9], R.drawable.sensors, null, count[9], null));
                menuBulletList.add(new MenuBullet(manCats[10], descCats[10], R.drawable.car_body, null, count[10], null));
                menuBulletList.add(new MenuBullet(manCats[11], descCats[11], R.drawable.salon, null, count[11], null));
                menuBulletList.add(new MenuBullet(manCats[12], descCats[12], R.drawable.oil, null, count[12], null));

                adapter = new MainMenuAdapter(menuBulletList, getContext(), 1);
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
                                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
                                .replace(R.id.fragment_container, editTestFragment).
                                addToBackStack("EditTestMainMenu").commit();
                    }

                    @Override
                    public void onDeleteClick(int position) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return view;
    }
}