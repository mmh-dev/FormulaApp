package com.example.formulaapp.Fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.Adapters.MainMenuAdapter;
import com.example.formulaapp.Models.MenuBullet;
import com.example.formulaapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainMenuFragment extends Fragment {

    List<MenuBullet> menuBulletList = new ArrayList<>();
    RecyclerView recyclerView;
    MainMenuAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        getActivity().setTitle(getString(R.string.main_menu));
        fillMenuList();

        adapter = new MainMenuAdapter(menuBulletList, getContext(), 0);
        recyclerView = view.findViewById(R.id.main_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainMenuAdapter.RecycleOnClickListener() {
            @Override
            public void onItemClick(int position) {
                SecondMenuFragment secondMenuFragment = new SecondMenuFragment();
                Bundle bundle = new Bundle();
                bundle.putString("header", menuBulletList.get(position).getHeader());
                secondMenuFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, secondMenuFragment).
                        addToBackStack("MainMenu").commit();
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });

        return view;
    }

    private void fillMenuList() {
        menuBulletList.clear();
        String[] manCats = getResources().getStringArray(R.array.main_categories);
        String[] descCats = getResources().getStringArray(R.array.main_categories_desc);

        menuBulletList.add(new MenuBullet(manCats[0], descCats[0], R.drawable.engine, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[1], descCats[1], R.drawable.gearbox, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[2], descCats[2], R.drawable.transmission, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[3], descCats[3], R.drawable.steering_wheel, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[4], descCats[4], R.drawable.cooling, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[5], descCats[5], R.drawable.igniting, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[6], descCats[6], R.drawable.fuel, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[7], descCats[7], R.drawable.brakes, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[8], descCats[8], R.drawable.electric, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[9], descCats[9], R.drawable.sensors, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[10], descCats[10], R.drawable.car_body, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[11], descCats[11], R.drawable.salon, null, 0, null));
        menuBulletList.add(new MenuBullet(manCats[12], descCats[12], R.drawable.oil, null, 0, null));
    }
}