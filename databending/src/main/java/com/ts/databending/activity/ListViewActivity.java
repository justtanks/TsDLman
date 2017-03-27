package com.ts.databending.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ts.databending.BR;
import com.ts.databending.R;
import com.ts.databending.adapter.ListAdapter;
import com.ts.databending.adapter.ListViewAdapter;
import com.ts.databending.bean.ReccycleBean;

import java.util.ArrayList;
import java.util.List;

//使用listview和databinder的activity
//添加谷歌的自带的下拉刷新组件
public class ListViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ListView listView;
    SwipeRefreshLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        layout = (SwipeRefreshLayout) this.findViewById(R.id.refresh);
        layout.setOnRefreshListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        layout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        layout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        layout.setSize(SwipeRefreshLayout.DEFAULT); // 设置圆圈的大小

        listView = (ListView) this.findViewById(R.id.listview);
        List<ReccycleBean> ms = new ArrayList<ReccycleBean>() {
            {
                for (int i = 0; i < 40; i++) {
                    add(new ReccycleBean("hha" + i, "http://121.199.32.4:8088/Uploads/2017-01-18/587ec04d8a27b.jpg"));
                }
            }
        };
//        ListViewAdapter adapter=new ListViewAdapter(ms, BR.mego,this);
        ListAdapter<ReccycleBean> adapter = new ListAdapter<>(this, ms, BR.mego, R.layout.item_listview_testbinding);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "正在刷新", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                layout.setRefreshing(false);
            }
        }, 5000); // 5秒后发送消息，停止刷新
    }
}
