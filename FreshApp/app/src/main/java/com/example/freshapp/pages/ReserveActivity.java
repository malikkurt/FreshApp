package com.example.freshapp.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.freshapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReserveActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private Button reserveButton;

    TextView city,type,weight,price,description;


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
            Intent shopIntent = new Intent(ReserveActivity.this, MySaleActivity.class);
            startActivity(shopIntent);
        }
        if(menuItem.getItemId() == R.id.action_newsale){
            Intent newSaleIntent = new Intent(ReserveActivity.this, NewSaleActivity.class);
            startActivity(newSaleIntent);
        }
        if(menuItem.getItemId() ==R.id.action_home){
            Intent homeIntent = new Intent(ReserveActivity.this, MainActivity.class);
            startActivity(homeIntent);
        }
        if(menuItem.getItemId() == R.id.action_sale){
            Intent saleIntent = new Intent(ReserveActivity.this, SaleActivity.class);
            startActivity(saleIntent);
        }
        if(menuItem.getItemId() == R.id.action_profile) {
            Intent profileIntent = new Intent(ReserveActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);


        reserveButton = (Button) findViewById(R.id.reserve_active_button);
        city = findViewById(R.id.reserve_city);
        type = findViewById(R.id.reserve_type);
        weight = findViewById(R.id.reserve_weight);
        price= findViewById(R.id.reserve_price);
        description = findViewById(R.id.reserve_description);

        Intent reserveIntent = getIntent();
        String sale_id = reserveIntent.getStringExtra("sale_id");


        database = FirebaseDatabase.getInstance();
        databaseReference  = database.getReference("Sales").child(sale_id);

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

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                

            }
        });


    }
}