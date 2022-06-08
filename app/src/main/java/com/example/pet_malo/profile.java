package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class profile extends AppCompatActivity {

    private LinearLayout appointment_btn,map_btn,petcard,reserved;
    TextView email_iden,count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
         getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>PET-Malo</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        setContentView(R.layout.activity_profile);



        email_iden=(TextView)findViewById(R.id.identifier);
        String email=getIntent().getStringExtra("email_iden");
        email_iden.setText(email);

        appointment_btn = (LinearLayout) findViewById(R.id.appointment);
        appointment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openappointment();
            }
        });
        map_btn = (LinearLayout) findViewById(R.id.maps);
        map_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openmap();
        }
    });
        petcard = (LinearLayout) findViewById(R.id.card);
        petcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpetcard();
            }
        });
        reserved = (LinearLayout) findViewById(R.id.reserved_prod);
        reserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openreserved();
            }
        });
      }
//footer menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(this, com.example.pet_malo.user_info.class));
                break;
            case R.id.signout:
                Intent intent = new Intent(profile.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

        }return true;
    }
    public void openappointment(){
        Intent intent = new Intent(this,Appointment.class);
        startActivity(intent);
    }
    public void openmap(){
        Intent intent = new Intent(this,mapstore.class);
        startActivity(intent);
    }
    public void openpetcard(){
        Intent intent = new Intent(this,petlist.class);
        startActivity(intent);
    }
    public void openreserved(){
        Intent intent = new Intent(this,reserved_list.class);
        intent.putExtra("useremail",email_iden.getText().toString());
        startActivity(intent);
    }

}