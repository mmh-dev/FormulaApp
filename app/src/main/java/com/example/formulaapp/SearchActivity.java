package com.example.formulaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.formulaapp.Adapters.PagesAdapter;
import com.example.formulaapp.Fragments.ArticleFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    PagesAdapter adapter;
    List<String> allPagesList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search_home) {
            startActivity(new Intent(SearchActivity.this, MainActivity.class));
        }
        if (item.getItemId() == R.id.search_search) {
            Toast.makeText(SearchActivity.this, "Search!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);

        toolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.allPagesView);

        fillList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PagesAdapter(allPagesList, 2, this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PagesAdapter.RecycleOnClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchActivity.this, ArticleActivity.class);
                intent.putExtra("title", allPagesList.get(position));
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });

    }

    private void fillList() {
        allPagesList.clear();
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.engine));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.gearbox));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.transmission));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.steering_wheel));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.cooling));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.igniting));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.fuel));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.brakes));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.electric));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.sensors));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.car_body));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.salon));
        Collections.addAll(allPagesList, getResources().getStringArray(R.array.oil));
    }
}