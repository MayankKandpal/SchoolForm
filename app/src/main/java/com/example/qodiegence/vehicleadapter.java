package com.example.qodiegence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class vehicleadapter extends RecyclerView.Adapter<vehicleadapter.VadapterViewHolder>{
    ArrayList<vehicle> vehiclelist;
    Context context;

    public vehicleadapter(ArrayList<vehicle> vehiclelist, Context context) {
        this.vehiclelist = vehiclelist;
        this.context = context;
    }

    @NonNull
    @Override
    public VadapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview =  LayoutInflater.from(context).inflate(R.layout.vehicle_details,parent,false);
        return new VadapterViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull VadapterViewHolder holder, int position) {
    vehicle v1 = vehiclelist.get(position);
    holder.tvVname.setText(v1.getVname());
    holder.tvVmodel.setText(v1.getVmodel());
    holder.tvVnumber.setText(v1.getVnumber());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class VadapterViewHolder extends RecyclerView.ViewHolder{
        TextView tvVname,tvVmodel,tvVnumber;
        public VadapterViewHolder(View view){
            super(view);
            tvVmodel = view.findViewById(R.id.tvVmodel);
            tvVname = view.findViewById(R.id.tvVname);
            tvVnumber = view.findViewById(R.id.tvVnumber);

        }

    }

}
