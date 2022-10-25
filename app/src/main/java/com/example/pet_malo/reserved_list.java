package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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

public class reserved_list extends AppCompatActivity {

    RecyclerView listview;
    ImageView noreserved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_list);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Reserved Products</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pastel)));

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
        noreserved=(ImageView)findViewById(R.id.noreserved);
        listview=(RecyclerView)findViewById(R.id.listview);
        listview.setLayoutManager(new LinearLayoutManager(this));

        processdata(getIntent().getStringExtra("useremail"));

    }
    public void processdata(String useremail)
    {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("pet-shop-management.000webhostapp.com")
                .appendPath("android_map_markers")
                .appendPath("reserverlist.php")
                .appendQueryParameter("cust_email",useremail);
        String url = builder.build().toString();
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder=new GsonBuilder();
                Gson  gson=builder.create();

               reservedlist_model data[]= gson.fromJson(response,reservedlist_model[].class);
               reserved_adapter res_adapter=new reserved_adapter(data);
               listview.setAdapter(res_adapter);

                if(data.length<1) {
                    listview.setVisibility(View.GONE);
                    noreserved.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        }
        );

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}