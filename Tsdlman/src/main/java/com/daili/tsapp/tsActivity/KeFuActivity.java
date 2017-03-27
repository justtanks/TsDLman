package com.daili.tsapp.tsActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.ActivityKeFuBinding;
import com.daili.tsapp.tsBase.BaseActivity;

public class KeFuActivity extends BaseActivity {
   ActivityKeFuBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b= DataBindingUtil.setContentView(this,R.layout.activity_ke_fu);
        init();
    }
    private void init(){
        b.kefuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
