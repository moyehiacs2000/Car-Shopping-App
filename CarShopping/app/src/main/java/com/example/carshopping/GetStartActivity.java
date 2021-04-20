package com.example.carshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class GetStartActivity extends AppCompatActivity {
    Animation lefttoright,righttoleft;
    TextView RoomBookingApp,textview_info;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start2);
        RoomBookingApp=findViewById(R.id.textview_title);
        textview_info=findViewById(R.id.textview_info);
        view=findViewById(R.id.line);
        lefttoright= AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        righttoleft= AnimationUtils.loadAnimation(this,R.anim.righttoleft);
        RoomBookingApp.setAnimation(righttoleft);
        view.setAnimation(righttoleft);
        Button button_getStarted=(Button)findViewById(R.id.button_getstart);
        button_getStarted.setAnimation(lefttoright);
        textview_info.setAnimation(lefttoright);
        button_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(GetStartActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}