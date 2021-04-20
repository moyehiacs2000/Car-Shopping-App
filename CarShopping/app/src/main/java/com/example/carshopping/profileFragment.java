package com.example.carshopping;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class profileFragment extends Fragment {
    private RecyclerView recyclerView_profile;
    private Adapterprofile mAdapter;
    private Uri filePath;
    Bitmap bitmap;
    private List<String> datas=new ArrayList<>();
    private List<Integer> dataImages=new ArrayList<>();
    private ImageView imgProfile,imgclose,image_camera;
    private static final int PICK_IMAGE_REGUEST=234;
    private TextView textview_name,textview_email;
    private String UserName,UserEmail,Userphoto,UserID;
    private RelativeLayout rlv;
    private LinearLayout overbox;
    Animation fromsmall,fromnothing,forActive,togo;
    public profileFragment(String userName, String userEmail,String userID) {
        UserName = userName;
        UserEmail = userEmail;
        UserID = userID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView_profile=v.findViewById(R.id.recyclerview_profile);
        boolean is=false;
        ConnectDatabase db = new ConnectDatabase();
        java.sql.Connection con = db.connectDB();
        if (con == null) {
            Toast.makeText(getContext(), "Please Check Internet Access", Toast.LENGTH_LONG).show();
        }
       else{
            try {
                Statement stmt = con.createStatement();

                ResultSet n = stmt.executeQuery("select * from Usertable where ID='" + UserID + "'");

                if (n.next() == true) {
                    if(!n.getString(5).equals("empty")){
                        is=true;
                    Userphoto=n.getString(5);}

                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }}
        imgProfile=v.findViewById(R.id.circleimageview);
       if(is)
        imgProfile.setImageBitmap(convertToBitmap(Userphoto));
        textview_name=v.findViewById(R.id.textview_name);
        textview_name.setText(UserName);
        textview_email=v.findViewById(R.id.textview_email);
        imgclose=v.findViewById(R.id.imgclose);
        imgclose.setVisibility(View.GONE);
        rlv=v.findViewById(R.id.rlv);
        rlv.setVisibility(View.GONE);
        overbox=v.findViewById(R.id.overbox);
        image_camera=v.findViewById(R.id.image_camera);
        overbox.setVisibility(View.GONE);
        fromsmall= AnimationUtils.loadAnimation(getContext(),R.anim.fromsmall);
        fromnothing= AnimationUtils.loadAnimation(getContext(),R.anim.fromnothing);
        forActive= AnimationUtils.loadAnimation(getContext(),R.anim.fromactive);
        togo= AnimationUtils.loadAnimation(getContext(),R.anim.togo);
        textview_email.setText(UserEmail);
        recyclerView_profile.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        datas.add("Account");
        datas.add("Change Password");
        datas.add("Change Name");
        datas.add("Logout");
        dataImages.add(R.drawable.ic_account);
        dataImages.add(R.drawable.ic_lock_24);
        dataImages.add(R.drawable.ic_booking);
        dataImages.add(R.drawable.ic_logout);
        recyclerView_profile.setLayoutManager(layoutManager);
        mAdapter=new Adapterprofile(datas,dataImages,getActivity());
        recyclerView_profile.setAdapter(mAdapter);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                overbox.setVisibility(View.VISIBLE);
                rlv.setVisibility(View.VISIBLE);
                imgclose.setVisibility(View.VISIBLE);
                overbox.startAnimation(fromnothing);
                rlv.startAnimation(fromsmall);
                imgclose.startAnimation(fromsmall);
            }
        });
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overbox.startAnimation(togo);
                rlv.startAnimation(togo);
                imgclose.startAnimation(togo);
                overbox.setVisibility(View.GONE);
                rlv.setVisibility(View.GONE);
                imgclose.setVisibility(View.GONE);
            }
        });
        image_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent =new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REGUEST);
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_IMAGE_REGUEST&&resultCode== Activity.RESULT_OK&&data!=null&&data.getData()!=null){
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                imgProfile.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            imageDataBase imgdp=new imageDataBase();
            String imghex=imgdp.convertImageIntoString(bitmap);
            imgdp.saveImageInDP(getActivity(),UserID);
        }
    }
    private Bitmap convertToBitmap(String b){
        byte []decodeString= Base64.decode(b,Base64.DEFAULT);
        Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        return decodebitmap ;
    }
}