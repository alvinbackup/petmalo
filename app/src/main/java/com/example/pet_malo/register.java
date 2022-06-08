package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pet_malo.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class register extends AppCompatActivity implements View.OnClickListener {

    private TextView registerUser;
    private EditText editTextFirstName,editTextLastName, editTextAddress,editTextContact, editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private Button banner;



    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        banner = (Button) findViewById(R.id.banner);
        banner.setOnClickListener(this);
        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFirstName = (EditText) findViewById(R.id.fullname);
        editTextLastName = (EditText) findViewById(R.id.lastname);
        editTextAddress = (EditText) findViewById(R.id.address);
        editTextContact= (EditText) findViewById(R.id.contact);
        editTextEmail = (EditText) findViewById(R.id.ed_email);
        editTextPassword = (EditText) findViewById(R.id.ed_password);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }

    }
    private void registerUser(){
    String email = editTextEmail.getText().toString().trim();
    String password = editTextPassword.getText().toString().trim();
    String firstname = editTextFirstName.getText().toString().trim();
    String lastname = editTextLastName.getText().toString().trim();
    String address = editTextAddress.getText().toString().trim();
    String contact = editTextContact.getText().toString().trim();

    if(firstname.isEmpty()){
        editTextFirstName.setError("First Name is Required!");
        editTextFirstName.requestFocus();
        return;
    }
    if(lastname.isEmpty()){
            editTextLastName.setError("Last Name is Required!");
            editTextLastName.requestFocus();
            return;
        }
    if(address.isEmpty()){
        editTextAddress.setError("Address is required!");
        editTextAddress.requestFocus();
        return;
        }

        if(contact.isEmpty()){
            editTextContact.setError("Contact is required!");
            editTextContact.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("please provide valid Email");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            editTextPassword.setError("Password should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(firstname, lastname, address, contact, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(register.this, " User has been REGISTERED!", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);

                                        //redirect to login Layout!
                                }else{
                                    Toast.makeText(register.this, "Failed to Register! Try again!", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }

                                }

                            });
                        }else{
                            Toast.makeText(register.this, "Failed to Register! Email is taken!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

        }
}