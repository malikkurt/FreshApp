package com.example.freshapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.freshapp.producterscreenelements.NewSaleScreen;


public class ProducterScreen extends Activity{

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producterscreen);

        Button gonewsalescreen = (Button)findViewById(R.id.button_newsalescreen);
        gonewsalescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goingnewsalescreen = new Intent(ProducterScreen.this, NewSaleScreen.class);
                startActivity(goingnewsalescreen);
            }
        });
    }



}
