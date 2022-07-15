package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class prod_category extends AppCompatActivity {
    LinearLayout all, lin_food,lin_supplies,lin_vitamin,lin_prescrip;
    TextView Storeids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_category);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Categories</font>"));


         all =findViewById(R.id.cat_all);
         lin_food=findViewById(R.id.lin_food);
         lin_supplies=findViewById(R.id.lin_supplies);
         lin_vitamin=findViewById(R.id.lin_vitamin);
         lin_prescrip=findViewById(R.id.lin_prescrip);

        Storeids=findViewById(R.id.id);
        String ids=getIntent().getStringExtra("storeid");
        Storeids.setText(ids);

        //ids
        TextView all_categ = (TextView) findViewById(R.id.all);
        TextView food = (TextView) findViewById(R.id.food);
        TextView supplies = (TextView) findViewById(R.id.supplies);
        TextView vitamin =(TextView) findViewById(R.id.vitamin);
        TextView prescrip =(TextView) findViewById(R.id.prescrip);


        //onclick categories

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = all_categ.getText().toString();
                Intent intent = new Intent(getApplicationContext(), product.class);
                intent.putExtra("store_id",Storeids.getText().toString());
                intent.putExtra("category",value);
                startActivity(intent);
            }
        });
        lin_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = food.getText().toString();
                Intent intent = new Intent(getApplicationContext(), product.class);
                intent.putExtra("store_id",Storeids.getText().toString());
                intent.putExtra("category",value);
                startActivity(intent);

            }
        });
        lin_supplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = supplies.getText().toString();
                Intent intent = new Intent(getApplicationContext(), product.class);
                intent.putExtra("store_id",Storeids.getText().toString());
                intent.putExtra("category",value);
                startActivity(intent);
            }
        });
        lin_vitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = vitamin.getText().toString();
                Intent intent = new Intent(getApplicationContext(), product.class);
                intent.putExtra("store_id",Storeids.getText().toString());
                intent.putExtra("category",value);
                startActivity(intent);
            }
        });
        lin_prescrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = prescrip.getText().toString();
                Intent intent = new Intent(getApplicationContext(), product.class);
                intent.putExtra("store_id",Storeids.getText().toString());
                intent.putExtra("category",value);
                startActivity(intent);

            }
        });
    //end
        }
    }