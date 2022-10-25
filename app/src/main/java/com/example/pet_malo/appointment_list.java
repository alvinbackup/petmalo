package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class appointment_list extends AppCompatActivity {
    private static final String url ="https://pet-shop-management.000webhostapp.com/android_map_markers/appointment_jsonselect.php";
    RecyclerView recview_app;

    ImageView noapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>My Appointment</font>"));

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
        noapp=(ImageView)findViewById(R.id.noapp);
        recview_app=findViewById(R.id.recview_appointment);
        recview_app.setLayoutManager(new LinearLayoutManager(this));

        processdata();
    }
    public void  processdata()
    {
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();

                appointment_list_model data[]=gson.fromJson(response,appointment_list_model[].class);
                myapp_adapter adapter=new myapp_adapter(data);
                recview_app.setAdapter(adapter);

                if(data.length<1) {
                    recview_app.setVisibility(View.GONE);
                    noapp.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_LONG).show();

            }
        }
        );

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}