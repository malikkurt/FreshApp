package com.example.freshapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private Button button_sign_out;
    private FirebaseAuth mAuth;

    TextView email,name,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        button_sign_out = (Button) findViewById(R.id.profile_button_signout);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.profile_email);
        name = findViewById(R.id.profile_name);
        phone = findViewById(R.id.profile_phone);

        String user_id = mAuth.getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Users").child(user_id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String emaildb = snapshot.child("Email").getValue(String.class);
                email.setText(emaildb);
                String namedb = snapshot.child("name").getValue(String.class);
                name.setText(namedb);
                String phonedb = snapshot.child("phone").getValue(String.class);
                phone.setText(phonedb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(getApplicationContext(),"Çıkış yapıldı",Toast.LENGTH_SHORT).show();
                Intent signout = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(signout);
            }
        });

    }
}