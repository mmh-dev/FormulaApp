package com.example.formulaapp.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.formulaapp.Adapters.RatingAdapter;
import com.example.formulaapp.Models.User;
import com.example.formulaapp.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserRatingFragment extends Fragment {

    RadioGroup radioGroup;
    RecyclerView recyclerView;
    DatabaseReference reference;
    List<User> userList = new ArrayList<>();
    List<User> filteredList = new ArrayList<>();
    RatingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_rating, container, false);
        radioGroup = view.findViewById(R.id.radioGroup);
        recyclerView = view.findViewById(R.id.userListView);
        getActivity().setTitle(getString(R.string.users_rating));

        userList.clear();
        filteredList.addAll(userList);
        adapter = new RatingAdapter(filteredList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    userList.add(user);
                }

                userList.sort(Comparator.comparing(User::getAllPoints).reversed());
                userList.get(0).setRanking(1);
                for (int i = 1; i <userList.size() ; i++) {
                    if (userList.get(i).getAllPoints() >= userList.get(i-1).getAllPoints()){
                        userList.get(i).setRanking(userList.get(i-1).getRanking());
                    }
                    else {
                        userList.get(i).setRanking(userList.get(i-1).getRanking() + 1);
                    }
                };
                adapter.notifyDataSetChanged();
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.internsRadioButton:
                                for (User u: userList) {
                                    if (u.getStatus().equals("Стажер")){
                                        filteredList.add(u);
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                return;
                            case R.id.employeesRadioButton:
                                for (User u: userList) {
                                    if (u.getStatus().equals("Сотрудник")){
                                        filteredList.add(u);
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                return;
                            case R.id.allUsersRadioButton:
                                filteredList.addAll(userList);
                                adapter.notifyDataSetChanged();
                        }
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        adapter.setOnItemClickListener(new RatingAdapter.RecycleOnClickListener() {
            @Override
            public void onItemClick(int position) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User selectedUser = new User();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            User u = dataSnapshot.getValue(User.class);
                            assert u != null;
                            if (u.getEmail().equals(userList.get(position).getEmail())) {
                                selectedUser = u;
                            }
                        }
                        RatingFragment ratingFragment = new RatingFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("selectedUser", selectedUser);
                        ratingFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
                                .replace(R.id.fragment_container, ratingFragment).
                                addToBackStack("User rating menu").commit();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
        return view;
    }

}