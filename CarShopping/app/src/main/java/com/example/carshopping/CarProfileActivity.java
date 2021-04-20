package com.example.carshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class CarProfileActivity extends AppCompatActivity {
    private RecyclerView recyclerview_popular;
    private AdapterSearch mAdapter;
    private List<String> datas;
    private Toolbar toolbar;
    private TextView txtautomatic,txtL,txtsuv,txtPetrol,txtdoor,txtSeats,txtfull,BrandName,CarName,Price,partDesc,txtreview;
    GetAllCar c;
    int[] sampleImages = {R.drawable.car, R.drawable.imageprofile};
    ViewPager viewPager;
    slideshowadapterCarProfile adabter;
    SpringDotsIndicator indicator;
    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_profile);
        recyclerview_popular = (RecyclerView)findViewById(R.id.recyclerview_popular);
        txtautomatic=findViewById(R.id.txtautomatic);
        txtL=findViewById(R.id.txtL);
        txtsuv=findViewById(R.id.txtsuv);
        txtPetrol=findViewById(R.id.txtPetrol);
        txtdoor=findViewById(R.id.txtdoor);
        txtSeats=findViewById(R.id.txtSeats);
        txtfull=findViewById(R.id.txtfull);
        BrandName=findViewById(R.id.BrandName);
        CarName=findViewById(R.id.CarName);
        Price=findViewById(R.id.Price);
        txtreview=findViewById(R.id.reviews);
        partDesc=findViewById(R.id.partDesc);
        recyclerview_popular.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplication(),RecyclerView.HORIZONTAL,false);
        recyclerview_popular.setLayoutManager(layoutManager);
        Intent i=getIntent();
        String TypeNo=i.getStringExtra("TypeNo");
        String UserID=i.getStringExtra("UserID");
        String BrandNo=i.getStringExtra("TypeBrand");
        c=new GetAllCar();
        ArrayList<CarType> cars=new ArrayList(c.GetSimilarCars(TypeNo,BrandNo));
        mAdapter = new AdapterSearch(cars,this,UserID,false,true);
        recyclerview_popular.setAdapter(mAdapter);
        viewPager = findViewById(R.id.viewpager);

        CarType car=c.getoneCar(TypeNo);
        txtautomatic.setText(car.getTxtautomatic());
        txtL.setText(car.getTxtL());
        txtsuv.setText(car.getTxtsuv());
        txtPetrol.setText(car.getTxtPetrol());
        txtdoor.setText(car.getTxtdoor());
        txtSeats.setText(car.getTxtSeats());
        txtfull.setText(car.getTxtfull());
        BrandName.setText(car.getBrandName());
        CarName.setText(car.getCarName());
        Price.setText("Price: "+car.getPrice());
        partDesc.setText(car.getPartDesc());
        txtreview.setText(car.getBrandName()+" "+car.getCarName()+" "+car.getTxtsuv()+" "+"(Reviews)");
        adabter=new slideshowadapterCarProfile(getApplication(),car);
        viewPager.setAdapter(adabter);
        indicator=findViewById(R.id.circleindecator);
        indicator.setViewPager(viewPager);

    }



}