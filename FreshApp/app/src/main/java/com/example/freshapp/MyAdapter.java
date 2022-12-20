package com.example.freshapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshapp.pages.ReserveActivity;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    static Context context;
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
        holder.city.setText(user.getCity());
        holder.type.setText(user.getType());
        holder.weight.setText(user.getWeight());
        holder.price.setText(user.getPrice());
        holder.description.setText(user.getDescription());
        holder.sale_id.setText(user.getSale_id());
        holder.user_id.setText(user.getUser_id());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView city,type,weight,price,description,sale_id,user_id;
        Button rebutton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.cityname);
            type = itemView.findViewById(R.id.typename);
            weight = itemView.findViewById(R.id.weightvalue);
            price = itemView.findViewById(R.id.pricevalue);
            description = itemView.findViewById(R.id.description);
            sale_id = itemView.findViewById(R.id.saleId);
            user_id = itemView.findViewById(R.id.userid);

            rebutton = itemView.findViewById(R.id.reserve_button);

            rebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent reserveIntent = new Intent(context, ReserveActivity.class);
                    reserveIntent.putExtra("sale_id",sale_id.getText()).toString();
                    context.startActivity(reserveIntent);


                }
            });

        }
    }
}
