package com.example.freshapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder>{

    static Context context;
    ArrayList<Announcement> list;

    public AnnouncementAdapter(Context context, ArrayList<Announcement> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(context).inflate(R.layout.announcementlist, parent, false);
         return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Announcement cont  = list.get(position);
        holder.title.setText(cont.getTitle());
        holder.content.setText(cont.getContent());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlename);
            content = itemView.findViewById(R.id.content);
        }
    }
}
