package com.example.formulaapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.formulaapp.Models.User;
import com.example.formulaapp.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import jrizani.jrspinner.JRSpinner;

public class UserRatingFragment extends Fragment {

    SwitchCompat statusSwitch;
    JRSpinner spinner;
    Button save;
    BarChart barChart;
    DatabaseReference reference;
    List<User> userList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_rating, container, false);
        statusSwitch = view.findViewById(R.id.statusSwitch);
        spinner = view.findViewById(R.id.cat_spinner);
        save = view.findViewById(R.id.save_btn_user_rating);
        barChart = view.findViewById(R.id.barChart);
        getActivity().setTitle(getString(R.string.users_rating));

        reference = FirebaseDatabase.getInstance().getReference("Users");
        userList.clear();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    userList.add(user);
                }
                createBarChart(userList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        return view;
    }

    private void createBarChart(List<User> userList) {
        List<String> xAxisLabels = new ArrayList();
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i <userList.size() ; i++) {
            barEntries.add(new BarEntry(i, userList.get(i).getAllPoints()));
            xAxisLabels.add(userList.get(i).getUsername());
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
        barDataSet.setValueTextSize(14f);
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int)value);
            }
        };
        barDataSet.setValueFormatter(formatter);
        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));
        xAxis.setCenterAxisLabels(false);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(10f);
        xAxis.setGranularityEnabled(true);

        Legend l = barChart.getLegend();
        l.setEnabled(false);

        barChart.getDescription().setEnabled(false);
        barChart.setVisibleXRangeMaximum(4);

        barChart.animateXY(1000, 500);
        barChart.invalidate();
    }
}