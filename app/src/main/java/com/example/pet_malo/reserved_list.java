package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class reserved_list extends AppCompatActivity {

    RecyclerView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_list);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Reserved Products</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pastel)));

        listview=(RecyclerView)findViewById(R.id.listview);
        listview.setLayoutManager(new LinearLayoutManager(this));

        processdata(getIntent().getStringExtra("useremail"));

    }
    public void processdata(String useremail)
    {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("christoherhonrado.000webhostapp.com")
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