package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;

public class order_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.notthatwhite)));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Order Details</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}