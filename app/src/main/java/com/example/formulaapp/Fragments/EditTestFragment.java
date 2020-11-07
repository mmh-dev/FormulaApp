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
import android.widget.Button;
import android.widget.Toast;

import com.example.formulaapp.Adapters.PagesAdapter;
import com.example.formulaapp.Models.Question;
import com.example.formulaapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EditTestFragment extends Fragment {

    Button add_new_question_btn;
    List<String> questionsList = new ArrayList<>();
    PagesAdapter adapter;
    RecyclerView recyclerView;
    String header;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_test, container, false);

        add_new_question_btn = view.findViewById(R.id.add_new_question_btn);
        recyclerView = view.findViewById(R.id.question_edit_menu);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            header = bundle.getString("header");
        }
        Objects.requireNonNull(getActivity()).setTitle(header);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PagesAdapter(questionsList, 1, getContext());
        reference = FirebaseDatabase.getInstance().getReference("Questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Question question = dataSnapshot.getValue(Question.class);
                    assert question != null;
                    if (question.getCategory().equals(header)) {
                        questionsList.add(question.getQuestionText());
                    }

                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add_new_question_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("header", header);
                AddNewQuestionFragment addNewQuestionFragment = new AddNewQuestionFragment();
                addNewQuestionFragment.setArguments(bundle);
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
                            .replace(R.id.fragment_container, addNewQuestionFragment).
                            addToBackStack("Edit Questions").commit();
                }
            }
        });

        adapter.setOnItemClickListener(new PagesAdapter.RecycleOnClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                Bundle bundle1 = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle1.putString("header", header);
                bundle2.putString("question", questionsList.get(position));
                bundle.putBundle("header", bundle1);
                bundle.putBundle("question", bundle2);
                AddNewQuestionFragment addNewQuestionFragment = new AddNewQuestionFragment();
                addNewQuestionFragment.setArguments(bundle);
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_left)
                            .replace(R.id.fragment_container, addNewQuestionFragment).
                            addToBackStack("Edit Questions").commit();
                }
            }

            @Override
            public void onDeleteClick(int position) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle(R.string.delete_question_dialog_title);
                dialog.setMessage(R.string.delete_alert_dialog_message).setCancelable(false);
                dialog.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                            Question question = dataSnapshot.getValue(Question.class);
                                            if (question != null && question.getQuestionText().equals(questionsList.get(position))) {
                                                dataSnapshot.getRef().removeValue();
                                                questionsList.remove(position);
                                                adapter.notifyDataSetChanged();
                                                Toast.makeText(getContext(), getString(R.string.question_deleted), Toast.LENGTH_SHORT).show();
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