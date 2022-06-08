package com.example.pet_malo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class Appointment extends AppCompatActivity {


    EditText name,address,landmark,contact,service;
    String str_name,str_adress,str_landmark,str_contact,str_service;
    private static final String url = "https://petmalo.000webhostapp.com/service.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);


        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        landmark = findViewById(R.id.landmark);
        contact = findViewById(R.id.contact);
        service = findViewById(R.id.service);
    }

    public void submit(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Submitting Please Wait...");

        if(name.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
        }
        else if(address.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
        } else if (landmark.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Landmark", Toast.LENGTH_SHORT).show();
        }else if(contact.getText().toString().equals("")) {
            Toast.makeText(this, "Contact is Required", Toast.LENGTH_SHORT).show();
        } else if (service.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Service", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.show();
            str_name = name.getText().toString().trim();
            str_adress = address.getText().toString().trim();
            str_landmark = landmark.getText().toString().trim();
            str_contact = contact.getText().toString().trim();
            str_service = service.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Toast.makeText(Appointment.this, response, Toast.LENGTH_SHORT).show();
                    name.setText("");
                    address.setText("");
                    landmark.setText("");
                    contact.setText("");
                    service.setText("");
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
                params.put("name", str_name);
                params.put("address", str_adress);
                params.put("landmark", str_landmark);
                params.put("contact", str_contact);
                params.put("service", str_service);
                return params;
            }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        }
    }
}