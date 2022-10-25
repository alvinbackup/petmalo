package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class My_cart extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;
     RecyclerView cartrecview;
     cart_adapder cart_adapder;
     List<cart_model> cartModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);


        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        cartrecview=findViewById(R.id.recycleview_cart);
        cartrecview.setLayoutManager(new LinearLayoutManager(this));

        cartModelList=new ArrayList<>();
        cart_adapder=new cart_adapder(this,cartModelList);

        cartrecview.setAdapter(cart_adapder);


        db.collection("AddToCArt").document(auth.getCurrentUser().getUid()).collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        cart_model cart_model = documentSnapshot.toObject(com.example.pet_malo.cart_model.class);
                        cartModelList.add(cart_model);
                        cart_adapder.notifyDataSetChanged();
                    }
                }
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_nav);
        //Set Home Nav As Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_cart);

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
    }
}