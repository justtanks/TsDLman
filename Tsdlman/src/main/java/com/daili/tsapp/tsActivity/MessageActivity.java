package com.daili.tsapp.tsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.PingLunBean;
import com.daili.tsapp.tsAdapter.MessageAdapter;
import com.daili.tsapp.tsBase.BaseActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.msg_back)
    RelativeLayout back;
    @ViewInject(R.id.msg_lv)
    ListView lv;
    private Intent intent;
    private List<PingLunBean.DataBean> pinglundatas=new ArrayList<>();
    private MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        x.view().inject(this);
        init();
    }

    private void init() {
        intent=getIntent();
        back.setOnClickListener(this);
        PingLunBean data=(PingLunBean)intent.getSerializableExtra("pinglun");
        pinglundatas=data.getData();
        adapter=new MessageAdapter(this,pinglundatas);
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back:
                onBackPressed();
                break;
        }
    }
}
