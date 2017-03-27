package com.ts.databending.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2017/1/19.  复制
 * 这是对image1：在imageview中自定义的一个属性，然后就能够用对imageview和url进行绑定
 * 定义了这个util之后可以在任意的imageview之中直接设置url就行了
 */

public class ImageBinding {
    @BindingAdapter("image")
    public static void  imageLoader(ImageView imageView,String url){
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }
}
