package com.example.qodiegence;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.xwray.groupie.GroupAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class AddVehicles extends AppCompatActivity implements DriverDialog.driverdialoglistener{
    EditText etVName,etVModel,etVNumber;
  DatabaseReference ref,refdriver;
  StorageReference str;
  RecyclerView rcview;
  Uri docpath;
  String DName,DLicense;
  ImageView imdoc;
  ArrayList<vehicle> vehiclelist;
  String id = String.valueOf(System.currentTimeMillis());
  Button btnDoc,btnDriver,btnSave,saveDriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicles);
        btnDoc = findViewById(R.id.btnDoc);
        etVName = findViewById(R.id.etVName);
        etVModel =findViewById(R.id.etVModel);
        etVNumber = findViewById(R.id.etVNumber);
        btnDriver = findViewById(R.id.btnDriver);
        btnSave = findViewById(R.id.btnSave);
        saveDriver = findViewById(R.id.saveDriver);
        rcview = findViewById(R.id.rcview);
        imdoc = findViewById(R.id.imdoc);
        refdriver = FirebaseDatabase.getInstance().getReference("Drivers");
        ref = FirebaseDatabase.getInstance().getReference("Vehicles");
        str = FirebaseStorage.getInstance().getReference("Documents");
        vehicleadapter adapter = new vehicleadapter(vehiclelist,this);
        rcview.setAdapter(adapter);
        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DriverDialog driverDialog = new DriverDialog();
                driverDialog.show(getSupportFragmentManager(),"Driver Dialog");
                Toast.makeText(AddVehicles.this, "Driver Added", Toast.LENGTH_SHORT).show();
            }
        });
        btnDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Document Image"),1);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vname = etVName.getText().toString().trim();
                String vmodel = etVModel.getText().toString().trim();
                String vnumber = etVNumber.getText().toString().trim();
                vehicle vehicle = new vehicle(vname,vmodel,vnumber);
                ref.child(id).setValue(vehicle);
                uploadDoc();
               // fetchdata();
            }
        });
        saveDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driver Driver = new driver(DName,DLicense);
                String id = String.valueOf(System.currentTimeMillis());
                refdriver.child(id).setValue(Driver);
            }
        });
    }

    private void fetchdata() {
    ref.child(id).addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        vehicle data =snapshot.getValue(vehicle.class);
        vehiclelist.add(data);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            docpath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), docpath);
                imdoc.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        private void uploadDoc() {
        StorageReference reference = str.child("images/"+ UUID.randomUUID().toString());
        final ProgressDialog progressDialog = new ProgressDialog(AddVehicles.this);
        progressDialog.setTitle("Uploading..");
        progressDialog.show();
        reference.putFile(docpath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                Toast.makeText(AddVehicles.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
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
    public void applytext(String dname, String dlicense) {
        DName = dname;
        DLicense = dlicense;
    }

}