package com.example.formulaapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.formulaapp.R;


public class ArticleFragment extends Fragment {

    String header;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            header = bundle.getString("title");
        }
        getActivity().setTitle(header);

        return view;
}
}