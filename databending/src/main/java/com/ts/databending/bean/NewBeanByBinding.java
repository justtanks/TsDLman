package com.ts.databending.bean;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by Administrator on 2017/1/16.
 * 通过databinding 设置的实体类  是这么使用的
 */

public class NewBeanByBinding {
    public ObservableField<String> firstString=new ObservableField<>();
    public ObservableInt firstInt=new ObservableInt();
    public ObservableBoolean bolean=new ObservableBoolean();
}
