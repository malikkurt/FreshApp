package com.example.meetingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class NewMeetingActivity extends AppCompatActivity {

    private Spinner newmeetSpinnerCity, newmeetSpinnerTime, newmeetSpinnerConcept;
    private EditText newmeetAdress, newmeetDescription;
    private Button newmeetButton;
    private List<String> city,times,concept;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meeting);

        newmeetSpinnerCity = findViewById(R.id.newmeet_spinner_city);
        city = new ArrayList<>();
        newmeetSpinnerTime = findViewById(R.id.newmeet_spinner_time);
        times = new ArrayList<>();
        newmeetSpinnerConcept = findViewById(R.id.newmeet_spinner_concept);
        concept = new ArrayList<>();
        newmeetAdress = (EditText) findViewById(R.id.newmeet_adress);
        newmeetDescription = (EditText) findViewById(R.id.newmeet_description);
        newmeetButton = (Button) findViewById(R.id.newmeet_button);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("spinner_city").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot chilsnapshot : snapshot.getChildren()){
                    String spinnerCity = chilsnapshot.child("city").getValue(String.class);
                    city.add(spinnerCity);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(NewMeetingActivity.this, android.R.layout.simple_spinner_item, city);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                newmeetSpinnerCity.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child("spinner_times").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot tysnapshot : snapshot.getChildren()) {
                    String spinnerTimes = tysnapshot.child("begınTıme").getValue(String.class);
                    times.add(spinnerTimes);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(NewMeetingActivity.this, android.R.layout.simple_spinner_item, times);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                newmeetSpinnerTime.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child("spinner_content").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot mysnapshot : snapshot.getChildren()) {
                    String spinnerConcept = mysnapshot.child("concept").getValue(String.class);
                    concept.add(spinnerConcept);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(NewMeetingActivity.this, android.R.layout.simple_spinner_item, concept);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                newmeetSpinnerConcept.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        newmeetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = newmeetSpinnerCity.getSelectedItem().toString();
                String time = newmeetSpinnerTime.getSelectedItem().toString();
                String concept = newmeetSpinnerConcept.getSelectedItem().toString();
                String adress = newmeetAdress.getText().toString();
                String description = newmeetDescription.getText().toString();

                if(!TextUtils.isEmpty(city) || !TextUtils.isEmpty(time) || !TextUtils.isEmpty(concept) || !TextUtils.isEmpty(adress) || !TextUtils.isEmpty(description)){

                    newmeetAdd(city,time,concept,adress,description);

                }

            }
        });


    }

    String user_id = mAuth.getInstance().getCurrentUser().getUid();

    private void newmeetAdd(String city, String time, String concept, String adress, String description) {

        DatabaseReference newMeetRef = databaseReference.push();
        String meet_id = newMeetRef.getKey();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Meet").child(meet_id);
        HashMap<String,String> userMap = new HashMap<>();
        userMap.put("city",city);
        userMap.put("time",time);
        userMap.put("concept",concept);
        userMap.put("adress",adress);
        userMap.put("description",description);
        userMap.put("user_id",user_id);
        userMap.put("meet_id",meet_id);

        databaseReference.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Buluşma Ayarlandı",Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(NewMeetingActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }
            }
        });




    }
}