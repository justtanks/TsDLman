package com.ts.databending.activity;
/**
 the problem is ? the mean about it , the way to  do
 */
import android.databinding.DataBindingUtil;
 import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ts.databending.R;
import com.ts.databending.bean.FucBean;
import com.ts.databending.databinding.NewBinding;
//在fragment中使用databinding的方法

public class Binding1Fragment extends Fragment {

  FucBean stu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        NewBinding ben= DataBindingUtil.inflate(inflater,R.layout.fragment_binding1,container,false);
        stu=new FucBean();
        ben.setFuck(stu);
        stu.mystring.set("hahha");

        return ben.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置保存状态是true
        setRetainInstance(true);
    }
}
