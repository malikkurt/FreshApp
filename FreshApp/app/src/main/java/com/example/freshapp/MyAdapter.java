package com.example.freshapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Sales> list;

    public MyAdapter(Context context, ArrayList<Sales> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Sales user = list.get(position);
        holder.city.setText(user.getCityname());
        holder.type.setText(user.getTypename());
        holder.weight.setText(user.getWeightvalue());
        holder.price.setText(user.getPricevalue());
        holder.description.setText(user.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView city,type,weight,price,description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.cityname);
            type = itemView.findViewById(R.id.typename);
            weight = itemView.findViewById(R.id.weightvalue);
            price = itemView.findViewById(R.id.pricevalue);
            description = itemView.findViewById(R.id.description);

        }
    }
}
