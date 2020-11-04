package com.example.formulaapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.formulaapp.R;
import com.vaibhavlakhera.circularprogressview.CircularProgressView;

public class TestFragment extends Fragment {
    CircularProgressView progressBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setTotal(100);
        progressBar.setProgress(70, false);

        return view;
    }
}