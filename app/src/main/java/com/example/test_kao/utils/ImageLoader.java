package com.example.test_kao.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.test_kao.app.MyApp;

public class ImageLoader {
    //url 图片地址
    public static void loadImage(String url, ImageView img){
        //无图模式
        if(SpUtils.getInstance().getBoolean("image") && img != null){
            Glide.with(MyApp.app).load(url).into(img);
        }else if(img != null){//有图模式
            Glide.with(MyApp.app).load(url).into(img);
        }
    }
//    //解析图片的路径 分割字符串 用于下载
//    public static String[] splitUrl(String url){
//        String []arr = new String[3];
//        int end = url.lastIndexOf("/")+1;
//        String baseUrl = url.substring(0,end); //公共的
//        String imgName = url.substring(end,url.length());//图片后面的名字，不同的地方
//        String path = Constants.PATH_IMGS+"/"+imgName;
//        arr[0] = baseUrl;
//        arr[1] = imgName;
//        arr[2] = path;
//        return arr;
//    }
//    public static Bitmap getIconBitmap(Context context, int iconId) {
//        try {
//            Drawable icon = ContextCompat.getDrawable(context, iconId);
//            if (icon == null) {
//                return null;
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && icon instanceof AdaptiveIconDrawable) {
//                Bitmap bitmap = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bitmap);
//                icon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//                icon.draw(canvas);
//                return bitmap;
//            } else {
//                return ((BitmapDrawable) icon).getBitmap();
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
