package com.daili.tsapp.tsActivity;
/**
 * 没有使用
 */

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.WindowManager;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.HomeBinding;
import com.daili.tsapp.tsBase.BaseActivity;
public class HomeActivity extends BaseActivity {
    public static final String MAIN_KEY = "1";
    HomeBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getActionBar();
        b = DataBindingUtil.setContentView(this, R.layout.activity_home);
    }
}
