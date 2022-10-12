package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class product_details extends AppCompatActivity {
    TextView value;
    int count=1;
    private Button btn_reserve;
    private ImageView img;
    private TextView prod_name, prod_price,prod_stock,prod_desc,prod_id,prod_categ,image_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        value=findViewById(R.id.value);
        img=findViewById(R.id.prod_image);
        btn_reserve=findViewById(R.id.btn_reserve);
        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openreview();

            }
        });
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
        Glide.with(product_details.this).load("https://christoherhonrado.000webhostapp.com/examples/upload/"+image).into(img);

        String timage=getIntent().getStringExtra("img");
        image_text.setText(timage);

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
}