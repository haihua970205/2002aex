package com.example.test_kao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.test_kao.utils.TxtUtils;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

public class BigVpAdapter extends PagerAdapter {
    private Context context;
    private List<String> big;

    public BigVpAdapter(Context context, List<String> big) {
        this.context = context;
        this.big = big;
    }

    @Override
    public int getCount() {
        return big.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //图片缩放
        PhotoView iv = new PhotoView(context);
        TxtUtils.setPhotoView(context,iv,big.get(position));
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
