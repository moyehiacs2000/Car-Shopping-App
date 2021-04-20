package com.example.carshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPopular extends RecyclerView.Adapter<AdapterPopular.MyViewHolder> {
    private List<String> data;
    private Context context;
    public AdapterPopular(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPopular.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_popular,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPopular.MyViewHolder holder, int position) {
        int left = (int) context.getResources().getDimension(R.dimen.margin8dp);
        int right = (int) context.getResources().getDimension(R.dimen.margin8dp);
        int bottom = (int) context.getResources().getDimension(R.dimen.margin8dp);

        if (position == 0){
            left = (int) context.getResources().getDimension(R.dimen.marginSize);
        }else{
            left = 0;
        }

        if (position==data.size()-1){
            right = (int) context.getResources().getDimension(R.dimen.marginSize);
        }

        holder.textview_item_room_name.setText(data.get(position));
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(holder.itemView.getLayoutParams());
        marginLayoutParams.setMargins(left,0,right,bottom);
        holder.itemView.setLayoutParams(marginLayoutParams);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textview_item_room_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_item_room_name = (TextView)itemView.findViewById(R.id.textview_item_room_name);
        }
    }
}
