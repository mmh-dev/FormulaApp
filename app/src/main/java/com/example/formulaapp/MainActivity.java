package com.example.formulaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.formulaapp.Fragments.AboutAppFragment;
import com.example.formulaapp.Fragments.MainMenuFragment;
import com.example.formulaapp.Fragments.RatingFragment;
import com.example.formulaapp.Fragments.SavedPagesFragment;
import com.example.formulaapp.Fragments.SpravochnikFragment;
import com.example.formulaapp.Fragments.TestFragment;
import com.example.formulaapp.Models.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    CircleImageView user_photo;
    TextView username, user_email;

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
            System.exit(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.spravochnik:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SpravochnikFragment()).commit();
                        break;
                    case R.id.main:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainMenuFragment()).commit();
                        break;
                    case R.id.rating:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RatingFragment()).commit();
                        break;
                    case R.id.saved_pages:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SavedPagesFragment()).commit();
                        break;
                    case R.id.final_test:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TestFragment()).commit();
                        break;
                    case R.id.send_email_to_manager:
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                        intent.putExtra(Intent.EXTRA_EMAIL, "formula.osh.manager@gmail.com");
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                        break;
                    case R.id.about_app:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutAppFragment()).commit();
                        break;
                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this, Login.class));
                        finish();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MainMenuFragment()).commit();
        }

        View headNavView = navigationView.getHeaderView(0);
        user_photo = (CircleImageView) headNavView.findViewById(R.id.nav_user_photo);
        username = (TextView) headNavView.findViewById(R.id.nav_username);
        user_email = (TextView) headNavView.findViewById(R.id.nav_user_email);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User user = snapshot.getValue(User.class);
                if (user != null) {
                    username.setText(user.getUsername());
                    user_email.setText(user.getEmail());
                    if (user.getImageUrl().equals("default")){
                        user_photo.setImageResource(R.drawable.person_icon);
                    }
                    else {
                        Picasso.get().load(user.getImageUrl()).into(user_photo);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}