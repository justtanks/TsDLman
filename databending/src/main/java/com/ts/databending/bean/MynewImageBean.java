package com.ts.databending.bean;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2017/1/18.
 */

public class MynewImageBean {
//    http://121.199.32.4:8088/Uploads/2017-01-18/587ec04d8a27b.jpg
@BindingAdapter({"imageUrl"})
   public static void imageLoader(ImageView imageView, String imageUrl){
    Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
}

}
