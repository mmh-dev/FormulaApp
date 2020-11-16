package com.example.formulaapp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        adapter = new MainMenuAdapter(userList, getContext(), 2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User u = dataSnapshot.getValue(User.class);
                    if (!u.getId().equals(firebaseUser.getUid())){
                        userList.add(new MenuBullet(u.getUsername(), u.getStatus(), 0, u.getImageUrl(), 0, u.getEmail()));
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        adapter.setOnItemClickListener(new MainMenuAdapter.RecycleOnClickListener() {
            @Override
            public void onItemClick(int position) {
                RatingFragment ratingFragment = new RatingFragment();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, ratingFragment).
                        addToBackStack("ManageUsers").commit();
            }

            @Override
            public void onDeleteClick(int position) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle(R.string.delete_user_dialog_title);
                dialog.setMessage(R.string.delete_user_dialog_message).setCancelable(false);
                dialog.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    User u = dataSnapshot.getValue(User.class);
                                    assert u != null;
                                    if (u.getEmail().equals(userList.get(position).getUser_email())) {
                                        reference.child(u.getId()).removeValue();
                                        userList.remove(position);
                                        adapter.notifyDataSetChanged();
                                        Toast.makeText(getContext(), getString(R.string.user_deleted_toast), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                });
                dialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.create().show();
            }
        });

        return view;
    }
}