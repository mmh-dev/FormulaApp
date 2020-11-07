package com.example.formulaapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.formulaapp.Adapters.PagesAdapter;
import com.example.formulaapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SecondMenuFragment extends Fragment {

    PagesAdapter adapter;
    List<String> pagesList = new ArrayList<>();
    RecyclerView recyclerView;
    Button start_test_btn;
    String header;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_menu, container, false);
        recyclerView = view.findViewById(R.id.second_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        start_test_btn = view.findViewById(R.id.start_test_btn);
        

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            header = bundle.getString("header");
        }
        getActivity().setTitle(header);
        fillList(header);

        adapter = new PagesAdapter(pagesList, 2, getContext());
        recyclerView.setAdapter(adapter);

        start_test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestFragment testFragment = new TestFragment();
                Bundle bundle = new Bundle();
                bundle.putString("category", header);
                testFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, testFragment).
                        addToBackStack("SecondMenu").commit();
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
        switch (header){
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