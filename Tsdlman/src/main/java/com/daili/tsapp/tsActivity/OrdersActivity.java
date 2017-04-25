package com.daili.tsapp.tsActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.OrderBinding;
import com.daili.tsapp.jsBean.netBean.ErrorBean;
import com.daili.tsapp.jsBean.netBean.FormlistDateBean;
import com.daili.tsapp.tsAdapter.OrderFragmentPagerAdapter;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsBase.impl.OnOrdersChangeListener;
import com.daili.tsapp.tsFragment.AllOrdersFragment;
import com.daili.tsapp.tsFragment.BeforeYestodayOrdersFragment;
import com.daili.tsapp.tsFragment.TodayOrdersFragment;
import com.daili.tsapp.tsFragment.YestodayOrdersFragment;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 展示所有订单的activity 展示今天 昨天 和全部订单
 */
public class OrdersActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, SwipeRefreshLayout.OnRefreshListener {
    OrderBinding b;
    private Animation animation;
    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    private int position_two;
    private int position_three;
    private ArrayList<Fragment> fragmentList;
    private AllOrdersFragment allFormfragment;
    private TodayOrdersFragment todayFragment;
    private YestodayOrdersFragment yesdayFragment;
    private BeforeYestodayOrdersFragment beforYesdayFragment;
    private List<TextView> textViews;
    private List<OnOrdersChangeListener> pages = new ArrayList<>();
    SystemUtil su;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_orders);
        init();

    }

    private void init() {
        su = new SystemUtil(this);
        textViews = new ArrayList<>();
        textViews.add(b.myformTab1);
        textViews.add(b.myformTab2);
        textViews.add(b.myformTab3);
        textViews.add(b.myformTab4);
        b.ordersBack.setOnClickListener(this);
        b.ordersFenlei.setOnClickListener(this);
        b.myformTab1.setOnClickListener(new MyOnClickListener(0));
        b.myformTab2.setOnClickListener(new MyOnClickListener(1));
        b.myformTab3.setOnClickListener(new MyOnClickListener(2));
        b.myformTab4.setOnClickListener(new MyOnClickListener(3));
        setCircle();
        initAdapter();
        fresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initWidth();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orders_back:
                onBackPressed();
                break;
            case R.id.orders_fenlei:
                //判断是否下拉，然后做view的隐藏显示

                break;

        }

    }

    private void initWidth() {
        bottomLineWidth = b.orderColorline.getLayoutParams().width;
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = 0 + 30;
        position_one = (int) (screenW / 4.0);
        position_two = position_one * 2;
        position_three = position_one * 3;
    }

    private void initAdapter() {
        fragmentList = new ArrayList<>();
        allFormfragment = new AllOrdersFragment();
        todayFragment = new TodayOrdersFragment();
        yesdayFragment = new YestodayOrdersFragment();
        beforYesdayFragment = new BeforeYestodayOrdersFragment();
        fragmentList.add(todayFragment);
        fragmentList.add(yesdayFragment);
        fragmentList.add(beforYesdayFragment);
        fragmentList.add(allFormfragment);
        pages.add(todayFragment);
        pages.add(yesdayFragment);
        pages.add(beforYesdayFragment);
        pages.add(allFormfragment);
        b.orderViewpager.setCurrentItem(0);
        setTextColor(0);
        b.orderViewpager.setOffscreenPageLimit(4);
        b.orderViewpager.setAdapter(new OrderFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));//解决fragment嵌套问题
        b.orderViewpager.addOnPageChangeListener(this);
    }

    private void setTextColor(int currIndex) {
        for (TextView ts : textViews) {
            ts.setTextColor(getResources().getColor(R.color.gray3));
        }
        textViews.get(currIndex).setTextColor(getResources().getColor(R.color.main_tixianbutton));

    }

    @Override
    public void onRefresh() {
        fresh();
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
                }
                break;

        }
        currIndex = position;
        animation.setFillAfter(true);
        animation.setDuration(100);
        b.orderColorline.startAnimation(animation);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animation = null;
        fragmentList = null;
        textViews = null;
        b = null;
    }

    private void setCircle() {
        b.ordersFresh.setOnRefreshListener(this);
        b.ordersFresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        b.ordersFresh.setDistanceToTriggerSync(300);
        b.ordersFresh.setSize(SwipeRefreshLayout.DEFAULT);
    }

    FormlistDateBean orders;

    public void fresh() {
        b.ordersFresh.setRefreshing(true);
        final Map<String, Object> parms = new HashMap<>();
        parms.put("waiter_id", su.showUid());
        NetUtils.Post(BaseData.GETORDERS, parms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.substring(0, 18).contains("Error")) {
                    ErrorBean error = gson.fromJson(result, ErrorBean.class);
                    Toast.makeText(OrdersActivity.this, error.getMsg(), Toast.LENGTH_SHORT).show();
                    b.ordersFresh.setRefreshing(false);
                } else {
                    orders = gson.fromJson(result, FormlistDateBean.class);
                    for (OnOrdersChangeListener or : pages) {
                        or.onOrdersChange(orders);
                    }
                    toast("订单加载成功");

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
               toast(getString(R.string.getcardagain));
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                b.ordersFresh.setRefreshing(false);

            }
        });
    }
}
