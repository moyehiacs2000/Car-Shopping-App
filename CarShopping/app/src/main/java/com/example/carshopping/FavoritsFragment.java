package com.example.carshopping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FavoritsFragment extends Fragment {
    private RecyclerView recyclerview_fav;
    private AdapterSearch mAdapter;
    private ArrayList<String> dates;
    GetAllCar c;
    String UserID;
    public FavoritsFragment(String UserID) {
       this.UserID=UserID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.fragment_favorits, container, false);
        recyclerview_fav=v.findViewById(R.id.recyclerview_fav);
        //RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerview_fav.setLayoutManager(layoutManager);
        c=new GetAllCar();
        ArrayList<CarType> cars=new ArrayList(c.GetFavCars(getContext(),UserID));
        mAdapter=new AdapterSearch(cars,getActivity(),UserID,true,false);
        recyclerview_fav.setAdapter(mAdapter);
         return v;
    }
}