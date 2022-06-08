package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class flashscreen extends AppCompatActivity {
    Animation topanim,botanim;
    ImageView petmalo;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashscreen);

        topanim= AnimationUtils.loadAnimation(this, R.anim.top_animation);
        botanim= AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        petmalo =findViewById(R.id.sflashlogo);
        name=findViewById(R.id.name);

        petmalo.setAnimation(topanim);
        name.setAnimation(botanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            }
        },5000);


    }
}