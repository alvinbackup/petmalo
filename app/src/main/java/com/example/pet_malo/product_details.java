package com.example.pet_malo;

import android.content.Intent;
import java.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class product_details extends AppCompatActivity {
    TextView value;
    int count=1;
    private Button btn_reserve,add_to_cart;
    private ImageView img;
    private TextView prod_name, prod_price,prod_stock,prod_desc,prod_id,prod_categ,image_text;
    TextView store;

    FirebaseFirestore firestore;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Product Detail</font>"));

        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();


        value=findViewById(R.id.value);
        img=findViewById(R.id.prod_image);
        btn_reserve=findViewById(R.id.btn_reserve);
        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openreview();

            }
        });

        add_to_cart=findViewById(R.id.add_cart);
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtocart();

            }
        });

        store=findViewById(R.id.storename);
        image_text=findViewById(R.id.text_image);
        prod_name=findViewById(R.id.prod_name);
        prod_price=findViewById(R.id.prod_price);
        prod_stock=findViewById(R.id.prod_stock);
        prod_desc=findViewById(R.id.prod_desc);
        prod_id=findViewById(R.id.prod_id);
        prod_categ=findViewById(R.id.prod_categ);

        String pname=getIntent().getStringExtra("prod_name");
        prod_name.setText(pname);

        String price=getIntent().getStringExtra("prod_price");
        prod_price.setText(price);

        String stock=getIntent().getStringExtra("prod_stock");
        prod_stock.setText(stock);

        String desc=getIntent().getStringExtra("prod_desc");
        prod_desc.setText(desc);

        String id=getIntent().getStringExtra("prod_id");
        prod_id.setText(id);

        String categ=getIntent().getStringExtra("prod_categ");
        prod_categ.setText(categ);

        String image=getIntent().getStringExtra("img");
        Glide.with(product_details.this).load("https://pet-shop-management.000webhostapp.com/examples/upload/"+image).into(img);

        String timage=getIntent().getStringExtra("img");
        image_text.setText(timage);


        String storename=getIntent().getStringExtra("store_name");
        store.setText(storename);





    }



    public void increment(View v){
        count++;
        value.setText(""+ count);


    }
    public void decrement(View v){
        if (count<=1) count=1;
        else count--;
        value.setText(""+ count);
    }


    public void openreview(){
        Intent intent = new Intent(this,review_reserveprod.class);
        intent.putExtra("rev_name",prod_name.getText().toString());
        intent.putExtra("rev_price",prod_price.getText().toString());
        intent.putExtra("rev_desc",prod_desc.getText().toString());
        intent.putExtra("store_id",prod_id.getText().toString());
        intent.putExtra("rev_categ",prod_categ.getText().toString());
        intent.putExtra("rev_quan",value.getText().toString());
        intent.putExtra("rev_img",image_text.getText().toString());
        startActivity(intent);
    }
    private void addtocart() {
        String savecurrentdate, savecurrenttime;
        Calendar calfordate = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MM dd, yyyy");
        savecurrentdate = currentdate.format(calfordate.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");
        savecurrenttime = currenttime.format(calfordate.getTime());


        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("product_name",prod_name.getText().toString());
        cartMap.put("product_price",prod_price.getText().toString());
        cartMap.put("product_desc",prod_desc.getText().toString());
        cartMap.put("product_id",prod_id.getText().toString());
        cartMap.put("product_categ",prod_categ.getText().toString());
        cartMap.put("product_quan",value.getText().toString());
        cartMap.put("product_img",image_text.getText().toString());
        cartMap.put("currentdate",savecurrentdate);
        cartMap.put("currenttime",savecurrenttime);
        cartMap.put("store",store.getText().toString());



        firestore.collection("AddToCArt").document(auth.getCurrentUser().getUid()).collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(product_details.this,"Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}