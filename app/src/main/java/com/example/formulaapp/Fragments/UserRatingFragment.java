package com.example.formulaapp.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        View view = inflater.inflate(R.layout.fragment_user_rating, container, false);
        radioGroup = view.findViewById(R.id.radioGroup);
        recyclerView = view.findViewById(R.id.userListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reference = FirebaseDatabase.getInstance().getReference("Users");
        getActivity().setTitle(getString(R.string.users_rating));
        adapter = new RatingAdapter(filteredList, getContext());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                adapter.notifyDataSetChanged();
                switch (checkedId) {
                    case R.id.internsRadioButton:
                        filteredList.clear();
                        for (User u : userList) {
                            if (u.getStatus().equals("Стажер")) {
                                filteredList.add(u);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        return;
                    case R.id.employeesRadioButton:
                        filteredList.clear();
                        for (User u : userList) {
                            if (u.getStatus().equals("Cотрудник")) {
                                filteredList.add(u);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        return;
                    case R.id.allUsersRadioButton:
                        filteredList.clear();
                        filteredList.addAll(userList);
                        adapter.notifyDataSetChanged();
                }
            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
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
                filteredList.clear();
                filteredList.addAll(userList);

                recyclerView.setAdapter(adapter);
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
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
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