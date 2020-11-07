package com.example.formulaapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.formulaapp.Adapters.MainMenuAdapter;
import com.example.formulaapp.Models.MenuBullet;
import com.example.formulaapp.Models.User;
import com.example.formulaapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManageUserFragment extends Fragment {

    List<MenuBullet> userList = new ArrayList<>();
    RecyclerView recyclerView;
    MainMenuAdapter adapter;
    DatabaseReference reference;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_user, container, false);
        getActivity().setTitle(getString(R.string.manage_users));
        recyclerView = view.findViewById(R.id.manage_users_menu);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userList.clear();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User u = dataSnapshot.getValue(User.class);
                    if (!u.getId().equals(firebaseUser.getUid())){
                        userList.add(new MenuBullet(u.getUsername(), u.getStatus(), 0, u.getImageUrl(), 0));
                    }
                }
                adapter = new MainMenuAdapter(userList, getContext(), 2);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


//        adapter.setOnItemClickListener(new MainMenuAdapter.RecycleOnClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                SecondMenuFragment secondMenuFragment = new SecondMenuFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("header", menuBulletList.get(position).getHeader());
//                secondMenuFragment.setArguments(bundle);
//                getFragmentManager().beginTransaction()
//                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
//                        .replace(R.id.fragment_container, secondMenuFragment).
//                        addToBackStack("MainMenu").commit();
//            }
//        });

        return view;
    }
}