package com.daili.tsapp.tsFragment;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.NewFormNum;
import com.daili.tsapp.jsBean.VoiceSpeek;
import com.daili.tsapp.jsBean.daoBean.FormEvent;
import com.daili.tsapp.jsBean.netBean.FormListnew;
import com.daili.tsapp.tsAdapter.NewFormAdapter;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsBase.BaseFragment;
import com.daili.tsapp.tsNet.Xutils;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/1/4.
 * 新订单界面  修改订单获取方式，将无限循环改为推送接收
 * 通过eventbus传递订单事件，通过网络获取订单列表，在界面进行展示
 */
@ContentView(R.layout.fragment_newform)
public class NewFormFragment extends BaseEventFragment {
    @ViewInject(R.id.newform_linear)
    RelativeLayout relativeLayout;
    @ViewInject(R.id.fragment_new_list)
    ListView listView;
    private Callback.Cancelable cancelable;
    private int time = BaseData.REFRESHTIME;  //循环访问时间
    List<FormListnew.DataBean> dataBeens; //接收数据
    NewFormAdapter adapter;    //listview的adapter
    VoiceSpeek speek;           //传递语音工具 暂时没有使用
    Gson gson = new Gson();
    FormListnew resu;
    Ringtone r;//调起系统铃声
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //设置访问网络时间
                    handler.postDelayed(runnable, time);
                    break;
            }
        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void init() {
        adapter = new NewFormAdapter(getContext());
        speek = new VoiceSpeek(null);
        listView.setAdapter(adapter);
        handler.postDelayed(runnable, 0);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        r = RingtoneManager.getRingtone(getContext(), notification);
    }

    //定时循环
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
            searchFormOnNet();
        }
    };
    /*
    请求网络  找到订单  定时
     */
    private void searchFormOnNet() {
        cancelable = Xutils.Post(BaseData.XINDINGDAN, new HashMap<String, Object>(), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.substring(9, 14).equals("Error")) {
                    EventBus.getDefault().post(new NewFormNum(0));
                    relativeLayout.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                    return;
                }
                resu = gson.fromJson(result, FormListnew.class);
                if (resu.getFlag() == "Error") {
                    return;
                }
                if (null == resu || resu.getData().size() == 0) {
                    EventBus.getDefault().post(new NewFormNum(0));
                    relativeLayout.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                } else {
                    relativeLayout.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    dataBeens = resu.getData();
                    EventBus.getDefault().post(new NewFormNum(dataBeens.size()));
                    //语音发送到activity
//                    speek.setContext("您有" + dataBeens.size() + "条新订单");
//                    EventBus.getDefault().post(speek);
                    r.play();
                    adapter.setUsers(dataBeens);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Toast.makeText(getContext(), R.string.netError, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    @Subscribe
    public void onEvent(FormEvent event) {
        searchFormOnNet();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("newformfragment");
        init();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("newformfragment");
        handler.removeCallbacks(runnable);
        if (cancelable != null) {
            this.cancelable.cancel();
        }

    }
}
