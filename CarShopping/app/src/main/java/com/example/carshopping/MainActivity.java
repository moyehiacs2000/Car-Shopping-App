package com.example.carshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    FrameLayout containe;
    RadioGroup radiogroup_home;
    RadioButton radiobutton_home,radiobutton_search,radiobutton_profile,radiobutton_fav;
    Fragment fragment;
    Toolbar toolbar;
    ImageView colorCircle;
    TextView tvBrands;
    View view;
    String UserName,UserEmail,Userphoto,UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        containe = (FrameLayout)findViewById(R.id.container);
        radiogroup_home = (RadioGroup)findViewById(R.id.radiogroup_home);
        radiobutton_home = (RadioButton)findViewById(R.id.radiobutton_home);
        radiobutton_search = (RadioButton)findViewById(R.id.radiobutton_Search);
        radiobutton_profile = (RadioButton)findViewById(R.id.radiobutton_profile);
        radiobutton_fav = (RadioButton)findViewById(R.id.fav);
        colorCircle = findViewById(R.id.colorCircle);
        toolbar=findViewById(R.id.toolbar);
        tvBrands=findViewById(R.id.tvBrands);
        view=findViewById(R.id.view);
        Intent i=getIntent();
         UserID=i.getStringExtra("UserId");
         UserName=i.getStringExtra("UserName");
         UserEmail=i.getStringExtra("UserEmail");
        radiobutton_home.setChecked(true);
        showFragment(new HomeFragment(radiobutton_search,UserID));

        radiogroup_home.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radiobutton_home :
                        toolbar.setVisibility(View.VISIBLE);
                        colorCircle.setVisibility(View.VISIBLE);
                        tvBrands.setVisibility(View.VISIBLE);
                        view.setVisibility(View.VISIBLE);
                        showFragment(new HomeFragment(radiobutton_search,UserID));
                        break;
                    case R.id.radiobutton_Search :
                        toolbar.setVisibility(View.GONE);
                        colorCircle.setVisibility(View.GONE);
                        tvBrands.setVisibility(View.GONE);
                        view.setVisibility(View.GONE);
                        showFragment(new SearchFragment(UserID));
                        break;
                    case R.id.radiobutton_profile :
                        toolbar.setVisibility(View.GONE);
                        colorCircle.setVisibility(View.GONE);
                        tvBrands.setVisibility(View.GONE);
                        view.setVisibility(View.GONE);
                        showFragment(new profileFragment(UserName,UserEmail,UserID));
                        break;
                    case R.id.fav :
                        toolbar.setVisibility(View.GONE);
                        colorCircle.setVisibility(View.GONE);
                        tvBrands.setVisibility(View.GONE);
                        view.setVisibility(View.GONE);
                        showFragment(new FavoritsFragment(UserID));
                        break;
                }
            }
        });

    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }
}