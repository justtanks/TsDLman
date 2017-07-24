package com.daili.tsapp.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.daili.tsapp.jsBean.TuiSongBusBean;
import com.daili.tsapp.tsService.DownLoadHelper;
import com.daili.tsapp.tsService.UpdateService;
import com.daili.tsapp.utils.SystemUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/4.
 * 消息接收的广播，首先测试每个方法的功能 阿里推送
 */

public class MyMessageReceiver extends MessageReceiver {
    // 消息接收部分的LOG_TAG
    public static final String REC_TAG = "receiver";
    /*
    通知接收回调
         客户端接收到通知后，回调该方法。会显示到通知栏
         可获取到并处理通知相关的参数。
           参数
          context 上下文环境
          title 通知标题
           summary 通知内容
          extraMap 通知额外参数，包括部分系统自带参数：
         _ALIYUN_NOTIFICATION_ID_(V2.3.5及以上):创建通知对应id
          _ALIYUN_NOTIFICATION_PRIORITY_(V2.3.5及以上):创建通知对应id。默认不带，需要通过OpenApi设置
     */

    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        EventBus.getDefault().postSticky(new TuiSongBusBean(11));
        new SystemUtil(context).saveHaveUser(1);
        for(String s:extraMap.keySet()){
            Log.e("hello",s);
            Log.e("hello",extraMap.get(s));
        }
    }

    /*
    消息接收回调             不会显示到通知栏
    用于接收服务端推送的消息。
    消息不会弹窗，而是回调该方法。
    类型，可以获取消息Id、消息标题和内容。
     */
    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        Log.e("MyMessageReceiver", "onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
    }

    /*
    通知打开回调
    打开通知时会回调该方法，通知打开上报由SDK自动完成。
    参数
    context 上下文环境
    title 通知标题
    summary 通知内容
    extraMap 通知额外参数，包括部分系统自带参数：
    _ALIYUN_NOTIFICATION_ID_(V2.3.5及以上):创建通知对应id
    _ALIYUN_NOTIFICATION_PRIORITY_(V2.3.5及以上):创建通知对应id。默认不带，需要通过OpenApi设置
     */
    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        Log.e("MyMessageReceiver", "onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
        EventBus.getDefault().postSticky(new TuiSongBusBean(11));
    }

    /*
    无跳转逻辑通知打开回调
    打开无跳转逻辑(open=4)通知时回调该方法(v2.3.2及以上版本支持)，通知打开上报由SDK自动完成。
     */
    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Log.e("MyMessageReceiver", "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
    }
    /*
    通知在应用内到达回调
       当用户创建自定义通知样式，并且设置推送应用内到达不创建通知弹窗时调用该回调，且此时不调用onNotification回调(v2.3.3及以上版本支持)
       参数
    context 上下文环境
    title 通知标题
    summary 通知内容
    extraMap 通知额外参数
    openType 原本通知打开方式，1：打开APP；2：打开activity；3：打开URL；4：无跳转逻辑
    openActivity 所要打开的activity的名称，仅当openType=2时有效，其余情况为null
    openUrl 所要打开的URL，仅当openType=3时有效，其余情况为null
     */

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
//        Log.e("MyMessageReceiver", "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);

    }

    /*
    通知删除回调

    删除通知时回调该方法，通知删除上报由SDK自动完成。
    参数
    context 上下文环境
    messageId 删除通知的Id
     */
    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        Log.e("MyMessageReceiver", "onNotificationRemoved");
    }
}
