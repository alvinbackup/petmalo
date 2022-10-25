package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class appointment_details extends AppCompatActivity {

    TextView app_id,app_store,app_date,app_time,app_stat,app_fname,app_lname,app_add,app_con,app_landmark,app_service;
    ImageView app_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);

        app_id=findViewById(R.id.appoint_id);
        String app_ids=getIntent().getStringExtra("app_id");
        app_id.setText(app_ids);

        app_store=findViewById(R.id.appoint_store);
        String appstore=getIntent().getStringExtra("store");
        app_store.setText(appstore);

        app_date=findViewById(R.id.appoint_date);
        String appdate=getIntent().getStringExtra("date");
        app_date.setText(appdate);

        app_time=findViewById(R.id.appoint_time);
        String apptime=getIntent().getStringExtra("time");
        app_time.setText(apptime);

        app_stat=findViewById(R.id.appoint_status);
        String appstat=getIntent().getStringExtra("status");
        app_stat.setText(appstat);


        app_fname=findViewById(R.id.appoint_fname);
        String appfname=getIntent().getStringExtra("fname");
        app_fname.setText(appfname);

        app_lname=findViewById(R.id.appoint_lname);
        String applname=getIntent().getStringExtra("lname");
        app_lname.setText(applname);

        app_add=findViewById(R.id.appoint_add);
        String appadd=getIntent().getStringExtra("address");
        app_add.setText(appadd);


        app_con=findViewById(R.id.appoint_contact);
        String appcon=getIntent().getStringExtra("contact");
        app_con.setText(appcon);

        app_landmark=findViewById(R.id.appoint_landmark);
        String appland=getIntent().getStringExtra("landmark");
        app_landmark.setText(appland);

        app_service=findViewById(R.id.appoint_service);
        String appservice=getIntent().getStringExtra("service");
        app_service.setText(appservice);

        app_img=findViewById(R.id.app_details_img);
        String appimg=getIntent().getStringExtra("image");
        Glide.with(appointment_details.this).load("https://pet-shop-management.000webhostapp.com/examples/upload/"+appimg).into(app_img);
    }
}