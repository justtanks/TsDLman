package com.daili.tsapp.tsFragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.HomeFragBinding;
import com.daili.tsapp.jsBean.netBean.CardsBean;
import com.daili.tsapp.jsBean.netBean.ErrorBean;
import com.daili.tsapp.jsBean.netBean.FormlistDateBean;
import com.daili.tsapp.jsBean.netBean.HomeBaobiaoBean;
import com.daili.tsapp.jsBean.netBean.NetError;
import com.daili.tsapp.tsActivity.CardActivity;
import com.daili.tsapp.tsActivity.DrawCashActivity;
import com.daili.tsapp.tsActivity.OrdersActivity;
import com.daili.tsapp.tsActivity.TabHomeActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsBase.BaseFragment;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页第一个fragment 加载用户个人账户信息
 */
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    HomeFragBinding b;
    TabHomeActivity context;
    SystemUtil su;
    Gson gson = new Gson();
    HomeBaobiaoBean bean;
    ProgressDialog dialog;
    Callback.Cancelable cancel;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return b.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        EventBus.getDefault().register(this);
        b.mainDingdanmingxi.setOnClickListener(this);
        b.mainTixianText.setOnClickListener(this);
        context = (TabHomeActivity) getActivity();
        su = new SystemUtil(context);
        setCircle();
        getDataOnNet();

    }

    //设置刷新控件
    private void setCircle() {

        b.mainRefresh.setOnRefreshListener(this);
        b.mainRefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        b.mainRefresh.setDistanceToTriggerSync(300);
        b.mainRefresh.setSize(SwipeRefreshLayout.DEFAULT);
    }

    private void getDataOnNet() {
        b.mainRefresh.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        param.put("waiter_id", su.showUid());
//        param.put("waiter_id",71);
        NetUtils.Post(BaseData.BAOBIAO, param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.substring(0, 18).contains("Error")) {
                    NetError error = gson.fromJson(result, NetError.class);
                    Toast.makeText(context, error.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    bean = gson.fromJson(result, HomeBaobiaoBean.class);
                    if (bean.getMsg() != null && bean.getFlag().equals("Success")) {
                        b.setMainbaobiao(bean.getMsg().get(0));
                        if (bean.getMsg().get(0).getType() == 1) {
                            b.mainShangsheng.setImageDrawable(getResources().getDrawable(R.mipmap.home_getup));
                        } else {
                            b.mainShangsheng.setImageDrawable(getResources().getDrawable(R.mipmap.home_getdown));
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                b.mainRefresh.setRefreshing(false);

            }
        });

    }

    @Override
    public void onRefresh() {
        getDataOnNet();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_dingdanmingxi:
                getToDingdan();
                break;
            case R.id.main_tixian_text:
                toDrawCards();
                break;
        }
    }
    private void toDrawCards() {
        dialog = ProgressDialog.show(context, "", "正在获取银行卡信息");
        dialog.show();
        Map<String, Object> parm = new HashMap<>();
        parm.put("waiter_id", su.showUid());
        cancel=NetUtils.Post(BaseData.GETCARDS, parm, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                if (result.substring(0, 18).contains("Error")) {
                    NetError error = gson.fromJson(result, NetError.class);
                    Toast.makeText(getActivity(), error.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    CardsBean cards = gson.fromJson(result, CardsBean.class);
                    if(cards.getData().size()==0){
                        Toast.makeText(getActivity(), "您还没有银行卡，请先添加银行卡后再进行提现操作", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    intent = new Intent(context, DrawCashActivity.class);
                    intent.putExtra("cards", cards);
                    intent.putExtra("allmoney",bean.getMsg().get(0).getBalance_money());
                    startActivity(intent);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                dialog.dismiss();
            }
        });
    }


    //获取到订单信息并跳转到订单列表 /waiter_id/71
    public void getToDingdan() {
        Map<String, Object> parms = new HashMap<>();
        parms.put("waiter_id", 71);
        NetUtils.Post(BaseData.GETORDERS, parms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.substring(0, 18).contains("Error")) {
                    ErrorBean error = gson.fromJson(result, ErrorBean.class);
                    Toast.makeText(context, error.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    FormlistDateBean data = gson.fromJson(result, FormlistDateBean.class);
                    if (!data.getFlag().equals("Success")) {

                    } else {
                        Intent intent = new Intent(context, OrdersActivity.class);
                        intent.putExtra("forms", data);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    //重新刷新界面请求数据
    @Subscribe
    public void onEventMainThread(Integer x) {
       if(x==111){
           getDataOnNet();
       }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
