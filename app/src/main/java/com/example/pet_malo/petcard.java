package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class petcard extends AppCompatActivity {

    EditText pet_name, pet_birthday, pet_gender, pet_breed, pet_species, pet_date, pet_againts, pet_manufacturer, pet_veterinarian;
    Calendar calendar;
    String str_name, str_birthday, str_gender, str_breed, str_species, str_date, str_manufacturer, str_againts, str_veterinarian;

    private static final String url = "https://christoherhonrado.000webhostapp.com/petcard.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petcard);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#0F2350'>Pet Card</font>"));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));

        pet_name = findViewById(R.id.petname);

        pet_gender = findViewById(R.id.gender);
        pet_breed = findViewById(R.id.breed);
        pet_species = findViewById(R.id.species);
        pet_againts = findViewById(R.id.againts);
        pet_manufacturer = findViewById(R.id.manufacturer);
        pet_veterinarian = findViewById(R.id.veterinarian);
//Input Date Picker
        pet_birthday = findViewById(R.id.birthday);
        pet_date = findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayofmonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayofmonth);
                updateCalendar();
            }

            private void updateCalendar() {
                String Format = "EEE, d MMM yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.US);
                pet_birthday.setText(sdf.format(calendar.getTime()));
            }
        };
        pet_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(petcard.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

//date picker end


    Calendar datecalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener datecalen = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayofmonth) {
            datecalendar.set(Calendar.YEAR, year);
            datecalendar.set(Calendar.MONTH, month);
            datecalendar.set(Calendar.DAY_OF_MONTH, dayofmonth);
            updatedate();
        }

        private void updatedate() {
            String Format = "EEE, d MMM yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.US);
            pet_date.setText(sdf.format(datecalendar.getTime()));
        }
    };
     pet_date.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        new DatePickerDialog(petcard.this, datecalen, datecalendar.get(Calendar.YEAR), datecalendar.get(Calendar.MONTH), datecalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    });
}
    public void submit(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Submitting Please Wait...");

        if (pet_name.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Pet Name", Toast.LENGTH_SHORT).show();
        } else if (pet_birthday.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Pet Birthday", Toast.LENGTH_SHORT).show();
        } else if (pet_breed.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Pet Bread", Toast.LENGTH_SHORT).show();
        } else if (pet_gender.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Pet Gender", Toast.LENGTH_SHORT).show();
        } else if (pet_species.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Pet Species", Toast.LENGTH_SHORT).show();
        } else if (pet_date.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Date", Toast.LENGTH_SHORT).show();
        } else if (pet_againts.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Againts", Toast.LENGTH_SHORT).show();
        } else if (pet_manufacturer.getText().toString().equals("")) {
            Toast.makeText(this, "Enter manufacturer", Toast.LENGTH_SHORT).show();
        } else if (pet_veterinarian.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Veterinarian", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();
            str_name = pet_name.getText().toString().trim();
            str_birthday = pet_birthday.getText().toString().trim();
            str_breed = pet_breed.getText().toString().trim();
            str_gender = pet_gender.getText().toString().trim();
            str_species = pet_species.getText().toString().trim();
            str_date = pet_date.getText().toString().trim();
            str_againts = pet_againts.getText().toString().trim();
            str_manufacturer = pet_manufacturer.getText().toString().trim();
            str_veterinarian = pet_veterinarian.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Toast.makeText(petcard.this, response, Toast.LENGTH_SHORT).show();
                    pet_name.setText("");
                    pet_birthday.setText("");
                    pet_breed.setText("");
                    pet_gender.setText("");
                    pet_species.setText("");
                    pet_date.setText("");
                    pet_againts.setText("");
                    pet_manufacturer.setText("");
                    pet_veterinarian.setText("");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(petcard.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", str_name);
                    params.put("birthday", str_birthday);
                    params.put("breed", str_breed);
                    params.put("gender", str_gender);
                    params.put("species", str_species);
                    params.put("date", str_date);
                    params.put("againts", str_againts);
                    params.put("manufacturer", str_manufacturer);
                    params.put("veterinarian", str_veterinarian);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        }
    }

}