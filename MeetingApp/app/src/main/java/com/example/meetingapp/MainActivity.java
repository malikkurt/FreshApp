package com.example.meetingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.quickaccesswallet.WalletCard;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ArrayList<Meet> list;
    private MeetAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.action_profile) {
            Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
        }
        if(item.getItemId() == R.id.action_mymeet){
            Intent mymeetIntent = new Intent(MainActivity.this, MyMeetActivity.class);
            startActivity(mymeetIntent);
        }
        if(item.getItemId() == R.id.action_home){
            Intent homeIntent = new Intent(MainActivity.this,MainActivity.class);

        }

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Kimlik Doğrulama
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()==null){
            Intent loginIntent = new Intent(MainActivity.this , LoginActivity.class);
            startActivity(loginIntent);
            Toast.makeText(getApplicationContext(),"Giriş yapınız", Toast.LENGTH_SHORT).show();
        }
        //Float Button Yeni meet oluşturmaya gidiyor
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newmeetIntent = new Intent(MainActivity.this, NewMeetingActivity.class);
                startActivity(newmeetIntent);

            }
        });
        //Recycler view yapısının kodu

        recyclerView = findViewById(R.id.recycleview);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Meet");

        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetAdapter(this, list);
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Meet item = dataSnapshot.getValue(Meet.class);
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