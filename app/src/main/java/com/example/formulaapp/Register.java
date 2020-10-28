package com.example.formulaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulaapp.Models.Points;
import com.example.formulaapp.Models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    MaterialEditText user_email, user_name, user_password;
    Button signUp_btn, signUp_google_btn, status_intern_btn, status_employee_btn;
    TextView privacy_policy_link;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    User user = new User();
    List<Points> pointsList = new ArrayList<>();
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_email = findViewById(R.id.user_email);
        user_name = findViewById(R.id.user_name);
        user_password = findViewById(R.id.user_password);
        signUp_btn = findViewById(R.id.sign_up_btn);
        status_intern_btn = findViewById(R.id.status_intern_btn);
        status_employee_btn = findViewById(R.id.status_employee_btn);
        signUp_google_btn = findViewById(R.id.signUp_google_btn);
        privacy_policy_link = findViewById(R.id.privacy_policy_link);
        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        user.setPointsList(pointsList);
        user.setStatus("");

        status_intern_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setStatus("intern");
                status_intern_btn.setBackgroundResource(R.drawable.status_btn_yellow);
                status_employee_btn.setBackgroundResource(R.drawable.status_btn_grey);
            }
        });

        status_employee_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setStatus("employee");
                status_employee_btn.setBackgroundResource(R.drawable.status_btn_yellow);
                status_intern_btn.setBackgroundResource(R.drawable.status_btn_grey);
            }
        });

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
                    Toast.makeText(Register.this, "Пароль должен содержать не менее 6 символов!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (!user.getStatus().equals("")){
                        register(txt_username, txt_email, txt_password);
                    }
                    else {
                        Toast.makeText(Register.this, "Укажите Ваш статус!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        createRequest();

        signUp_google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!user.getStatus().equals("")){
                    signUpGoogle();
                }
                else {
                    Toast.makeText(Register.this, "Укажите Ваш статус!", Toast.LENGTH_LONG).show();
                }
            }
        });

        privacy_policy_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Privacy_policy.class));
            }
        });
    }

    private void register(String username, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            firebaseUser = auth.getCurrentUser();
                            String userId = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users")
                                    .child(userId);

                            user.setId(userId);
                            user.setUsername(username);
                            user.setEmail(email);
                            user.setImageUrl("default");

                            reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signUpGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle (account);
            } catch (ApiException e) {
                Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            firebaseUser = auth.getCurrentUser();
                            String userId = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users")
                                    .child(userId);

                            user.setId(userId);
                            user.setUsername(firebaseUser.getDisplayName());
                            user.setEmail(firebaseUser.getEmail());
                            user.setImageUrl(firebaseUser.getPhotoUrl().toString());
                            reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Register.this, "Пользователь успешно зарегистрирован!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Register.this, MainActivity.class));
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(Register.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}