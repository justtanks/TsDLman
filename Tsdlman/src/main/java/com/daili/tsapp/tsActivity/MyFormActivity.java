package com.daili.tsapp.tsActivity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.daili.tsapp.R;
import com.daili.tsapp.tsAdapter.MyFragmentPagerAdapter;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsFragment.AllFormFragment;
import com.daili.tsapp.tsFragment.WaitExamineFragment;
import com.daili.tsapp.tsFragment.WaitJudgeFragment;
import com.daili.tsapp.tsFragment.WaitReceiveFormFragment;
import com.daili.tsapp.tsFragment.WaitSureFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单界面 展示已接单 未接单 和 待评价订单
 */
@ContentView(R.layout.activity_my_form)
public class MyFormActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {


    @ViewInject(R.id.myform_tab1)
    private TextView tab1Text1;
    @ViewInject(R.id.myform_tab2)
    private TextView tab1Text2;
    @ViewInject(R.id.myform_tab3)
    private TextView tab1Text3;
    @ViewInject(R.id.myform_tab4)
    private TextView tab1Text4;
    @ViewInject(R.id.myform_tab5)
    private TextView tab1Text5;
    @ViewInject(R.id.myform_tab_line)
    private View tabline;
    @ViewInject(R.id.myform_viewpager)
    private ViewPager mViewpager;
    @ViewInject(R.id.myform_back)
    private ImageView backimg;
    private Animation animation;
    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    private int position_two;
    private int position_three;
    private int position_four;
    private ArrayList<Fragment> fragmentList;
    private Fragment allFormfragment, waitReceiveFragment, waitExamFragment, waitJudgeFragment, waitForSureFragment;
    private List<TextView> textViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();
    }

    private void init() {
        textViews = new ArrayList<>();
        textViews.add(tab1Text1);
        textViews.add(tab1Text2);
        textViews.add(tab1Text3);
        textViews.add(tab1Text4);
        textViews.add(tab1Text5);
        tab1Text1.setOnClickListener(new MyOnClickListener(0));
        tab1Text2.setOnClickListener(new MyOnClickListener(1));
        tab1Text3.setOnClickListener(new MyOnClickListener(2));
        tab1Text4.setOnClickListener(new MyOnClickListener(3));
        tab1Text5.setOnClickListener(new MyOnClickListener(4));

        backimg.setOnClickListener(this);
        initWidth();
        initAdapter();

    }

    private void initWidth() {

        bottomLineWidth = tabline.getLayoutParams().width;
        toast(bottomLineWidth + "");
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (int) ((screenW / 5 - bottomLineWidth) / 2);

        position_one = (int) (screenW / 5.0);
        position_two = position_one * 2;
        position_three = position_one * 3;
        position_four = position_one * 4;
    }

    private void initAdapter() {
        fragmentList = new ArrayList<>();
        allFormfragment = new AllFormFragment();
        waitReceiveFragment = new WaitReceiveFormFragment();
        waitExamFragment = new WaitExamineFragment();
        waitJudgeFragment = new WaitJudgeFragment();
        waitForSureFragment = new WaitSureFragment();
        fragmentList.add(allFormfragment);
        fragmentList.add(waitReceiveFragment);
        fragmentList.add(waitExamFragment);
        fragmentList.add(waitForSureFragment);
        fragmentList.add(waitJudgeFragment);

        mViewpager.setCurrentItem(0);
        setTextColor(0);
        mViewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));//解决fragment嵌套问题
        mViewpager.addOnPageChangeListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myform_back:
                onBackPressed();
                break;

        }


    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mViewpager.setCurrentItem(index);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTextColor(position);
        switch (position) {
            case 0:

                if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, 0, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, 0, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(position_four, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, position_one, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_one, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(position_four, position_one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_two, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_two, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(position_four, position_two, 0, 0);
                }
                break;
            case 3:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_three, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_three, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, position_three, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(position_four, position_three, 0, 0);
                }
                break;
            case 4:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_four, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_four, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, position_four, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_four, 0, 0);
                }
                break;
        }
        currIndex = position;
        animation.setFillAfter(true);
        animation.setDuration(100);
        tabline.startAnimation(animation);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setTextColor(int currIndex) {
        for (TextView ts : textViews) {
            ts.setTextColor(getResources().getColor(R.color.settingtext));
        }
        textViews.get(currIndex).setTextColor(getResources().getColor(R.color.myformcolor));

    }
}
