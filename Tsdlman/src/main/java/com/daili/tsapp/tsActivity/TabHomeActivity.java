package com.daili.tsapp.tsActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.TabHomeBinding;
import com.daili.tsapp.tsAdapter.OrderFragmentPagerAdapter;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsFragment.GetOrderFragment;
import com.daili.tsapp.tsFragment.HomeFragment;
import com.daili.tsapp.tsFragment.MineFragment;
import com.daili.tsapp.tsService.UpdateService;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

/**
 * 首页存放tab和fragment的activity  也就是登录界面跳转之后的界面
 */
public class TabHomeActivity extends BaseActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    TabHomeBinding b;
    //建立三个fragment
    HomeFragment fra1=new HomeFragment();
    GetOrderFragment fra2=new GetOrderFragment();
    MineFragment fra3= new MineFragment();
    private ArrayList<Fragment> fragmentList=new ArrayList<>();
    private long currentTime;
    AlertDialog.Builder builder;
    MenuItem prevMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_tab_home);
        currentTime = System.currentTimeMillis();
        MobclickAgent.openActivityDurationTrack(false);
        b.firstTab.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        this.setResult(3333);
        initAdapter();
        getFresh();

    }
    //显示是否具有新版本并进行更新
    private  void getFresh(){
        if(getIntent().getIntExtra("isfresh",0)==1){
               showDialog();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_home:
                     b.firstPager.setCurrentItem(0);
                    return true;
                case R.id.action_qiangdan:
                    b.firstPager.setCurrentItem(1);
                    return true;
                case R.id.action_me:
                    b.firstPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };
    //弹出对话框
    private void showDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("已经有新版本");
        builder.setMessage("是否进行更新？").
                setCancelable(false).setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent service = new Intent(TabHomeActivity.this,UpdateService.class);
                startService(service);
                dialog.cancel();
            }
        }).setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    void initAdapter(){
        fragmentList.add(fra1);
        fragmentList.add(fra2);
        fragmentList.add(fra3);
        b.firstPager.setCurrentItem(0);
        b.firstPager.setOffscreenPageLimit(3);
        b.firstPager.setAdapter(new OrderFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));//解决fragment嵌套问题
        b.firstPager.addOnPageChangeListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        } else {
            b.firstTab.getMenu().getItem(0).setChecked(false);
        }
        b.firstTab.getMenu().getItem(position).setChecked(true);
        prevMenuItem =  b.firstTab.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    public void onBackPressed() {
        long time = System.currentTimeMillis();
        if (time - currentTime > 3000) {
            Toast.makeText(this, R.string.home_backpress, Toast.LENGTH_SHORT).show();
            currentTime = time;
            return;
        }

        super.onBackPressed();
     }
}
