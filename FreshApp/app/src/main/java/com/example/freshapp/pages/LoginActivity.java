package com.example.freshapp.pages;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.freshapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail;
    private EditText loginPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPassword = findViewById(R.id.login_password);
        loginEmail = findViewById(R.id.login_email);
        Button loginButton = findViewById(R.id.login_button);
        TextView loginRegisterto = findViewById(R.id.register_to_button);
        mAuth = FirebaseAuth.getInstance();

        loginRegisterto.setOnClickListener(view -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });
        loginButton.setOnClickListener(view -> {
            String email = loginEmail.getText().toString();
            String password = loginPassword.getText().toString();
            if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
                LoginUser(email,password);
            }
        });
    }

    private void LoginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if (task.isSuccessful()){
                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                Toast.makeText(getApplicationContext(),"Giriş yapıldı",Toast.LENGTH_SHORT).show();
                startActivity(mainIntent);
            }
            else{
                Toast.makeText(getApplicationContext(),"Giriş yapılamadı",Toast.LENGTH_SHORT).show();
            }
        });
    }
}