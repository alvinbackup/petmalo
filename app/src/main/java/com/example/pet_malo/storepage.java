package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class storepage extends AppCompatActivity {
    private LinearLayout petcare, products, store_appointment;
    TextView Storeids, store_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storespage);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Store Home</font>"));

        //Bottom nav start
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_nav);
        //Set Home Nav As Selected
        bottomNavigationView.setItemIconTintList(null);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_cart:
                        startActivity(new Intent(getApplicationContext(), My_cart.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_findstore:
                        startActivity(new Intent(getApplicationContext(), mapstore.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }
        });
        //

        Storeids=findViewById(R.id.storeid);
        String ids=getIntent().getStringExtra("id");
        Storeids.setText(ids);

        store_name=findViewById(R.id.storetitle);
        String store=getIntent().getStringExtra("title");
        store_name.setText(store);


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
        intent.putExtra("storename",store_name.getText().toString());

        startActivity(intent);
    }
    public void openappointment(){
        Intent intent = new Intent(this,Appointment.class);
        intent.putExtra("storeid",Storeids.getText().toString());
        intent.putExtra("storename",store_name.getText().toString());
        startActivity(intent);
    }
}