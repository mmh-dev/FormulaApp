package com.example.formulaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulaapp.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Register extends AppCompatActivity {

    MaterialEditText user_email, user_name, user_password;
    Button signUp_btn, signUp_google_btn, status_intern_btn, status_employee_btn;
    TextView privacy_policy_link;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_email = findViewById(R.id.user_email);
        user_name = findViewById(R.id.user_name);
        user_password = findViewById(R.id.user_password);
        signUp_btn = findViewById(R.id.sign_up_btn);
        signUp_google_btn = findViewById(R.id.sign_up_google_btn);
        status_intern_btn = findViewById(R.id.status_intern_btn);
        status_employee_btn = findViewById(R.id.status_employee_btn);
        privacy_policy_link = findViewById(R.id.privacy_policy_link);
        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = user_name.getText().toString();
                String txt_email = user_email.getText().toString();
                String txt_password = user_password.getText().toString();

                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Register.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
                else if (txt_password.length() < 6){
                    Toast.makeText(Register.this, "Password must contain at least 6 symbols!", Toast.LENGTH_SHORT).show();
                }
                else {
                    register(txt_username, txt_email, txt_password);
                }
            }
        });
    }

    private void register(String username, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            String userId = user.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users")
                                    .child(userId);

                            User user1 = new User();
                            user1.setId(userId);
                            user1.setUsername(username);
                            user1.setEmail(email);
                            user1.setImageUrl("default");

                            reference.setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Register.this, "Пользователь успешно зарегистрирован!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Register.this, MainActivity.class));
                                    }
                                }
                            });
                        }
                    }
                });
    }
}