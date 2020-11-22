package com.example.formulaapp.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.formulaapp.Models.User;
import com.example.formulaapp.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jrizani.jrspinner.JRSpinner;

public class UserRatingFragment extends Fragment {

    SwitchCompat statusSwitch;
    JRSpinner spinner;
    Button save;
    RecyclerView userListView;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    List<User> userList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_rating, container, false);
        statusSwitch = view.findViewById(R.id.statusSwitch);
        spinner = view.findViewById(R.id.cat_spinner);
        save = view.findViewById(R.id.save_btn_user_rating);
        userListView = view.findViewById(R.id.user_list_view);


        return view;
    }
}