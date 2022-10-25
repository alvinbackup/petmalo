package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class Appointment extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    String[] service_item ={"Vaccination","Grooming"};
    AutoCompleteTextView auto_service;
    ArrayAdapter<String> adapterItem;

    EditText app_name,app_lname,app_address,landmark,app_contact,stored_id,app_email,date,time,store;
    String str_name,str_lname,str_adress,str_landmark,str_contact,str_service,str_id,str_email,str_date,str_time,str_store;
    private static final String url = "https://pet-shop-management.000webhostapp.com/android_map_markers/service.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        auto_service=findViewById(R.id.auto_complete_service);
        adapterItem=new ArrayAdapter<String>(this,R.layout.list_item,service_item);
        auto_service.setAdapter(adapterItem);
        auto_service.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Service: "+item,Toast.LENGTH_LONG).show();
            }
        });

        store=findViewById(R.id.store);
        app_name = findViewById(R.id.name);
        app_lname = findViewById(R.id.lname);
        app_email = findViewById(R.id.cust_email);
        app_address = findViewById(R.id.address);
        landmark = findViewById(R.id.landmark);
        app_contact = findViewById(R.id.contact);
        date=findViewById(R.id.app_date);
        time=findViewById(R.id.app_time);

        store=findViewById(R.id.store);
        String storename=getIntent().getStringExtra("storename");
        store.setText(storename);

        stored_id=(EditText)findViewById(R.id.store_id);
        String store_id=getIntent().getStringExtra("storeid");
        stored_id.setText(store_id);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String fullName = userProfile.fullName;
                    String lastName=userProfile.lastname;
                    String address = userProfile.address;
                    String contact = userProfile.contact;
                    String email = userProfile.email;

                    app_name.setText(fullName);
                    app_lname.setText(lastName);
                    app_address.setText(address);
                    app_contact.setText(contact);
                    app_email.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(Appointment.this, "Something Wont Happened!", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void submit(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Submitting Please Wait...");

        if(app_name.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
        }
        else if(app_address.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
        } else if (landmark.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Landmark", Toast.LENGTH_SHORT).show();
        }else if(app_contact.getText().toString().equals("")) {
            Toast.makeText(this, "Contact is Required", Toast.LENGTH_SHORT).show();
        } else if (auto_service.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Service", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.show();
            str_email=app_email.getText().toString().trim();
            str_id=stored_id.getText().toString().trim();
            str_name = app_name.getText().toString().trim();
            str_lname= app_lname.getText().toString().trim();
            str_contact = app_contact.getText().toString().trim();
            str_adress = app_address.getText().toString().trim();
            str_date=date.getText().toString().trim();
            str_time=time.getText().toString().trim();
            str_landmark = landmark.getText().toString().trim();
            str_service = auto_service.getText().toString().trim();
            str_store= store.getText().toString().trim();
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Toast.makeText(Appointment.this, response, Toast.LENGTH_SHORT).show();
                    app_name.setText("");
                    app_address.setText("");
                    landmark.setText("");
                    app_contact.setText("");
                    app_lname.setText("");
                    date.setText("");
                    time.setText("");
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Appointment.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }) {@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("storeid",str_id);
                params.put("email",str_email);
                params.put("name", str_name);
                params.put("lname",str_lname);
                params.put("date",str_date);
                params.put("time",str_time);
                params.put("address", str_adress);
                params.put("landmark", str_landmark);
                params.put("contact", str_contact);
                params.put("service", str_service);
                params.put("storename", str_store);
                return params;
            }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        }
    }
}