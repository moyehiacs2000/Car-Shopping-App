package com.example.carshopping;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.MyViewHolder> {
    private ArrayList<CarType> data;
    private Context context;
    private slideshowadapter adabter;
    private String UserID;
    private boolean fromfav;
    boolean fromSimilar;

    public AdapterSearch(ArrayList<CarType> data, Context context,String UserID,boolean fromfav,   boolean fromSimilar) {
        this.data = data;
        this.context = context;
        this.UserID=UserID;
        this.fromfav=fromfav;
        this.fromSimilar=fromSimilar;
    }

    @Override
    public AdapterSearch.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_search,parent,false));
        //View v= LayoutInflater.from(context).inflate(R.layout.view_item_search,parent,false);
        //MyViewHolder viewHolder=new MyViewHolder(v);
        //return  viewHolder;
        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_search, null) ;
        return new MyViewHolder(view) ;*/
        View v= LayoutInflater.from(context).inflate(R.layout.view_item_search,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterSearch.MyViewHolder holder, final int position) {
        int top = (int) context.getResources().getDimension(R.dimen.margin8dp);
        int bottom = (int) context.getResources().getDimension(R.dimen.margin8dp);
        int left = (int) context.getResources().getDimension(R.dimen.marginSize);
        int right = (int) context.getResources().getDimension(R.dimen.marginSize);
        if (position == 0){
            top = 0;
        }else{
            top =  (int) context.getResources().getDimension(R.dimen.margin8dp);
        }

        if (position==data.size()-1){
            bottom= (int) context.getResources().getDimension(R.dimen.margin8dp);
        }
        if(fromSimilar){
            holder.indicator.setVisibility(View.GONE);
        }
        adabter=new slideshowadapter(context,data.get(position),UserID,fromfav,fromSimilar);
        holder.viewPager.setAdapter(adabter);
        holder.indicator.setViewPager( holder.viewPager);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(holder.itemView.getLayoutParams());
        marginLayoutParams.setMargins(left,top,right,bottom);
        holder.itemView.setLayoutParams(marginLayoutParams);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void filterlist( ArrayList<CarType>filted_list){
        data=filted_list;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ViewPager viewPager;
        private SpringDotsIndicator indicator;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            viewPager = itemView.findViewById(R.id.imageview_item_fav);
            indicator=itemView.findViewById(R.id.carindecator);


        }

    }
}
