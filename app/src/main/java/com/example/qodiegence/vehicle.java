package com.example.qodiegence;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class vehicle  {
    String Vname,Vmodel,Vnumber;

    public vehicle(String vname, String vmodel, String vnumber) {
        Vname = vname;
        Vmodel = vmodel;
        Vnumber = vnumber;
    }

    public String getVname() {
        return Vname;
    }

    public String getVmodel() {
        return Vmodel;
    }

    public String getVnumber() {
        return Vnumber;
    }
}
