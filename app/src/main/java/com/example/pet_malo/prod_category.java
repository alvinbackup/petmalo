package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class prod_category extends AppCompatActivity {
    LinearLayout all, lin_food;
   String food;


TextView Storeids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_category);

        food = food;

         lin_food=findViewById(R.id.lin_food);

        Storeids=findViewById(R.id.id);
        String ids=getIntent().getStringExtra("storeid");
        Storeids.setText(ids);

        all =findViewById(R.id.cat_all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openreview();
            }
        });

        lin_food=findViewById(R.id.lin_food);
        lin_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openfood();
            }
        });
    //end
        }

    public void openreview(){
        Intent intent = new Intent(this,product.class);
        startActivity(intent);
    }
    public void openfood() {
        Intent intent = new Intent(this, product.class);
        intent.putExtra("food",food);
        startActivity(intent);
    }

    }