package com.example.carshopping;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView rvArticles;
    AdabterArticles mAdapter;
    RadioButton radiobutton_Search;
    private ProgressDialog progressDialog;
    String UserID;
    public HomeFragment( RadioButton radiobutton_Search,String UserID) {
        // Required empty public constructor
        this.radiobutton_Search=radiobutton_Search;
        this.UserID=UserID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v=inflater.inflate(R.layout.fragment_home, container, false);
        rvArticles=v.findViewById(R.id.rvArticles);
        rvArticles.setHasFixedSize(true);
        progressDialog = ProgressDialog.show(getContext()
                ,"Please Wait","Sending mail....",true,false);
        final RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rvArticles.setLayoutManager(layoutManager);
        ArrayList<brands> dataArticles=new ArrayList<brands>();
        ConnectDatabase dp=new ConnectDatabase();
        ArrayList<brands> BrandList=new ArrayList<brands>();
        String selectQuery="select * from Brands";
        Connection conn=dp.connectDB();
        if(conn!=null){
            Statement stmt=null;
            try{
                stmt=conn.createStatement();
                ResultSet t=stmt.executeQuery(selectQuery);
                t.next();
                do{
                    brands contact=new brands();
                    contact.setBrandId(t.getString(1));
                    contact.setBrandName(t.getString(2));
                    contact.setBrandimg(t.getString(3));
                    BrandList.add(contact);
                }while(t.next());
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        else{
            Toast.makeText(getActivity(),"error",Toast.LENGTH_LONG).show();
        }
        mAdapter=new AdabterArticles(BrandList,getActivity(),radiobutton_Search,UserID);
        rvArticles.setAdapter(mAdapter);
        rvArticles.setPadding(30,20,30,40);

        final SnapHelper snapHelper=new LinearSnapHelper() ;
        snapHelper.attachToRecyclerView(rvArticles);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RecyclerView.ViewHolder viewHolder=rvArticles.findViewHolderForAdapterPosition(0);
                RelativeLayout rl1=viewHolder.itemView.findViewById(R.id.rl1);
                rl1.animate().scaleY(1).scaleX(1).setDuration(350).setInterpolator(new AccelerateInterpolator()).start();
            }
        },100);
        rvArticles.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View v=snapHelper.findSnapView(layoutManager);
                int pos=layoutManager.getPosition(v);
                RecyclerView.ViewHolder viewHolder =rvArticles.findViewHolderForAdapterPosition(pos);
                RelativeLayout rl1=viewHolder.itemView.findViewById(R.id.rl1);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    rl1.animate().setDuration(350).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
                }
                else{
                    rl1.animate().setDuration(350).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        progressDialog.dismiss();
        return v;
    }
}