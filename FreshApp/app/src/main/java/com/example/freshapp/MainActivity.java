package com.example.freshapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private androidx.appcompat.widget.Toolbar toolbar;

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
        if(item.getItemId() == R.id.action_shop){
            Intent shopIntent = new Intent(MainActivity.this, ShopActivity.class);
            startActivity(shopIntent);
        }
        if(item.getItemId() == R.id.action_newsale){
            Intent newSaleIntent = new Intent(MainActivity.this, NewSaleActivity.class);
            startActivity(newSaleIntent);
        }
        if(item.getItemId() == R.id.action_sale){
            Intent saleIntent = new Intent(MainActivity.this, SaleActivity.class);
            startActivity(saleIntent);
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


    }
}