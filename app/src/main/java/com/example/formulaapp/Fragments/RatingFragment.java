package com.example.formulaapp.Fragments;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.formulaapp.Models.User;
import com.example.formulaapp.MyMarkerView;
import com.example.formulaapp.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public class RatingFragment extends Fragment {

    PieChart chart;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    List<User> userList = new ArrayList<>();
    User myUser = new User();
    TextView leader1_tv, leader2_tv, leader3_tv, your_position;
    ImageView cup, cup2, cup3;
    String leaders1 = "";
    String leaders2 = "";
    String leaders3 = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        chart = view.findViewById(R.id.chart);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Objects.requireNonNull(getActivity()).setTitle(getString(R.string.rating));
        leader1_tv = view.findViewById(R.id.leader1_name);
        leader2_tv = view.findViewById(R.id.leader2_name);
        leader3_tv = view.findViewById(R.id.leader3_name);
        your_position = view.findViewById(R.id.your_position);
        cup = view.findViewById(R.id.cup);
        cup2 = view.findViewById(R.id.cup2);
        cup3 = view.findViewById(R.id.cup3);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myUser = (User) bundle.getSerializable("selectedUser");
        }

        userList.clear();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    userList.add(user);
                }

                userList.sort(Comparator.comparing(User::getAllPoints).reversed());
                userList.get(0).setRanking(1);
                for (int i = 1; i < userList.size(); i++) {
                    if (userList.get(i).getAllPoints() >= userList.get(i - 1).getAllPoints()) {
                        userList.get(i).setRanking(userList.get(i - 1).getRanking());
                    } else {
                        userList.get(i).setRanking(userList.get(i - 1).getRanking() + 1);
                    }
                }

                for (User u : userList) {
                    if (u.getRanking() == 1) {
                        leaders1 = leaders1 + u.getUsername() + ", ";
                    }
                    if (u.getRanking() == 2) {
                        leaders2 = leaders2 + u.getUsername() + ", ";
                    }
                    if (u.getRanking() == 3) {
                        leaders3 = leaders3 + u.getUsername() + ", ";
                    }
                }
                leader1_tv.setText(leaders1.substring(0, leaders1.length() - 2));
                leader2_tv.setText(leaders2.substring(0, leaders2.length() - 2));
                leader3_tv.setText(leaders3.substring(0, leaders3.length() - 2));

                for (User u : userList) {
                    if (u.getId().equals(myUser.getId())) {
                        your_position.setText(String.valueOf(u.getRanking()));
                        if (u.getRanking() == 1) {
                            cup.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_cup_gold, null));
                            your_position.setTextColor(getContext().getResources().getColor(R.color.colorYellow));
                        } else if (u.getRanking() == 2) {
                            cup.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_cup_silver, null));
                            your_position.setTextColor(getContext().getResources().getColor(R.color.colorYellow));
                        } else if (u.getRanking() == 3) {
                            cup.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_cup_bronze, null));
                            your_position.setTextColor(getContext().getResources().getColor(R.color.colorYellow));
                        }
                    }
                }

                createPieChart();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }

    private void createPieChart() {
        chart.setUsePercentValues(false);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(20, 0, 20, 0);
        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);
        chart.setTransparentCircleRadius(61f);

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        };

        chart.setCenterText(myUser.getAllPoints() + "\n баллов");
        chart.setCenterTextSize(22f);

        Legend l = chart.getLegend();
        l.setEnabled(false);

        IMarker mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
        chart.setMarker(mv);

        PieDataSet dataSet = new PieDataSet(myPointsData(myUser), "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(10f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setValueFormatter(formatter);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTextSize(12f);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(16f);
        data.setValueTextColor(Color.BLACK);
        chart.setData(data);
        chart.animateY(1000, Easing.EaseInOutQuad);
        chart.invalidate();
    }

    private List<PieEntry> myPointsData(User u) {
        String[] manCats = getResources().getStringArray(R.array.main_categories);
        List<PieEntry> yVal = new ArrayList<>();
        if (u.getDvigatel() != 0) {
            yVal.add(new PieEntry((float) u.getDvigatel(), manCats[0]));
        }
        if (u.getTransmissiya() != 0) {
            yVal.add(new PieEntry((float) u.getTransmissiya(), manCats[1]));
        }
        if (u.getPodveska() != 0) {
            yVal.add(new PieEntry((float) u.getPodveska(), manCats[2]));
        }
        if (u.getRul() != 0) {
            yVal.add(new PieEntry((float) u.getRul(), manCats[3]));
        }
        if (u.getOhlazhdeniye() != 0) {
            yVal.add(new PieEntry((float) u.getOhlazhdeniye(), manCats[4]));
        }
        if (u.getZajiganiye() != 0) {
            yVal.add(new PieEntry((float) u.getZajiganiye(), manCats[5]));
        }
        if (u.getToplivo() != 0) {
            yVal.add(new PieEntry((float) u.getToplivo(), manCats[6]));
        }
        if (u.getTormoz() != 0) {
            yVal.add(new PieEntry((float) u.getTormoz(), manCats[7]));
        }
        if (u.getElectro() != 0) {
            yVal.add(new PieEntry((float) u.getElectro(), manCats[8]));
        }
        if (u.getDatchiki() != 0) {
            yVal.add(new PieEntry((float) u.getDatchiki(), manCats[9]));
        }
        if (u.getKuzov() != 0) {
            yVal.add(new PieEntry((float) u.getKuzov(), manCats[10]));
        }
        if (u.getSalon() != 0) {
            yVal.add(new PieEntry((float) u.getSalon(), manCats[11]));
        }
        if (u.getMasla() != 0) {
            yVal.add(new PieEntry((float) u.getMasla(), manCats[12]));
        }
        if (u.getTotalPoints() != 0) {
            yVal.add(new PieEntry((float) u.getTotalPoints(), getString(R.string.final_test)));
        }
        return yVal;
    }
}