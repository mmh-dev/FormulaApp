package com.example.formulaapp.Fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.formulaapp.Models.MenuBullet;
import com.example.formulaapp.Models.User;
import com.example.formulaapp.MyMarkerView;
import com.example.formulaapp.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
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
import java.util.OptionalDouble;


public class RatingFragment extends Fragment {

    PieChart chart;
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
                chart.setUsePercentValues(false);
                chart.getDescription().setEnabled(false);
                chart.setExtraOffsets(5, 10, 5, 5);
                chart.setDragDecelerationFrictionCoef(0.95f);
                chart.setDrawHoleEnabled(true);
                chart.setHoleColor(Color.WHITE);
                chart.setTransparentCircleRadius(61f);

                ValueFormatter formatter = new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return String.valueOf((int)value);
                    }
                };

                chart.setCenterText(sumAllPoints(userList));
                chart.setCenterTextSize(24f);
                chart.setDrawEntryLabels(false); // removing values labels

                Legend l = chart.getLegend();
                l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                l.setTextSize(14f);

                MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
                chart.setMarker(mv);

                chart.animateY(1000, Easing.EaseInOutQuad);
                PieDataSet dataSet = new PieDataSet(myPointsData(userList), "");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(10f);
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                dataSet.setValueFormatter(formatter);
                PieData data = new PieData(dataSet);
                data.setValueTextSize(24f);
                data.setValueTextColor(Color.YELLOW);
                chart.setData(data);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }

    private String sumAllPoints(List<User> userList) {
        Integer allPoints = 0;
        for (User u : userList) {
            if ((u.getId().equals(firebaseUser.getUid()))){
                allPoints += u.getDvigatel();
                allPoints += u.getTransmissiya();
                allPoints += u.getPodveska();
                allPoints += u.getRul();
                allPoints += u.getOhlazhdeniye();
                allPoints += u.getZajiganiye();
                allPoints += u.getToplivo();
                allPoints += u.getTormoz();
                allPoints += u.getElectro();
                allPoints += u.getDatchiki();
                allPoints += u.getKuzov();
                allPoints += u.getSalon();
                allPoints += u.getMasla();
                allPoints += u.getTotalPoints();
            }
        }
        return allPoints + "\n баллов";
    }

    private List<PieEntry> myPointsData(List<User> userList) {
        String[] manCats = getResources().getStringArray(R.array.main_categories);
        List<PieEntry> yVal = new ArrayList<>();
        for (User u : userList) {
            if (u.getId().equals(firebaseUser.getUid())) {
                if (u.getDvigatel() != 0){
                    yVal.add(new PieEntry((float) u.getDvigatel(), manCats[0]));
                }
                if (u.getTransmissiya() != 0){
                    yVal.add(new PieEntry((float) u.getTransmissiya(), manCats[1]));
                }
                if (u.getPodveska() != 0){
                    yVal.add(new PieEntry((float) u.getPodveska(), manCats[2]));
                }
                if (u.getRul() != 0){
                    yVal.add(new PieEntry((float) u.getRul(), manCats[3]));
                }
                if (u.getOhlazhdeniye() != 0){
                    yVal.add(new PieEntry((float) u.getOhlazhdeniye(), manCats[4]));
                }
                if (u.getZajiganiye() != 0){
                    yVal.add(new PieEntry((float) u.getZajiganiye(), manCats[5]));
                }
                if (u.getToplivo() != 0){
                    yVal.add(new PieEntry((float) u.getToplivo(), manCats[6]));
                }
                if (u.getTormoz() != 0){
                    yVal.add(new PieEntry((float) u.getTormoz(), manCats[7]));
                }
                if (u.getElectro() != 0){
                    yVal.add(new PieEntry((float) u.getElectro(), manCats[8]));
                }
                if (u.getDatchiki() != 0){
                    yVal.add(new PieEntry((float) u.getDatchiki(), manCats[9]));
                }
                if (u.getKuzov() != 0){
                    yVal.add(new PieEntry((float) u.getKuzov(), manCats[10]));
                }
                if (u.getSalon() != 0){
                    yVal.add(new PieEntry((float) u.getSalon(), manCats[11]));
                }
                if (u.getMasla() != 0){
                    yVal.add(new PieEntry((float) u.getMasla(), manCats[12]));
                }
                if (u.getTotalPoints() != 0){
                    yVal.add(new PieEntry((float) u.getTotalPoints(), getString(R.string.final_test)));
                }
            }
        }
        return  yVal;
    }
}