package com.example.carshopping;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdabterArticles extends RecyclerView.Adapter<AdabterArticles.ViewHolder> {
    ArrayList<brands> list;
    Context context;
    MainActivity m;
    RadioButton radiobutton_Search;
    String Userid;
    public AdabterArticles(ArrayList<brands> list, Context context,RadioButton radiobutton_Search,  String Userid) {
        this.list = list;
        this.context = context;
        this.radiobutton_Search=radiobutton_Search;
        this.Userid=Userid;
        m=new MainActivity();
    }
    @NonNull
    @Override
    public AdabterArticles.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.view_articles,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdabterArticles.ViewHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).getBrandName().toString());
        Picasso.get().load(list.get(position).getBrandimg()).into(holder.imgArticle);
        holder.imgArticle.setOnClickListener(new View.OnClickListener() {
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
        public ImageView imgArticle;
        public TextView tvTitle,tvDesc;
        public Button btReadMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgArticle=itemView.findViewById(R.id.imgArticle);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDesc=itemView.findViewById(R.id.tvDesc);

        }
    }
}
