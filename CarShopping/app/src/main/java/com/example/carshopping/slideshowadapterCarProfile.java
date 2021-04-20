package com.example.carshopping;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

public class slideshowadapterCarProfile extends PagerAdapter {
    private Context context;
    LayoutInflater layoutInflater;
    GestureDetector gestureDetector;
    public CarType car;
    //String pos;
    public slideshowadapterCarProfile(Context context,CarType car){
        this.context=context;
        this.car=car;
    }

    @Override
    public int getCount() {
        if(car.photos.size()<6)return car.photos.size();

        return 6;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slidshow_layoutcarprofile,container,false);
        ImageView img=view.findViewById(R.id.imgSlideCarProfile);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,FullScreenActivity.class);
                i.putStringArrayListExtra("photo",car.getPhotos());//Extra("TypeNo",car.getTypeNo());
                context.startActivity(i);
            }
        });
        Picasso.get().load(car.photos.get(position)).into(img);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object) ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }
}
