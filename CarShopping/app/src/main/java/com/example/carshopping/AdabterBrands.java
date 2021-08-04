package com.example.carshopping;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdabterBrands extends RecyclerView.Adapter<AdabterBrands.ViewHolder> {
    ArrayList<brands> list;
    Context context;
    RadioButton radiobutton_Search;
    String Userid;
    public AdabterBrands(ArrayList<brands> list, Context context, RadioButton radiobutton_Search, String Userid) {
        this.list = list;
        this.context = context;
        this.radiobutton_Search=radiobutton_Search;
        this.Userid=Userid;
    }
    @NonNull
    @Override
    public AdabterBrands.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.view_brand,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdabterBrands.ViewHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).getBrandName().toString());
        Picasso.get().load(list.get(position).getBrandimg()).into(holder.imgBrand);
        holder.imgBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(context,BrandActivity.class);
               i.putExtra("UserId",Userid);
               i.putExtra("BrandID",list.get(position).getBrandId().toString());
               i.putExtra("BrandName",list.get(position).getBrandName().toString());
               context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgBrand;
        public TextView tvTitle,tvDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBrand=itemView.findViewById(R.id.imgBrand);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDesc=itemView.findViewById(R.id.tvDesc);

        }
    }
}
