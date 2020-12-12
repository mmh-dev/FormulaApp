package com.example.formulaapp.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaapp.Adapters.PagesAdapter;
import com.example.formulaapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SavedPagesListFragment extends Fragment {

    PagesAdapter adapter;
    List<String> offlinePagesList = new ArrayList<>();
    String loadedJson;
    RecyclerView recyclerView;
    Button clearCache;

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
        View view = inflater.inflate(R.layout.fragment_saved_pages_list, container, false);

        offlinePagesList.clear();
        recyclerView = view.findViewById(R.id.saved_pages_view);
        clearCache = view.findViewById(R.id.clear_cache);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getActivity().setTitle(R.string.saved_pages);

        getFragmentManager().popBackStack("saved_list", FragmentManager.POP_BACK_STACK_INCLUSIVE);


        getContext();
        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        loadedJson = pref.getString("key", ""); // getting String
        Type type = new TypeToken<List<String>>(){}.getType();
        if (!loadedJson.equals("")) {
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
                        .addToBackStack("saved_list")
                        .replace(R.id.fragment_container, savedPagesFragment)
                        .addToBackStack("saved_list")
                        .commit();
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });

        clearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File dir = getActivity().getCacheDir();
                    deleteDir(dir);
                    SharedPreferences pref = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.clear().apply();
                    offlinePagesList.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), R.string.cleared, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });

        return view;
    }
    public boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}