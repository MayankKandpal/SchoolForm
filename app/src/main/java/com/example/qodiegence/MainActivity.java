package com.example.qodiegence;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    TextInputEditText etName,etPhone,etEmail,etAddress,etYear,etLocation;
    Button imagebtn;
    Button button,btnOpen,btnClose;
    DatabaseReference dbref;
    TextView tvOpen,tvClose;
    ImageView imView;
    private Uri filePath;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    int t1hour,t1minute,t2hour,t2minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etLocation = findViewById(R.id.etLocation);
        etYear = findViewById(R.id.etYear);
        dbref = FirebaseDatabase.getInstance().getReference("School" );
        button = findViewById(R.id.button);
        btnOpen =findViewById(R.id.btnOpen);
        btnClose = findViewById(R.id.btnClose);
        imagebtn = findViewById(R.id.imgagebtn);
        imView = findViewById(R.id.imView);
        btnOpen = findViewById(R.id.btnOpen);
        btnClose = findViewById(R.id.btnClose);
        tvClose = findViewById(R.id.tvClose);
        tvOpen =(TextView)findViewById(R.id.tvOpen);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t2hour = hourOfDay;
                        t2minute =minute;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,t2hour,t2minute);
                        tvClose.setText(DateFormat.format("hh:mm aa",calendar));
                    }
                },12,0,false);
                timePickerDialog2.updateTime(t2hour,t2minute);
                timePickerDialog2.show();

            }
        });
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1hour = hourOfDay;
                        t1minute =minute;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,t1hour,t1minute);
                        tvOpen.setText(DateFormat.format("hh:mm aa",calendar));
                    }
                },12,0,false);
                timePickerDialog.updateTime(t1hour,t1minute);
                timePickerDialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               addData();
               uploadImage();
           }
       });
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
            }
        });

    }

    private void uploadImage() {
        StorageReference reference = storageReference.child("images/"+ UUID.randomUUID().toString());
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Uploading..");
        progressDialog.show();
        reference.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setMessage("Uploaded"+(int)progress+"%");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void addData() {
     String name = etName.getText().toString().trim();
     String phone = etPhone.getText().toString().trim();
     String email = etPhone.getText().toString().trim();
     String address = etAddress.getText().toString().trim();
     String location = etLocation.getText().toString().trim();
     String year = etYear.getText().toString().trim();
     String opentime = tvOpen.getText().toString().trim();
     String closetime = tvClose.getText().toString().trim();
     example Example = new example(name,phone,email,address,location,year,opentime,closetime);
     String id = String.valueOf(System.currentTimeMillis());
     dbref.child(id).push().setValue(Example);
        Toast.makeText(this, "School Added", Toast.LENGTH_SHORT).show();
    }
}