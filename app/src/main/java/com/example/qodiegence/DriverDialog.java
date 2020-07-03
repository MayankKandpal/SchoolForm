package com.example.qodiegence;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DriverDialog extends AppCompatDialogFragment {
    EditText etDName,etDLicense;
    driverdialoglistener listener2;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view2 = inflater.inflate(R.layout.driverdetails,null);
        builder2.setView(view2).setTitle("Drivers").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
              String dname = etDName.getText().toString().trim();
              String dlicense =  etDLicense.getText().toString().trim();
              listener2.applytext(dname,dlicense);
            }
        });
        etDName = view2.findViewById(R.id.etDName);
        etDLicense = view2.findViewById(R.id.etDLicense);
        return builder2.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener2 = (driverdialoglistener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public interface driverdialoglistener{
        void applytext (String dname,String dlicense);
    }
}
