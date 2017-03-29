package com.daili.tsapp.tsActivity;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.ActivityMyFormBinding;
import com.daili.tsapp.tsAdapter.OrderFragmentPagerAdapter;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsFragment.HadJudgeFragment;
import com.daili.tsapp.tsFragment.MyAllFormsFragment;
import com.daili.tsapp.tsFragment.WaitJudgeFragment;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单界面 待评价 已评价 和 已接单 所有信息
 */
public class MyFormActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Animation animation;
    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    private int position_two;
    private int position_three;
    private ArrayList<Fragment> fragmentList;
    private Fragment allFormfragment, hadPingjiaFragment, waitPingJiaFragment;
    private List<TextView> textViews;
    ActivityMyFormBinding b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }


    private void init() {
        b = DataBindingUtil.setContentView(this, R.layout.activity_my_form);
        textViews = new ArrayList<>();
        textViews.add(b.formsTab1);
        textViews.add(b.formsTab2);
        textViews.add(b.formsTab3);
        b.formsBack.setOnClickListener(this);
        b.formsTab1.setOnClickListener(new MyOnClickListener(0));
        b.formsTab2.setOnClickListener(new MyOnClickListener(1));
        b.formsTab3.setOnClickListener(new MyOnClickListener(2));
        initAdapter();
    }

    //初始化 viewpager
    private void initAdapter() {
        fragmentList = new ArrayList<>();
        allFormfragment = new MyAllFormsFragment();
        hadPingjiaFragment = new HadJudgeFragment();
        waitPingJiaFragment = new WaitJudgeFragment();

        fragmentList.add(allFormfragment);
        fragmentList.add(waitPingJiaFragment);
        fragmentList.add(hadPingjiaFragment);

        b.orderViewpager.setCurrentItem(0);
        setTextColor(0);
        b.orderViewpager.setOffscreenPageLimit(4);
        b.orderViewpager.setAdapter(new OrderFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));//解决fragment嵌套问题
        b.orderViewpager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forms_back:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initWidth();
        int firstPosition = getIntent().getIntExtra("id", 0);
        b.orderViewpager.setCurrentItem(firstPosition);
        setTextColor(firstPosition);
        changeLinePositon(firstPosition);
    }
//初始化计算屏幕宽度
    private void initWidth() {
        bottomLineWidth = b.orderColorline.getLayoutParams().width;
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = 0 + 30;
        position_one = (int) (screenW / 3.0);
        position_two = position_one * 2;
        position_three = position_one * 3;
    }

    private void setTextColor(int currIndex) {
        for (TextView ts : textViews) {
            ts.setTextColor(getResources().getColor(R.color.gray3));
        }
        textViews.get(currIndex).setTextColor(getResources().getColor(R.color.main_tixianbutton));

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTextColor(position);
        changeLinePositon(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changeLinePositon(int position) {
        switch (position) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, 0, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, position_one, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_two, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_two, 0, 0);
                }
                break;

            case 3:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_three, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_three, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, position_three, 0, 0);
                }
                break;
        }
        currIndex = position;
        if (animation!=null) {
            animation.setFillAfter(true);
            animation.setDuration(100);
            b.orderColorline.startAnimation(animation);
        }

    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            b.orderViewpager.setCurrentItem(index);
        }
    }
}
