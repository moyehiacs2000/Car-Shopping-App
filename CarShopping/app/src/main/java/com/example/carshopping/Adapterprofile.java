package com.example.carshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapterprofile extends RecyclerView.Adapter<Adapterprofile.MyViewHolder> {
    private List<String> data;
    List<Integer> dataiamge;
    private Context context;
    public Adapterprofile(List<String> data, List<Integer> dataiamge, Context context) {
        this.data = data;
        this.context = context;
        this.dataiamge=dataiamge;
    }

    @NonNull
    @Override
    public Adapterprofile.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_profile,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterprofile.MyViewHolder holder, int position) {
     holder.textview_item_profile.setText(data.get(position));
     holder.imageview_item_profile.setImageResource(dataiamge.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageview_item_profile;
        private TextView textview_item_profile;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_item_profile = (TextView)itemView.findViewById(R.id.textview_item_profile);
            imageview_item_profile = (ImageView) itemView.findViewById(R.id.imageview_item_profile);
        }
    }
}
