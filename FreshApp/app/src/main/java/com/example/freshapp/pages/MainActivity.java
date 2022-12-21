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

    private ArrayList<Announcement> list;

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
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        if ( mAuth.getCurrentUser()==null){
            Intent loginIntent = new Intent(MainActivity.this , LoginActivity.class);
            startActivity(loginIntent);
            Toast.makeText(getApplicationContext(),"Giriş yapınız",Toast.LENGTH_SHORT).show();
        }
        //Announcement

        RecyclerView recyclerView = findViewById(R.id.announcementrecycle);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("announcement");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AnnouncementAdapter adapter = new AnnouncementAdapter(this, list);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Announcement item = dataSnapshot.getValue(Announcement.class);
                    list.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}