package com.example.freshapp.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.freshapp.Announcement;
import com.example.freshapp.AnnouncementAdapter;
import com.example.freshapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private androidx.appcompat.widget.Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<Announcement> list;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private AnnouncementAdapter adapter;

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
            Intent myIntent = new Intent(MainActivity.this, MySaleActivity.class);
            startActivity(myIntent);
        }
        if(menuItem.getItemId() == R.id.action_newsale){
            Intent newSaleIntent = new Intent(MainActivity.this, NewSaleActivity.class);
            startActivity(newSaleIntent);
        }
        if(menuItem.getItemId() ==R.id.action_home){
            Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
        }
        if(menuItem.getItemId() == R.id.action_sale){
            Intent saleIntent = new Intent(MainActivity.this, SaleActivity.class);
            startActivity(saleIntent);
        }
        if(menuItem.getItemId() == R.id.action_profile) {
            Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        if ( mAuth.getCurrentUser()==null){
            Intent loginIntent = new Intent(MainActivity.this , LoginActivity.class);
            startActivity(loginIntent);
            Toast.makeText(getApplicationContext(),"Giriş yapınız",Toast.LENGTH_SHORT).show();
        }
        //Announcement

        recyclerView = findViewById(R.id.announcementrecycle);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("announcement");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnnouncementAdapter(this, list);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Announcement item = dataSnapshot.getValue(Announcement.class);
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