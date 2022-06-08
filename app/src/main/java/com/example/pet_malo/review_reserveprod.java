  package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;


public class review_reserveprod extends AppCompatActivity {
    //Variables
private TextView rev_price,rev_name,rev_desc,rev_categ,rev_quan,rev_storeid,result,image_text;
private TextView FullnameTextView, AddressTextView,ContactTextView,emailTextView;
//URL
private static  final String url ="https://petmalo.000webhostapp.com/android_map_markers/reserved_product_con.php";
private ImageView re_img;
// POST string
String str_name,str_total,str_desc,str_categ,str_quan,str_storeid,str_custname,str_custadd,str_custcontact,str_custemail,
           str_image;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_reserveprod);


        re_img=findViewById(R.id.rev_img);
        rev_price=findViewById(R.id.rev_price);
        rev_name=findViewById(R.id.rev_name);
        rev_desc=findViewById(R.id.rev_desc);
        rev_categ=findViewById(R.id.rev_categ);
        rev_quan=findViewById(R.id.rev_quan);
        rev_storeid=findViewById(R.id.rev_storeid);
        result=findViewById(R.id.rev_total);
        image_text=findViewById(R.id.image_text);


        String r_name=getIntent().getStringExtra("rev_name");
        rev_name.setText(r_name);

        String r_quan=getIntent().getStringExtra("rev_quan");
        rev_quan.setText(r_quan);

        String r_price=getIntent().getStringExtra("rev_price");
        rev_price.setText(r_price);

        String r_desc=getIntent().getStringExtra("rev_desc");
        rev_desc.setText(r_desc);

        String r_categ=getIntent().getStringExtra("rev_categ");
        rev_categ.setText(r_categ);

        String r_id=getIntent().getStringExtra("store_id");
        rev_storeid.setText(r_id);

        String img_text=getIntent().getStringExtra("rev_img");
        image_text.setText(img_text);

double price = Double.parseDouble(rev_price.getText().toString());
double quantity = Double.parseDouble(rev_quan.getText().toString());

double sum = price * quantity;
result.setText(Double.toString(sum));


        String revimg=getIntent().getStringExtra("rev_img");
        Glide.with(review_reserveprod.this).load("https://petmalo.000webhostapp.com/examples/upload/"+revimg).into(re_img);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        FullnameTextView = (TextView) findViewById(R.id.user_name);
        AddressTextView = (TextView) findViewById(R.id.user_address);
         ContactTextView = (TextView) findViewById(R.id.user_conatct);
         emailTextView = (TextView) findViewById(R.id.user_email);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String Name = userProfile.fullName;
                    String Address = userProfile.address;
                    String Contact = userProfile.contact;
                    String Email = userProfile.email;

                    FullnameTextView.setText(Name);
                    AddressTextView.setText(Address);
                    ContactTextView.setText(Contact);
                    emailTextView.setText(Email);

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(review_reserveprod.this, "Something Wont Happened!", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void Place(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.show();
        progressDialog.setContentView(R.layout.toast_product);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        str_name = rev_name.getText().toString();
        str_desc = rev_desc.getText().toString();
        str_quan = rev_quan.getText().toString();
        str_categ = rev_categ.getText().toString();
        str_custname = FullnameTextView.getText().toString();
        str_custadd = AddressTextView.getText().toString();
        str_custcontact = ContactTextView.getText().toString();
        str_custemail = emailTextView.getText().toString();

        str_storeid = rev_storeid.getText().toString();
        str_total = result.getText().toString();
        str_image = image_text.getText().toString();



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_product,(ViewGroup) findViewById(R.id.toast));

                TextView toasttext = layout.findViewById(R.id.toasttext);
                GifImageView gif = layout.findViewById(R.id.toastimg);
                Toast toast = new Toast(getApplicationContext());
                toasttext.setText("Reserved Successful!");
                gif.setImageResource(R.drawable.check);
                toast.setGravity(Gravity.CENTER, 0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(review_reserveprod.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }) {@Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("prod_name", str_name);
            params.put("prod_desc", str_desc);
            params.put("prod_quan", str_quan);
            params.put("prod_categ", str_categ);
            params.put("cust_name", str_custname);
            params.put("cust_add", str_custadd);
            params.put("cust_contact", str_custcontact);
            params.put("cust_email", str_custemail);

            params.put("store_id", str_storeid);
            params.put("prod_total", str_total);
            params.put("prod_image", str_image);

            return params;
        }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}