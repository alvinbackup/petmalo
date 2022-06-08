package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class user_info extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference  reference;
    private String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView FullnameTextView = (TextView) findViewById(R.id.fullname);
        final TextView addressTextView = (TextView) findViewById(R.id.address);
        final TextView ContactTextView = (TextView) findViewById(R.id.contact);
        final TextView emailTextView = (TextView) findViewById(R.id.email);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String fullName = userProfile.fullName;
                    String address = userProfile.address;
                    String contact = userProfile.contact;
                    String email = userProfile.email;

                    greetingTextView.setText("" + fullName +"!");
                    FullnameTextView.setText(fullName);
                    addressTextView.setText(address);
                    ContactTextView.setText(contact);
                    emailTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(user_info.this, "Something Wont Happened!", Toast.LENGTH_LONG).show();
            }
        });

    }
}