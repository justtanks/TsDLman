package com.daili.tsapp.tsFragment;


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
import com.daili.tsapp.jsBean.netBean.ErrorBean;
import com.daili.tsapp.jsBean.netBean.FormlistDateBean;
import com.daili.tsapp.jsBean.netBean.HomeBaobiaoBean;
import com.daili.tsapp.jsBean.netBean.NetError;
import com.daili.tsapp.tsActivity.OrdersActivity;
import com.daili.tsapp.tsActivity.TabHomeActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsBase.BaseFragment;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页第一个fragment 加载用户个人账户信息
 */
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    HomeFragBinding b;
    TabHomeActivity context;
    SystemUtil su;
    Gson gson = new Gson();
    HomeBaobiaoBean bean;
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
        b.mainDingdanmingxi.setOnClickListener(this);
        context = (TabHomeActivity) getActivity();
        su = new SystemUtil(context);
        getDataOnNet();
        setCircle();
    }

    private void setCircle() {
        b.mainRefresh.setRefreshing(true);
        b.mainRefresh.setOnRefreshListener(this);
        b.mainRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        b.mainRefresh.setDistanceToTriggerSync(300);
        b.mainRefresh.setSize(SwipeRefreshLayout.DEFAULT);
    }

    private void getDataOnNet() {
        Map<String, Object> param = new HashMap<>();
        param.put("waiter_id", su.showUid());
//        param.put("waiter_id",71);
        NetUtils.Post(BaseData.BAOBIAO, param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.substring(0, 18).contains("Error")) {
                    NetError error = gson.fromJson(result, NetError.class);    Toast.makeText(context, error.getMsg(), Toast.LENGTH_SHORT).show();

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
        switch (v.getId()){
            case R.id.main_dingdanmingxi:
                getToDingdan();
                break;
        }
    }
//获取到订单信息并跳转到订单列表 /waiter_id/71
    public void getToDingdan() {
        Map<String,Object> parms=new HashMap<>();
        parms.put("waiter_id",71);
        NetUtils.Post(BaseData.GETORDERS, parms, new Callback.CommonCallback<String>() {
            @Override
             public void onSuccess(String result) {
             if(result.substring(0,18).contains("Error")){
                 ErrorBean error=gson.fromJson(result,ErrorBean.class);
                 Toast.makeText(context, error.getMsg(), Toast.LENGTH_SHORT).show();
             } else {
                 FormlistDateBean data=gson.fromJson(result,FormlistDateBean.class);
                 if(!data.getFlag().equals("Success")){

                 }else {
                     Intent intent=new Intent(context, OrdersActivity.class);
                     intent.putExtra("forms",data);
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
}
