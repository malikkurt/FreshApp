package com.example.freshapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerName;
    private EditText registerEmail;
    private EditText registerPassword;
    private Button registerButton;
    private FirebaseAuth mAuth;
    private EditText registerPhone;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = (Button) findViewById(R.id.register_button);
        registerEmail = (EditText) findViewById(R.id.register_email);
        registerName = (EditText) findViewById(R.id.register_name);
        registerPassword = (EditText) findViewById(R.id.register_password);
        registerPhone = (EditText) findViewById(R.id.register_phone);

        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = registerName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String phone = registerPhone.getText().toString();


                if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(phone)){

                    register_user(name,password,email,phone);

                }
            }

        });
    }

    private void register_user(String name, String password, String email, String phone) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {

            if(task.isSuccessful()){
                Toast.makeText(getApplicationContext(),"Deneme 2",Toast.LENGTH_SHORT).show();
                String user_id = mAuth.getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                HashMap<String,String> userMap = new HashMap<>();
                userMap.put("name",name);
                userMap.put("phone",phone);

                mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Deneme 3",Toast.LENGTH_SHORT).show();
                            Intent mainIntent=new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(mainIntent);
                        }
                    }
                });
            }
            else{
                Toast.makeText(getApplicationContext(),"Hata"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}