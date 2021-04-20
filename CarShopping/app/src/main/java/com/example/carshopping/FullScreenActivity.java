package com.example.carshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FullScreenActivity extends AppCompatActivity {
    private ViewPager fullSlider;
    private FullSliderAdapter adapter;
    private ArrayList<String>carphoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        GetAllCar c=new GetAllCar();
        Intent i=getIntent();
        carphoto=i.getStringArrayListExtra("photo");
        fullSlider = findViewById(R.id.fullscreen);
        adapter = new FullSliderAdapter(this,carphoto);
        fullSlider.setAdapter(adapter);
    }

    private class FullSliderAdapter extends PagerAdapter {
        private Context context;
        private LayoutInflater layoutInflater;
        private ArrayList<String>carphoto1;
        // private ArrayList<String>car=new ArrayList<String>();

        public FullSliderAdapter(Context context,ArrayList<String>carphoto1) {
            this.context = context;
            this.carphoto1=carphoto1;
        }

        @Override
        public int getCount() {
            return carphoto1.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_full, container, false);
            PhotoView photoView = view.findViewById(R.id.full_img);

            Picasso.get().load(carphoto1.get(position)).into(photoView);
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ImageView) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return (view == object);
        }

    }
}
