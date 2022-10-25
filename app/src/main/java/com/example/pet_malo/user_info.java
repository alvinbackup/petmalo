package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class user_info extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference  reference;
    private String userID;
    private SwipeRefreshLayout swipecontainer;

    ImageView profile_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        getSupportActionBar().setTitle("Profile");

        Swipetorefresh();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView greetinglastname = (TextView) findViewById(R.id.lastname_greeting);
        final TextView gmail = (TextView) findViewById(R.id.gmail);
        final TextView FullnameTextView = (TextView) findViewById(R.id.fullname);
        final TextView lastnameTextView = (TextView) findViewById(R.id.lastname);
        final TextView addressTextView = (TextView) findViewById(R.id.address);
        final TextView ContactTextView = (TextView) findViewById(R.id.contact);
        final TextView emailTextView = (TextView) findViewById(R.id.email);

        profile_img=findViewById(R.id.profile_img);
        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_info.this, upload_profile.class);
                startActivity(intent);
                finish();

            }
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String fullName = userProfile.fullName;
                    String lastName = userProfile.lastname;
                    String address = userProfile.address;
                    String contact = userProfile.contact;
                    String email = userProfile.email;

                    greetingTextView.setText(fullName +" ");
                    greetinglastname.setText(lastName);
                    gmail.setText(email);
                    FullnameTextView.setText(fullName);
                    lastnameTextView.setText(lastName);
                    addressTextView.setText(address);
                    ContactTextView.setText(contact);
                    emailTextView.setText(email);

                    Uri uri=user.getPhotoUrl();
                    Glide.with(user_info.this).load(uri).into(profile_img);


                }else {
                    Toast.makeText(user_info.this, "Something Wont Happened!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(user_info.this, "Something Wont Happened!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void Swipetorefresh() {
        swipecontainer=findViewById(R.id.swiperefresh);

        swipecontainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startActivity(getIntent());
                finish();
                overridePendingTransition(0,0);
                swipecontainer.setRefreshing(false);

            }
        });
        swipecontainer.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_green_light,
                android.R.color.holo_orange_light,android.R.color.holo_red_light);

    }
}