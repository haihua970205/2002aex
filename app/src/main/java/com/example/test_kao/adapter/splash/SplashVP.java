package com.example.test_kao.adapter.splash;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SplashVP extends PagerAdapter {
    private Context context;
    private ArrayList<View> views;
    private List<Integer> integerList;

    public SplashVP(Context context, ArrayList<View> views, List<Integer> integerList) {
        this.context = context;
        this.views = views;
        this.integerList = integerList;
    }

    @Override
    public int getCount() {
        return integerList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView view = new ImageView(context);
        view.setScaleType((ImageView.ScaleType.CENTER_CROP));
        Glide.with(context).load(integerList.get(position)).into(view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
