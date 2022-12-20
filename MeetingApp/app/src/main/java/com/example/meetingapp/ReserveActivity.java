package com.example.meetingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.nio.charset.StandardCharsets;

public class ReserveActivity extends AppCompatActivity {

    private TextView city,time,concept,adress,description;
    private Button reserveCounter;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        reserveCounter = (Button) findViewById(R.id.reserve_counterbutton);
        mAuth = FirebaseAuth.getInstance();
        city = findViewById(R.id.reserve_city);
        time = findViewById(R.id.reserve_time);
        concept = findViewById(R.id.reserve_concept);
        adress = findViewById(R.id.reserve_adress);
        description = findViewById(R.id.reserve_description);




        Intent reserveIntent = getIntent();
        String meet_id = reserveIntent.getStringExtra("meet_id");


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Meet").child(meet_id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String citydb = snapshot.child("city").getValue(String.class);
                city.setText(citydb);
                String timedb = snapshot.child("time").getValue(String.class);
                time.setText(timedb);
                String conceptdb = snapshot.child("concept").getValue(String.class);
                concept.setText(conceptdb);
                String adressdb = snapshot.child("adress").getValue(String.class);
                adress.setText(adressdb);
                String descrpitiondb = snapshot.child("description").getValue(String.class);
                description.setText(descrpitiondb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reserveCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Kayıt yapıldı", Toast.LENGTH_SHORT).show();
            }
        });





    }
}