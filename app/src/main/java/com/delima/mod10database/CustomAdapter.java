package com.delima.mod10database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList userid, username, password;
    int position;
    Activity activity;

    CustomAdapter(Activity activity, Context context, ArrayList userid, ArrayList username, ArrayList password){
        this.activity = activity;
        this.context = context;
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;
        holder.userid.setText(String.valueOf(userid.get(position)));
        holder.username.setText(String.valueOf(username.get(position)));
        holder.password.setText(String.valueOf(password.get(position)));
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateUser.class);
            intent.putExtra("userid", String.valueOf(userid.get(position)));
            intent.putExtra("username", String.valueOf(username.get(position)));
            intent.putExtra("password", String.valueOf(password.get(position)));
            activity.startActivityForResult(intent, 1);
        });

    }

    @Override
    public int getItemCount() {
        return userid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userid, username, password;
        LinearLayout mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userid = itemView.findViewById(R.id.tvUserID);
            username = itemView.findViewById(R.id.tvUsername);
            password = itemView.findViewById(R.id.tvPassword);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
