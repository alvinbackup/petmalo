package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class pet_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Pet Info</font>"));

        TextView pet_name = (TextView) findViewById(R.id.pet_name);

        String petname=getIntent().getStringExtra("pet_name");
        pet_name.setText(petname);

    }
}