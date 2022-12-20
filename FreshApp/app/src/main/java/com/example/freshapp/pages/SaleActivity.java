package com.example.freshapp.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.freshapp.MyAdapter;
import com.example.freshapp.R;
import com.example.freshapp.Sales;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SaleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Sales> list;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    MyAdapter adapter;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        super.onOptionsItemSelected(menuItem);
        if(menuItem.getItemId() == R.id.action_mysale){
            Intent myIntent = new Intent(SaleActivity.this, MySaleActivity.class);
            startActivity(myIntent);
        }
        if(menuItem.getItemId() == R.id.action_newsale){
            Intent newSaleIntent = new Intent(SaleActivity.this, NewSaleActivity.class);
            startActivity(newSaleIntent);
        }
        if(menuItem.getItemId() ==R.id.action_home){
            Intent homeIntent = new Intent(SaleActivity.this, MainActivity.class);
            startActivity(homeIntent);
        }
        if(menuItem.getItemId() == R.id.action_sale){
            Intent saleIntent = new Intent(SaleActivity.this, SaleActivity.class);

        }
        if(menuItem.getItemId() == R.id.action_profile) {
            Intent profileIntent = new Intent(SaleActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SaleActivity.this, MainActivity.class));
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);



        recyclerView = findViewById(R.id.recycleview);

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

            }
        });



    }
}