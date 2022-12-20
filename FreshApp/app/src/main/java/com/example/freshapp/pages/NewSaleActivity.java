package com.example.freshapp.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.freshapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewSaleActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Spinner newsaleSpinnerCity, newsaleSpinnerType;
    private DatabaseReference databaseReference;
    private List<String> names,types;
    private Button newsaleButton;
    private EditText newsaleKg;
    private EditText newsalePrice;
    private EditText newsaleOpinion;
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
            Intent myIntent = new Intent(NewSaleActivity.this, MySaleActivity.class);
            startActivity(myIntent);
        }
        if(menuItem.getItemId() == R.id.action_newsale){
            Intent newSaleIntent = new Intent(NewSaleActivity.this, NewSaleActivity.class);

        }
        if(menuItem.getItemId() ==R.id.action_home){
            Intent homeIntent = new Intent(NewSaleActivity.this, MainActivity.class);
            startActivity(homeIntent);
        }
        if(menuItem.getItemId() == R.id.action_sale){
            Intent saleIntent = new Intent(NewSaleActivity.this, SaleActivity.class);
            startActivity(saleIntent);
        }
        if(menuItem.getItemId() == R.id.action_profile) {
            Intent profileIntent = new Intent(NewSaleActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
        }
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sale);

        newsaleSpinnerCity = findViewById(R.id.newsale_spinner_city);
        names = new ArrayList<>();
        newsaleSpinnerType = findViewById(R.id.newsale_spinner_type);
        types = new ArrayList<>();

        newsaleButton = (Button) findViewById(R.id.newsale_button);
        newsaleKg = (EditText) findViewById(R.id.newsale_kg);
        newsalePrice = (EditText) findViewById(R.id.newsale_price);
        newsaleOpinion = (EditText) findViewById(R.id.newsale_opininon);

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("spinner").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot chilsnapshot : snapshot.getChildren()) {
                    String spinnerName = chilsnapshot.child("name").getValue(String.class);
                    names.add(spinnerName);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(NewSaleActivity.this, android.R.layout.simple_spinner_item, names);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                newsaleSpinnerCity.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child("types").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot tysnapshot : snapshot.getChildren()) {
                    String spinnerTypes = tysnapshot.child("saleitem").getValue(String.class);
                    types.add(spinnerTypes);

                }

                ArrayAdapter<String> arraytypeAdapter = new ArrayAdapter<>(NewSaleActivity.this, android.R.layout.simple_spinner_item, types);
                arraytypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                newsaleSpinnerType.setAdapter(arraytypeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        newsaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = newsaleSpinnerCity.getSelectedItem().toString();
                String type = newsaleSpinnerType.getSelectedItem().toString();
                String weight = newsaleKg.getText().toString();
                String price = newsalePrice.getText().toString();
                String description = newsaleOpinion.getText().toString();

                if(!TextUtils.isEmpty(city) || !TextUtils.isEmpty(type) || !TextUtils.isEmpty(weight) || !TextUtils.isEmpty(price) || !TextUtils.isEmpty(description)){

                    newsaleadd(city,type,weight,price,description);
                }

            }
        });

    }



    String user_id = mAuth.getInstance().getCurrentUser().getUid();

    private void newsaleadd(String city, String type, String weight, String price, String description) {

        DatabaseReference newSaleRef = databaseReference.push();
        String sale_id = newSaleRef.getKey();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Sales").child(sale_id);
        HashMap<String,String> userMap = new HashMap<>();
        userMap.put("city",city);
        userMap.put("type",type);
        userMap.put("weight",weight);
        userMap.put("price",price);
        userMap.put("description",description);
        userMap.put("user_id",user_id);
        userMap.put("sale_id",sale_id);

        databaseReference.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Satışa eklendi",Toast.LENGTH_SHORT).show();
                    Intent saleIntent = new Intent(NewSaleActivity.this, SaleActivity.class);
                    startActivity(saleIntent);
                }
            }
        });

    }
}