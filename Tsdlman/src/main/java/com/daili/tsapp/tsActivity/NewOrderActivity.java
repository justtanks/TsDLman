package com.daili.tsapp.tsActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import com.daili.tsapp.BR;
import com.daili.tsapp.R;
import com.daili.tsapp.databinding.ActivityNewOrderBinding;
import com.daili.tsapp.jsBean.NewOrdersBean;
import com.daili.tsapp.tsAdapter.simpleAdapter.ListAdapter;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.google.gson.Gson;
import org.xutils.common.Callback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewOrderActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    ActivityNewOrderBinding b;
    List<NewOrdersBean.MsgBean> datas = new ArrayList<>();
    AlertDialog.Builder builder;
    ListAdapter<NewOrdersBean.MsgBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_new_order);
        init();
    }

    //访问网络请求数据并进行展示
    private void init() {
        builder = new AlertDialog.Builder(this);
        b.neworderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Collections.reverse(datas);
        adapter = new ListAdapter<>(this, datas, BR.neworder, R.layout.item_neworder_lv);
        b.neworderRefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        b.neworderLv.setAdapter(adapter);
        getDataOnServer();
        b.neworderRefresh.setOnRefreshListener(this);
        b.neworderLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if (b.neworderLv != null && b.neworderLv.getChildCount() > 0) {
                    boolean firstItemVisible = b.neworderLv.getFirstVisiblePosition() == 0;
                    boolean topOfFirstItemVisible = b.neworderLv.getChildAt(0).getTop() == 0;
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                b.neworderRefresh.setEnabled(enable);
                if (b.neworderLv.getVisibility() != View.VISIBLE) {
                    b.neworderRefresh.setEnabled(true);
                }
            }
        });
        b.neworderLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popDialog(position);
            }
        });

    }

    //弹出dialog 询问是否打电话
    private void popDialog(final int position) {
        builder.setTitle("确定要进行电话沟通？");
        builder.setCancelable(false).setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeUserCase(position);
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

    //点击打电话之后改变界面的状态和服务器的关于订单的一个状态
    private void changeUserCase(int position) {
        Map<String,Object>param=new HashMap<>();
        param.put("order_id",datas.get(position).getId());
        NetUtils.Post(BaseData.CHANGEORDERSTATE, param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                loge(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loge("hava  some error in it");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        datas.get(position).setNew_list(0);
        adapter.setDatas(datas);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + datas.get(position).getUser_tel()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //从服务器端获取到所有APP下的订单
    public void getDataOnServer() {
        NetUtils.Post(BaseData.NEWORDER, new HashMap<String, Object>(), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
               datas = new Gson().fromJson(result, NewOrdersBean.class).getMsg();
                if (datas.size() > 0) {
                    Collections.reverse(datas);
                    adapter.setDatas(datas);
                    b.neworderLv.setVisibility(View.VISIBLE);
                    b.neworderRelativelayout.setVisibility(View.GONE);
                } else {
                    b.neworderRelativelayout.setVisibility(View.VISIBLE);
                    b.neworderLv.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                b.neworderRelativelayout.setVisibility(View.VISIBLE);
                b.neworderLv.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                b.neworderRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        getDataOnServer();
    }
}
