package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class order_details extends AppCompatActivity {
TextView status,prod_name,quantity, cust_name,cust_contact,cust_add,order_descrip,order_total;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Order Details</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.notthatwhite)));


        status=(TextView)findViewById(R.id.order_status);
        prod_name=(TextView)findViewById(R.id.order_name);
        cust_name=(TextView)findViewById(R.id.cust_name);
        cust_contact=(TextView)findViewById(R.id.cust_contact);
        cust_add=(TextView)findViewById(R.id.cust_add);
        quantity=(TextView)findViewById(R.id.order_quan);
        img=(ImageView)findViewById(R.id.order_img);
        order_total=(TextView)findViewById(R.id.order_total);
        order_descrip=(TextView)findViewById(R.id.order_desc);


        String order_stat=getIntent().getStringExtra("order_status");
        status.setText(order_stat);

        String order_prod=getIntent().getStringExtra("prod_name");
        prod_name.setText(order_prod);

        String order_quan=getIntent().getStringExtra("order_quantity");
        quantity.setText(order_quan);

        String cust_nam=getIntent().getStringExtra("order_custname");
        cust_name.setText(cust_nam);

        String cust_con=getIntent().getStringExtra("order_custcon");
        cust_contact.setText(cust_con);

        String cust_address=getIntent().getStringExtra("order_custadd");
        cust_add.setText(cust_address);

        String order_desc=getIntent().getStringExtra("orderdd");
        order_descrip.setText(order_desc);

        String total=getIntent().getStringExtra("ordertt");
        order_total.setText(total);


        String image=getIntent().getStringExtra("img");
        Glide.with(order_details.this).load(image).into(img);
    }
}