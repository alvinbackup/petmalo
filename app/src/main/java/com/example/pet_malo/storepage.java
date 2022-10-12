package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class storepage extends AppCompatActivity {
    private LinearLayout petcare, products, store_appointment;
    TextView Storeids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storespage);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Store Home</font>"));

        Storeids=findViewById(R.id.storeid);
        String ids=getIntent().getStringExtra("id");
        Storeids.setText(ids);

        petcare = (LinearLayout) findViewById(R.id.petcare);
        petcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpetcare();
            }
        });
        products = (LinearLayout) findViewById(R.id.product);
        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openproduct();
            }
        });
        store_appointment = (LinearLayout) findViewById(R.id.store_appointment);
        store_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openappointment();
            }
        });




        //intent intent
    }
    public void openpetcare(){
        Intent intent = new Intent(this,petcare.class);
        intent.putExtra("storeid",Storeids.getText().toString());
        startActivity(intent);
    }
    public void openproduct(){
        Intent intent = new Intent(this,prod_category.class);
        intent.putExtra("storeid",Storeids.getText().toString());
        startActivity(intent);
    }
    public void openappointment(){
        Intent intent = new Intent(this,Appointment.class);
        intent.putExtra("storeid",Storeids.getText().toString());
        startActivity(intent);
    }
}