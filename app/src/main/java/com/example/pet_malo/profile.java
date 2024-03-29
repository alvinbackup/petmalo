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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class profile extends AppCompatActivity {

    private LinearLayout appointment_btn,profile_btn,petcard,reserved;
    TextView email_iden,count;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
         getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>PET-Malo</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pastel)));
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.home_nav);
        //Set Home Nav As Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

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

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        email_iden=(TextView)findViewById(R.id.identifier);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String email = userProfile.email;
                    email_iden.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(profile.this, "Something Wont Happened!", Toast.LENGTH_LONG).show();
            }
        });


        appointment_btn = (LinearLayout) findViewById(R.id.appointment);
        appointment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openappointment();
            }
        });
        profile_btn = (LinearLayout) findViewById(R.id.profile);
        profile_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openprofile();
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
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(profile.this, MainActivity.class));
                finish();


        }return true;
    }
    public void openappointment(){
        Intent intent = new Intent(this,appointment_list.class);
        intent.putExtra("useremail",email_iden.getText().toString());
        startActivity(intent);
    }
    public void openprofile(){
        Intent intent = new Intent(this,user_info.class);
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