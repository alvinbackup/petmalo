package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        getSupportActionBar().setTitle("Store");

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
        startActivity(intent);
    }
}