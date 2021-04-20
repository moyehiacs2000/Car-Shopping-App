package com.example.carshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class BrandActivity extends AppCompatActivity {
    private RecyclerView recyclerview_search;
    private AdapterSearch mAdapter;
    private TextView BrandName;
    GetAllCar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        Intent i=getIntent();
        String UserID=i.getStringExtra("UserId");
        String BrandID=i.getStringExtra("BrandID");
        String BrandN=i.getStringExtra("BrandName");
        recyclerview_search=findViewById(R.id.recyclerview_Brand);
        BrandName=findViewById(R.id.tvBrandName);
        BrandName.setText(BrandN);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview_search.setLayoutManager(layoutManager);
        c=new GetAllCar();
        ArrayList<CarType> cars=new ArrayList(c.GetBrandCars(this,BrandID));
        mAdapter=new AdapterSearch(cars,this,UserID,false,false);
        recyclerview_search.setAdapter(mAdapter);
    }
}