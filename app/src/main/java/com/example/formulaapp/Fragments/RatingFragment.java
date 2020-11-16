package com.example.formulaapp.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.formulaapp.Models.MenuBullet;
import com.example.formulaapp.Models.User;
import com.example.formulaapp.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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


public class RatingFragment extends Fragment {

    HorizontalBarChart chart;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    List<User> userList = new ArrayList<>();
    boolean isManager;
    int x = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        chart = view.findViewById(R.id.chart);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            isManager = bundle.getBoolean("isManager");
        }
        String header = (isManager) ? getString(R.string.users_rating) : getString(R.string.rating);
        Objects.requireNonNull(getActivity()).setTitle(header);
        userList.clear();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    userList.add(user);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Log.i("tag", String.valueOf(x));
        Log.i("size", String.valueOf(userList.size()));
//        Log.i("tag", String.valueOf(userList.get(0).getDvigatel()));
//        Log.i("tag", String.valueOf(userList.get(1).getDvigatel()));
//        Log.i("tag", String.valueOf(userList.get(2).getDvigatel()));

        int[] barColorArray = new int[]{Color.BLUE, Color.RED};
        String[] manCats = getResources().getStringArray(R.array.main_categories);
        List<String> catNames = new ArrayList<>(Arrays.asList(manCats));
        BarDataSet dataSet = new BarDataSet(fillDataValues(userList), getString(R.string.сategories));
        dataSet.setColors(barColorArray);
        BarData barData = new BarData(dataSet);
        chart.setData(barData);
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(catNames));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(catNames.size());
        xAxis.setLabelRotationAngle(90);
        chart.animateX(1000);
        chart.invalidate();
        return view;
    }

    private List<BarEntry> fillDataValues(List<User> userList) {
        float[] myPoints = new float[14];
        float[] maxPoints = new float[14];
        List<BarEntry> yVal = new ArrayList<>();
        for (User u : userList) {
            maxPoints[0] = Math.max((float) u.getDvigatel(), maxPoints[0]);
            maxPoints[1] = Math.max((float) u.getTransmissiya(), maxPoints[0]);
            maxPoints[2] = Math.max((float) u.getPodveska(), maxPoints[0]);
            maxPoints[3] = Math.max((float) u.getRul(), maxPoints[0]);
            maxPoints[4] = Math.max((float) u.getOhlazhdeniye(), maxPoints[0]);
            maxPoints[5] = Math.max((float) u.getZajiganiye(), maxPoints[0]);
            maxPoints[6] = Math.max((float) u.getToplivo(), maxPoints[0]);
            maxPoints[7] = Math.max((float) u.getTormoz(), maxPoints[0]);
            maxPoints[8] = Math.max((float) u.getElectro(), maxPoints[0]);
            maxPoints[9] = Math.max((float) u.getDatchiki(), maxPoints[0]);
            maxPoints[10] = Math.max((float) u.getKuzov(), maxPoints[0]);
            maxPoints[11] = Math.max((float) u.getSalon(), maxPoints[0]);
            maxPoints[12] = Math.max((float) u.getMasla(), maxPoints[0]);
            maxPoints[13] = Math.max((float) u.getTotalPoints(), maxPoints[0]);
            if (u.getId().equals(firebaseUser.getUid())) {
                myPoints[0] = ((float) u.getDvigatel());
                myPoints[1] = ((float) u.getTransmissiya());
                myPoints[2] = ((float) u.getPodveska());
                myPoints[3] = ((float) u.getRul());
                myPoints[4] = ((float) u.getOhlazhdeniye());
                myPoints[5] = ((float) u.getZajiganiye());
                myPoints[6] = ((float) u.getToplivo());
                myPoints[7] = ((float) u.getTormoz());
                myPoints[8] = ((float) u.getElectro());
                myPoints[9] = ((float) u.getDatchiki());
                myPoints[10] = ((float) u.getKuzov());
                myPoints[11] = ((float) u.getSalon());
                myPoints[12] = ((float) u.getMasla());
                myPoints[13] = ((float) u.getTotalPoints());
            }
            for (int i = 0; i < 14; i++) {
                yVal.add(new BarEntry((float) i+1, new float[]{myPoints[i], (maxPoints[i] - myPoints[i])}));
            }
        }
        return  yVal;
    }
}