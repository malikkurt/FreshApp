package com.example.freshapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Sales> list;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    MyAdapter adapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ShopActivity.this, MainActivity.class));
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        recyclerView = findViewById(R.id.recycleview2);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Sales");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, list);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Sales item = dataSnapshot.getValue(Sales.class);
                    list.add(item);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}