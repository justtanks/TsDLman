//package com.ts.databending.activity;
//import android.databinding.DataBindingUtil;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import com.ts.databending.R;
//import com.ts.databending.adapter.OrderFragmentPagerAdapter;
//import com.ts.databending.databinding.Gooo;
//
//import java.util.ArrayList;
//
///**
// * 将代理人修改成为这种样子的
// */
//
//public class FirstActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
//    Gooo b;
//    First1Fragment fra1=new First1Fragment();
//    First2ragment fra2=new First2ragment();
//    First3Fragment fra3= new First3Fragment();
//    private ArrayList<Fragment> fragmentList=new ArrayList<>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        b= DataBindingUtil.setContentView(this,R.layout.activity_first);
//        b.tab1.setOnClickListener(this);
//        b.tab2.setOnClickListener(this);
//        b.tab3.setOnClickListener(this);
//        initAdapter();
//        setTab(0);
//    }
//    void initAdapter(){
//        fragmentList.add(fra1);
//        fragmentList.add(fra2);
//        fragmentList.add(fra3);
//        b.firstPager.setCurrentItem(0);
//        b.firstPager.setOffscreenPageLimit(3);
//        b.firstPager.setAdapter(new OrderFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));//解决fragment嵌套问题
//        b.firstPager.addOnPageChangeListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.tab1:
//                setTab(0);
//                b.firstPager.setCurrentItem(0);
//                break;
//            case R.id.tab2:
//                setTab(1);
//                b.firstPager.setCurrentItem(1);
//                break;
//            case R.id.tab3:
//                setTab(2);
//                b.firstPager.setCurrentItem(2);
//                break;
//        }
//    }
//
//
//    private  void setTab(int id){
//        if(id==0){
//            b.firstHomeimg.setImageDrawable(getDrawable(R.mipmap.home_home_check));
//            b.firstHomeimg2.setImageDrawable(getDrawable(R.mipmap.home_util_normolt));
//            b.firstHomeimg3.setImageDrawable(getDrawable(R.mipmap.home_mine_normal));
//
//            b.tabText1.setTextColor(getResources().getColor(R.color.colorAccent));
//            b.tabText2.setTextColor(getResources().getColor(R.color.colorPrimary));
//            b.tabText3.setTextColor(getResources().getColor(R.color.colorPrimary));
//        }else if(id==1){
//            b.firstHomeimg.setImageDrawable(getDrawable(R.mipmap.home_home_normal));
//            b.firstHomeimg2.setImageDrawable(getDrawable(R.mipmap.home_util_check));
//            b.firstHomeimg3.setImageDrawable(getDrawable(R.mipmap.home_mine_normal));
//
//            b.tabText1.setTextColor(getResources().getColor(R.color.colorPrimary));
//            b.tabText2.setTextColor(getResources().getColor(R.color.colorAccent));
//            b.tabText3.setTextColor(getResources().getColor(R.color.colorPrimary));
//        }else if(id==2){
//            b.firstHomeimg.setImageDrawable(getDrawable(R.mipmap.home_home_normal));
//            b.firstHomeimg2.setImageDrawable(getDrawable(R.mipmap.home_util_normolt));
//            b.firstHomeimg3.setImageDrawable(getDrawable(R.mipmap.home_mine_check));
//
//            b.tabText1.setTextColor(getResources().getColor(R.color.colorPrimary));
//            b.tabText2.setTextColor(getResources().getColor(R.color.colorPrimary));
//            b.tabText3.setTextColor(getResources().getColor(R.color.colorAccent));
//        }
//
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//      setTab(position);
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//}
