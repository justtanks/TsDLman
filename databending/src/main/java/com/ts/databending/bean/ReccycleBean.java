package com.ts.databending.bean;

import android.databinding.BaseObservable;
import android.databinding.Observable;

/**
 * Created by Administrator on 2017/1/18.
 * reyccle的bean 通过databinding进行绑定
 */

public class ReccycleBean extends BaseObservable {
    String text1;
    String text2;

    public ReccycleBean() {
    }

    public ReccycleBean(String text2, String text1) {
        this.text2 = text2;
        this.text1 = text1;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
//        notifyPropertyChanged();
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
