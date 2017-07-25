package com.daili.tsapp.tsApplication;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.daili.tsapp.utils.SystemUtil;
import com.umeng.analytics.MobclickAgent;

import org.xutils.x;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

import cn.sharesdk.framework.ShareSDK;


/**
 * Created by Administrator on 2016/12/28.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        initCloudChannel(this);


    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.bindAccount(new SystemUtil(applicationContext).showPhone(), new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Log.e("application", "onSuccess: "+s+1);
            }

            @Override
            public void onFailed(String s, String s1) {

            }
        });
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("alituisong", "init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d("alituisong", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
//        设置推送图标
//        pushService.setNotificationLargeIcon();
    }


}
