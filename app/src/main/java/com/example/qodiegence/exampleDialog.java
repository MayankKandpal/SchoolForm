package com.example.qodiegence;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class exampleDialog extends AppCompatDialogFragment {
    EditText etTFee,etLFee,etTFee2,etLFee2,tvFirst,tvFirst2;
    CheckBox cbT1,cbT2,cbT3,cbT4;
    ExampleDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout,null);
        builder.setView(view).setTitle("Driver Details").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

        }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                String name1 = tvFirst.getText().toString().trim();
                Boolean training1 = cbT1.isChecked();
                Boolean license1 = cbT2.isChecked();
                String tfee1 = etTFee.getText().toString();
                String lfee1 = etLFee.getText().toString();
                String name2 = tvFirst2.getText().toString();
                Boolean training2 = cbT3.isChecked();
                Boolean license2 = cbT4.isChecked();
                String tfee2 = etTFee2.getText().toString();
                String lfee2 = etLFee2.getText().toString();
                listener.applyTexts(name1,training1,license1,tfee1,lfee1,name2,training2,license2,tfee2,lfee2);
            }
        });
        tvFirst = view.findViewById(R.id.tvFirst);
        tvFirst2 = view.findViewById(R.id.tvFirst2);
        etLFee = view.findViewById(R.id.etLFee);
        etTFee = view.findViewById(R.id.etTFee);
        etLFee2 = view.findViewById(R.id.etLFee2);
        etTFee2 = view.findViewById(R.id.etTFee2);
        cbT1 = view.findViewById(R.id.cbT1);
        cbT2 = view.findViewById(R.id.cbT2);
        cbT3 = view.findViewById(R.id.cbT3);
        cbT4 = view.findViewById(R.id.cbT4);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener)context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface ExampleDialogListener{
        void applyTexts(String name1,Boolean training1,Boolean license1,String tfee1,String lfee1,String name2,Boolean training2,Boolean license2,String tfee2,String lfee2);

        void applyTexts();
    }
}

