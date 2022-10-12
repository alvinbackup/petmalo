package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class product extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TextView storeid, categ;
    ImageView noproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Products</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pastel)));

        categ =(TextView)findViewById(R.id.categ);

        recyclerView=(RecyclerView)findViewById(R.id.prod_recview);
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        noproduct=(ImageView)findViewById(R.id.noproduct);
        storeid=(TextView)findViewById(R.id.store_id);

        processdata(getIntent().getStringExtra("store_id"),getIntent().getStringExtra("category"));



    }
    public void processdata(String store_id, String category)
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("christoherhonrado.000webhostapp.com")
                .appendPath("android_map_markers")
                .appendPath("products_mob_con.php")
                .appendQueryParameter("prod_id",store_id)
                .appendQueryParameter("product_categ",category);
        String url = builder.build().toString();
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder=new GsonBuilder();
                Gson gson =builder.create();

                prod_model data[]=gson.fromJson(response,prod_model[].class);
                prod_adapter prod_adapter=new prod_adapter(data);
                recyclerView.setAdapter(prod_adapter);

                if(data.length<1) {
                    recyclerView.setVisibility(View.GONE);
                    noproduct.setVisibility(View.VISIBLE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
}