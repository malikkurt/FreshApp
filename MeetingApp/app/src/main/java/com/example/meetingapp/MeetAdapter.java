package com.example.meetingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MeetAdapter extends RecyclerView.Adapter<MeetAdapter.MyViewHolder> {

    static  Context context;
    ArrayList<Meet> list;

    public MeetAdapter(Context context, ArrayList<Meet> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MeetAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.meetlist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetAdapter.MyViewHolder holder, int position) {
        Meet meet = list.get(position);
        holder.city.setText(meet.getCity());
        holder.time.setText(meet.getTime());
        holder.concept.setText(meet.getConcept());
        holder.adress.setText(meet.getAdress());
        holder.description.setText(meet.getDescription());
        holder.meetId.setText(meet.getMeet_id());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView city,time, concept,adress,description,meetId;
        Button rebutton;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            city = itemView.findViewById(R.id.cityname);
            time = itemView.findViewById(R.id.time);
            concept = itemView.findViewById(R.id.concept);
            adress = itemView.findViewById(R.id.adress);
            description = itemView.findViewById(R.id.description);
            rebutton = itemView.findViewById(R.id.reserve_button);
            meetId = itemView.findViewById(R.id.meetid);

            rebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent reserveIntent = new Intent(context, ReserveActivity.class);
                    reserveIntent.putExtra("meet_id", meetId.getText().toString());
                    context.startActivity(reserveIntent);
                }
            });
        }

    }
}
