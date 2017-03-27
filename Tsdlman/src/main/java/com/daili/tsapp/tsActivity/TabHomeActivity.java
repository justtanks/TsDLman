package com.daili.tsapp.tsActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.TabHomeBinding;
import com.daili.tsapp.tsAdapter.OrderFragmentPagerAdapter;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsFragment.GetOrderFragment;
import com.daili.tsapp.tsFragment.HomeFragment;
import com.daili.tsapp.tsFragment.MineFragment;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_tab_home);
        b.tab1.setOnClickListener(this);
        b.tab2.setOnClickListener(this);
        b.tab3.setOnClickListener(this);
        currentTime = System.currentTimeMillis();
        MobclickAgent.openActivityDurationTrack(false);
        initAdapter();
        setTab(0);
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
            case R.id.tab1:
                setTab(0);
                b.firstPager.setCurrentItem(0);
                break;
            case R.id.tab2:
                setTab(1);
                b.firstPager.setCurrentItem(1);
                break;
            case R.id.tab3:
                setTab(2);
                b.firstPager.setCurrentItem(2);
                break;
        }
    }
    private  void setTab(int id){
        if(id==0){
            b.firstHomeimg.setImageDrawable(getResources().getDrawable(R.mipmap.home_home_yes));
            b.firstHomeimg2.setImageDrawable(getResources().getDrawable(R.mipmap.home_qiangdan_no));
            b.firstHomeimg3.setImageDrawable(getResources().getDrawable(R.mipmap.home_mine_no));

            b.tabText1.setTextColor(getResources().getColor(R.color.zhucecolortitle));
            b.tabText2.setTextColor(getResources().getColor(R.color.gray1));
            b.tabText3.setTextColor(getResources().getColor(R.color.gray1));
        }else if(id==1){
            b.firstHomeimg.setImageDrawable(getResources().getDrawable(R.mipmap.hoem_home_not));
            b.firstHomeimg2.setImageDrawable(getResources().getDrawable(R.mipmap.home_qiangdan_yes));
            b.firstHomeimg3.setImageDrawable(getResources().getDrawable(R.mipmap.home_mine_no));

            b.tabText1.setTextColor(getResources().getColor(R.color.gray1));
            b.tabText2.setTextColor(getResources().getColor(R.color.zhucecolortitle));
            b.tabText3.setTextColor(getResources().getColor(R.color.gray1));
        }else if(id==2){
            b.firstHomeimg.setImageDrawable(getResources().getDrawable(R.mipmap.hoem_home_not));
            b.firstHomeimg2.setImageDrawable(getResources().getDrawable(R.mipmap.home_qiangdan_no));
            b.firstHomeimg3.setImageDrawable(getResources().getDrawable(R.mipmap.home_mine_yes));

            b.tabText1.setTextColor(getResources().getColor(R.color.gray1));
            b.tabText2.setTextColor(getResources().getColor(R.color.gray1));
            b.tabText3.setTextColor(getResources().getColor(R.color.zhucecolortitle));
        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTab(position);
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
        this.setResult(3333);
        super.onBackPressed();
     }
}
