package com.example.formulaapp.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class UserRatingFragment extends Fragment {

    HorizontalBarChart chart;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    List<User> userList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        chart = view.findViewById(R.id.chart);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Objects.requireNonNull(getActivity()).setTitle(getString(R.string.rating));
        userList.clear();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    userList.add(user);
                }
                String[] manCats = getResources().getStringArray(R.array.main_categories);
                List<String> catNames = new ArrayList<>(Arrays.asList(manCats));
                catNames.add(getString(R.string.final_test));
                BarDataSet dataSet1 = new BarDataSet(myPointsData(userList), "My points");
                BarDataSet dataSet2 = new BarDataSet(averagePointsData(userList), "Average Points");
                dataSet1.setStackLabels(new String[]{getString(R.string.my_points), getString(R.string.max_points_among_users)});
                dataSet1.setColors(Color.GREEN);
                dataSet2.setColors(Color.GRAY);
                BarData barData = new BarData();
                barData.addDataSet(dataSet1);
                barData.addDataSet(dataSet2);
                barData.setBarWidth(0.7f);
                chart.setData(barData);
                chart.setPinchZoom(false);
                chart.setFitBars(true);
                chart.setDrawValueAboveBar(false);
                XAxis xAxis = chart.getXAxis();
                chart.getDescription().setEnabled(false);
                xAxis.setValueFormatter(new IndexAxisValueFormatter(catNames));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                xAxis.setDrawAxisLine(false);
                xAxis.setXOffset(200f);
                chart.animateY(200);
                chart.invalidate();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }
    private List<BarEntry> myPointsData(List<User> userList) {
        float[] myPoints = new float[14];
        List<BarEntry> xVal = new ArrayList<>();
        for (User u : userList) {
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
                xVal.add(new BarEntry((float) i, myPoints[i]));
            }
        }
        return  xVal;
    }

    private List<BarEntry> averagePointsData(List<User> userList) {
        List<BarEntry> xVal = new ArrayList<>();
        List<Integer> dvigatel = new ArrayList<>();
        List<Integer> transmissiya = new ArrayList<>();
        List<Integer> podveska = new ArrayList<>();
        List<Integer> rul = new ArrayList<>();
        List<Integer> ohlazhdaniye = new ArrayList<>();
        List<Integer> zajiganiye = new ArrayList<>();
        List<Integer> toplivo = new ArrayList<>();
        List<Integer> tormoz = new ArrayList<>();
        List<Integer> electro = new ArrayList<>();
        List<Integer> datchiki = new ArrayList<>();
        List<Integer> kuzov = new ArrayList<>();
        List<Integer> salon = new ArrayList<>();
        List<Integer> masla = new ArrayList<>();
        List<Integer> totalP = new ArrayList<>();
        for (User u : userList) {
            dvigatel.add(u.getDvigatel());
            transmissiya.add(u.getTransmissiya());
            podveska.add(u.getPodveska());
            rul.add(u.getRul());
            ohlazhdaniye.add(u.getOhlazhdeniye());
            zajiganiye.add(u.getZajiganiye());
            toplivo.add(u.getToplivo());
            tormoz.add(u.getTormoz());
            electro.add(u.getElectro());
            datchiki.add(u.getDatchiki());
            kuzov.add(u.getKuzov());
            salon.add(u.getSalon());
            masla.add(u.getMasla());
            totalP.add(u.getTotalPoints());
        }

        for (int i = 0; i < 14; i++) {
            xVal.add(new BarEntry((float) i, findAverage(dvigatel)));
            xVal.add(new BarEntry((float) i, findAverage(transmissiya)));
            xVal.add(new BarEntry((float) i, findAverage(podveska)));
            xVal.add(new BarEntry((float) i, findAverage(rul)));
            xVal.add(new BarEntry((float) i, findAverage(ohlazhdaniye)));
            xVal.add(new BarEntry((float) i, findAverage(zajiganiye)));
            xVal.add(new BarEntry((float) i, findAverage(toplivo)));
            xVal.add(new BarEntry((float) i, findAverage(tormoz)));
            xVal.add(new BarEntry((float) i, findAverage(electro)));
            xVal.add(new BarEntry((float) i, findAverage(datchiki)));
            xVal.add(new BarEntry((float) i, findAverage(kuzov)));
            xVal.add(new BarEntry((float) i, findAverage(salon)));
            xVal.add(new BarEntry((float) i, findAverage(masla)));
            xVal.add(new BarEntry((float) i, findAverage(totalP)));
        }
        return  xVal;
    }
    private float findAverage (List<Integer> list){
        Integer sum = 0;
        if(!list.isEmpty()) {
            for (Integer x : list) {
                sum += x;
            }
            return sum.floatValue() / list.size();
        }
        return sum.floatValue();
    }
}