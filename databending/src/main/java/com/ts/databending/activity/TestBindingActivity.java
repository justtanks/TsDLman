package com.ts.databending.activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
//import com.ts.databending.BR;
import com.ts.databending.R;
import com.ts.databending.bean.FormListnew;
import com.ts.databending.bean.User;
import com.ts.databending.data.BaseData;


import com.ts.databending.utils.Xutils;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBindingActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       setContentView(R.layout.activity_test_binding);
        DataBindingUtil.setContentView(this,R.layout.activity_test_binding);
        init();

    }

    private void init() {
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
          Binding1Fragment fragment=new Binding1Fragment();
        transaction.add(R.id.fragment,fragment);
        transaction.commit();

    }


}
