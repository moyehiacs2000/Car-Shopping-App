package com.example.carshopping;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private RecyclerView recyclerview_search;
    private AdapterSearch mAdapter;
    private ArrayList<String> dates;
    private RadioButton radiobutton_popular,radiobutton_recomended;
    GetAllCar c;
    getRecomended Rec;
    String UserID;
    EditText editText;
    ArrayList<CarType> cars;
    public SearchFragment(String UserID) {
        // Required empty public constructor
        this.UserID=UserID;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_search, container, false);
        recyclerview_search=v.findViewById(R.id.recyclerview_search);
        radiobutton_popular=v.findViewById(R.id.radiobutton_popular);
        editText=v.findViewById(R.id.editText);
        radiobutton_popular.setChecked(true);
        radiobutton_recomended=v.findViewById(R.id.radiobutton_recomended);
        //RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerview_search.setLayoutManager(layoutManager);
        c=new GetAllCar();
         cars=new ArrayList(c.GetCars(getContext()));
        radiobutton_recomended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rec=new getRecomended();
                Rec.setGetRecomendedCarIds(UserID);
                ArrayList<CarType>car=new ArrayList(Rec.getRecomendedCar());
                mAdapter=new AdapterSearch(car,getActivity(),UserID,false,false);
                recyclerview_search.setAdapter(mAdapter);
                if(cars.size()==0) {
                    Toast.makeText(getContext(), "Sorry : React First", Toast.LENGTH_LONG).show();
                }
            }
        });
        radiobutton_popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter=new AdapterSearch(cars,getActivity(),UserID,false,false);
                recyclerview_search.setAdapter(mAdapter);
            }
        });
      if(radiobutton_popular.isChecked()){

          mAdapter=new AdapterSearch(cars,getActivity(),UserID,false,false);
          recyclerview_search.setAdapter(mAdapter);
      }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString(),cars);
            }
        });

        return v;
    }
    private void filter(String text,ArrayList<CarType>cars){
        ArrayList<CarType>filted_list=new ArrayList<CarType>();
        for(CarType item:cars){
            if(item.getCarName().toLowerCase().contains(text.toLowerCase())){
                filted_list.add(item);
            }
        }
        mAdapter.filterlist(filted_list);
    }

}