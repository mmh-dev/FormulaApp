package com.example.formulaapp.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.formulaapp.Adapters.PagesAdapter;
import com.example.formulaapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SavedPagesListFragment extends Fragment {

    PagesAdapter adapter;
    List<String> offlinePagesList = new ArrayList<>();
    String loadedJson;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_pages_list, container, false);

        offlinePagesList.clear();
        recyclerView = view.findViewById(R.id.saved_pages_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getActivity().setTitle(R.string.saved_pages);

        getContext();
        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        loadedJson = pref.getString("key", ""); // getting String
        Type type = new TypeToken<List<String>>(){}.getType();
        if (offlinePagesList != null){
            offlinePagesList.addAll(new Gson().fromJson(loadedJson, type));
        }

        adapter = new PagesAdapter(offlinePagesList, 2, getContext());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PagesAdapter.RecycleOnClickListener() {
            @Override
            public void onItemClick(int position) {
                SavedPagesFragment savedPagesFragment = new SavedPagesFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", offlinePagesList.get(position));
                savedPagesFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, savedPagesFragment).commit();
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });

        return view;
    }
}