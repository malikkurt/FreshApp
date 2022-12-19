package com.example.freshapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReserveActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase database;

    TextView city,type,weight,price,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);



        database = FirebaseDatabase.getInstance();
        databaseReference  = database.getReference("Sales");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String citydb = snapshot.child("city").getValue(String.class);
                city.setText(citydb);
                String typedb = snapshot.child("type").getValue(String.class);
                type.setText(typedb);
                String weightdb = snapshot.child("weight").getValue(String.class);
                weight.setText(weightdb);
                String pricedb = snapshot.child("price").getValue(String.class);
                price.setText(pricedb);
                String descriptiondb = snapshot.child("description").getValue(String.class);
                description.setText(descriptiondb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}