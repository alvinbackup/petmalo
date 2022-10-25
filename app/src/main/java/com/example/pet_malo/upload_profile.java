package com.example.pet_malo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class upload_profile extends AppCompatActivity {
    private ProgressBar progressBar;
    private ImageView imageViewUploadPic;
    private FirebaseAuth authprofile;
    private StorageReference storageReference;
    private FirebaseUser firebaseUser;
    private static final int  PICK_IMAGE_REQUEST = 1;
    private Uri  uriImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile);

        authprofile= FirebaseAuth.getInstance();

        Button buttononuploadchoose = findViewById(R.id.upload_pic_choose_button);
        Button buttonUploadpic = findViewById(R.id.upload_pic_button);
        progressBar = findViewById(R.id.progressBar);

        imageViewUploadPic=findViewById(R.id.imageView_profile_dp);

        authprofile= FirebaseAuth.getInstance();
        firebaseUser=authprofile.getCurrentUser();

        storageReference= FirebaseStorage.getInstance().getReference("DisplayPics");

        Uri uri = firebaseUser.getPhotoUrl();

        Glide.with(upload_profile.this).load(uri).into(imageViewUploadPic);


        buttononuploadchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        //UPLOAD IMAGE
        buttonUploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Uploadpic();
            }
        });

    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            uriImage = data.getData();
            imageViewUploadPic.setImageURI(uriImage);

        }
    }

    private void Uploadpic(){
        if (uriImage!=null){
            //save the image with uid of the currently logged user

            StorageReference fileReference =storageReference.child(authprofile.getCurrentUser().getUid() +"."
                    + getFileExtension(uriImage));
            //Upload Image to Storage
            fileReference.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUri = uri;
                            firebaseUser = authprofile.getCurrentUser();

                            //Finally Set the Display image of the User After Upload
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(downloadUri).build();
                            firebaseUser.updateProfile(profileUpdates);

                        }
                    });
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(upload_profile.this, "Upload SuccessFul!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(upload_profile.this, user_info.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(upload_profile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(upload_profile.this, "No File Selected!", Toast.LENGTH_SHORT).show();
        }
    }
    //obtain File Extension inte Image
    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
}