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

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class slideshowadapter extends PagerAdapter  {
    private Context context;
    LayoutInflater layoutInflater;
    GestureDetector gestureDetector;
    public CarType car;
    String UserID;
    GetAllCar favCar=new GetAllCar();
    ArrayList<String> favCarList;
    boolean fromfav=false;
    boolean fromSimilar=false;

    //String pos;
    public slideshowadapter(Context context,CarType car,String UserID,boolean fromfav,boolean fromSimilar){
        this.context=context;
        this.car=car;
        this.UserID=UserID;
        this.fromfav=fromfav;
        this.fromSimilar=fromSimilar;
        favCarList=favCar.GetFavCarsIDS(context,UserID);
    }
    @Override
    public int getCount() {
        if(fromSimilar)  return 1;
        if(car.photos.size()<6)return car.photos.size();
        return 6;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slidshow_layout,container,false);
        ImageView img=view.findViewById(R.id.imgSlide);
        TextView Mileage =view.findViewById(R.id.mileage);
        final ImageView Favorite=view.findViewById(R.id.Favorite);
        final ImageView RedHeart=view.findViewById(R.id.redheart);
        TextView carprice=view.findViewById(R.id.carprice);
        TextView Brandname=view.findViewById(R.id.tvfBrand);
        TextView CarName=view.findViewById(R.id.carName);
        TextView txtPrice=view.findViewById(R.id.Price);
        if(!fromfav){
            for(int i=0;i<favCarList.size();i++){
                if(favCarList.get(i).equals(car.getTypeNo())){
                    Favorite.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_red_favorite_24));
                    break;
                }
            }
        }
        else{
            Favorite.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_red_favorite_24));
        }
        if(position==0){
            Mileage.setVisibility(View.VISIBLE);
            Favorite.setVisibility(View.VISIBLE);
            RedHeart.setVisibility(View.VISIBLE);
            carprice.setVisibility(View.VISIBLE);
            Brandname.setVisibility(View.VISIBLE);
            CarName.setVisibility(View.VISIBLE);
            txtPrice.setVisibility(View.VISIBLE);
            RedHeart.setScaleX(0);
            RedHeart.setScaleY(0);
            Brandname.setText(car.BrandName);
            CarName.setText(car.getCarName());
            Mileage.setText(car.getMileage());
            carprice.setText(car.getPrice());
        }
        else{
            Mileage.setVisibility(View.GONE);
            txtPrice.setVisibility(View.GONE);
            Favorite.setVisibility(View.GONE);
            RedHeart.setVisibility(View.GONE);
            carprice.setVisibility(View.GONE);
            Brandname.setVisibility(View.GONE);
            CarName.setVisibility(View.GONE);
        }
        Picasso.get().load(car.photos.get(position)).into(img);
        container.addView(view);
       Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recomend r=new recomend(UserID,car.getTypeNo(),context);
                RedHeart.animate().scaleY(1).scaleX(1).setDuration(400).start();
                 Favorite.setImageResource(R.drawable.ic_red_favorite_24);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       RedHeart.animate().scaleY(0).scaleX(0).setDuration(400).start();
                    }
                },2000);

                ConnectDatabase db = new ConnectDatabase();
                java.sql.Connection con = db.connectDB();
                if (con == null) {
                    Toast.makeText(context, "Please Check Internet Access", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Statement stmt = con.createStatement();
                        int x = stmt.executeUpdate("insert into Favorits values('" + UserID + "','" + car.getTypeNo() + "')");
                        if (x == 0) {
                           Toast.makeText(context,"Sorry Try Again",Toast.LENGTH_LONG).show();
                        }
                    } catch (SQLException e) {
                        Toast.makeText(context, "Sorry Error" + e.getMessage() + e.getErrorCode(),Toast.LENGTH_LONG).show();
                    }

                    }
                  }

        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,CarProfileActivity.class);
                i.putExtra("TypeNo",car.getTypeNo());
                i.putExtra("UserID",UserID);
                i.putExtra("TypeBrand",car.getBrandNo());
                context.startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object) ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(RelativeLayout)object);
    }

}
