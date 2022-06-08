package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class petcare_detailed extends AppCompatActivity {
private ImageView img;
private TextView title, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petcare_detailed);
        img=findViewById(R.id.img);
        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);


        String pctitle=getIntent().getStringExtra("title");
        title.setText(pctitle);
        String pcdesc=getIntent().getStringExtra("desc");
        desc.setText(pcdesc);
        String image=getIntent().getStringExtra("img");
        Glide.with(petcare_detailed.this).load(image).into(img);
    }
}