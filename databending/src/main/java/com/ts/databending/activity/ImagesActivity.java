package com.ts.databending.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ts.databending.R;
import com.ts.databending.databinding.Go;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

public class ImagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Go goBinding= DataBindingUtil.setContentView(this,R.layout.activity_images);
        goBinding.setImage("http://121.199.32.4:8088/Uploads/2017-01-18/587ec04d8a27b.jpg");
//        Serializable
//        Object
        List a;
    }
}
