//package com.daili.tsapp.tsActivity;
//  之前使用的加载单独一个fragment的activity
//import android.content.Intent;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.widget.ImageView;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.baidu.android.common.logging.Log;
//import com.baidu.tts.auth.AuthInfo;
//import com.baidu.tts.client.SpeechError;
//import com.baidu.tts.client.SpeechSynthesizer;
//import com.baidu.tts.client.TtsMode;
//import com.daili.tsapp.R;
//import com.daili.tsapp.jsBean.HadFormNum;
//import com.daili.tsapp.jsBean.NewFormNum;
//import com.daili.tsapp.jsBean.VoiceSpeek;
//import com.daili.tsapp.jsBean.daoBean.FormEvent;
//import com.daili.tsapp.jsBean.netBean.ErrorBean;
//import com.daili.tsapp.jsBean.netBean.PingLunBean;
//import com.daili.tsapp.tsBase.BaseActivity;
//import com.daili.tsapp.tsBase.BaseData;
//import com.daili.tsapp.tsBase.BaseSpeechSyListener;
//import com.daili.tsapp.tsDB.DButils;
//import com.daili.tsapp.tsFragment.NewFormFragment;
//import com.daili.tsapp.tsFragment.OwnedFormFragment;
//import com.daili.tsapp.tsNet.Xutils;
//import com.daili.tsapp.utils.SystemUtil;
//import com.daili.tsapp.utils.VoiceHelper;
//import com.google.gson.Gson;
//import com.umeng.analytics.MobclickAgent;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.xutils.DbManager;
//import org.xutils.common.Callback;
//import org.xutils.ex.DbException;
//import org.xutils.view.annotation.ViewInject;
//import org.xutils.x;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//
////抢单activity 如果是抢单的话   现在是首先跳转的activity
//public class FindFormsActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
//
//    @ViewInject(R.id.toptab_radiogroup)
//    private RadioGroup mRadioGroup;
//    @ViewInject(R.id.toptab_new)
//    private RadioButton mNewForm;
//    @ViewInject(R.id.toptab_have)
//    private RadioButton mAllForm;
//    @ViewInject(R.id.toptab_setting)
//    private RelativeLayout settingbt;
//    @ViewInject(R.id.qiangdan_msg)
//    private ImageView msg;
//    private NewFormFragment newFormFragment = new NewFormFragment();
//    private OwnedFormFragment ownedFormFragment = new OwnedFormFragment();
//    private FragmentManager manager;
//    private SystemUtil su = new SystemUtil(this);
//    //计算时间进行退出的地方使用到新的activity
//    private long currentTime;
//    private VoiceHelper voice;  //语音功能对象
//    Gson gson = new Gson();
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_findform);
//        MobclickAgent.openActivityDurationTrack(false);
//        x.view().inject(this);
//        EventBus.getDefault().register(this);
//        init();
//        //初始化语音模块
//    }
//
//    private void init() {
//        mRadioGroup.setOnCheckedChangeListener(this);
//        manager = this.getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.qiangdan_framecontent, ownedFormFragment, "ownedform");
//        transaction.add(R.id.qiangdan_framecontent, newFormFragment, "newform");
//        transaction.hide(ownedFormFragment);
//        transaction.commit();
//        voice = new VoiceHelper(this);
//        currentTime = System.currentTimeMillis();
//        settingbt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FindFormsActivity.this, SettingActivity.class);
//                startActivity(intent);
//            }
//        });
//        msg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                msg.setImageResource(R.mipmap.message_nomes);
//                Intent intent = new Intent(FindFormsActivity.this, MessageActivity.class);
//                intent.putExtra("pinglun", bean);
//                startActivity(intent);
//            }
//        });
//        handler.post(runnable);
//    }
//
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1:
//                    break;
//            }
//        }
//    };
//    //执行访问网络的子线程 相当于定时器
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            getPinglunOnNet();
//            handler.postDelayed(runnable, 1 * 20 * 1000);
//        }
//    };
//
//    @Override
//    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//        if (i == R.id.toptab_new) {
//            mNewForm.setTextColor(getResources().getColor(R.color.qingdan_toptabtex_seleter));
//            mAllForm.setTextColor(getResources().getColor(R.color.qiangdan_toptabtex_notselect));
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.hide(ownedFormFragment);
//            transaction.show(newFormFragment);
//            transaction.commit();
//
//
//        } else if (i == R.id.toptab_have) {
//            mNewForm.setTextColor(getResources().getColor(R.color.qiangdan_toptabtex_notselect));
//            mAllForm.setTextColor(getResources().getColor(R.color.qingdan_toptabtex_seleter));
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.hide(newFormFragment);
//            transaction.show(ownedFormFragment);
//            transaction.commit();
//        }
//    }
//
//    @Subscribe
//    public void onEvent(HadFormNum num) {
//        mAllForm.setText("已抢订单" + "(" + num.getNum() + ")");
//    }
//
//    @Subscribe
//    public void onEvent(NewFormNum num) {
//        mNewForm.setText("新订单" + "(" + num.getNum() + ")");
//    }
//
//    @Subscribe
//    public void onEvent(VoiceSpeek speek) {
//        voice.speek(speek.getContext());
//    }
//
//    @Override
//    public void onBackPressed() {
//        long time = System.currentTimeMillis();
//        if (time - currentTime > 3000) {
//            Toast.makeText(this, R.string.home_backpress, Toast.LENGTH_SHORT).show();
//            currentTime = time;
//            return;
//         }
//         FindFormsActivity.this.setResult(3333);
//         super.onBackPressed();
////        finishExe();
//    }
//    //退出程序 跳回第一个activity 然后退出第一个activity
////    private void finishExe() {
////        //保存统计的数据，然后结束进程
////        MobclickAgent.onKillProcess(this);
////        Intent intent = new Intent(Intent.ACTION_MAIN);
////        intent.addCategory(Intent.CATEGORY_HOME);
////        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////        startActivity(intent);
////        android.os.Process.killProcess(android.os.Process.myPid());
////    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//        voice.release();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        MobclickAgent.onResume(this);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        MobclickAgent.onPause(this);
//    }
//
//    /*
//        访问网络  获取评论
//     */
//
//    private PingLunBean bean = new PingLunBean();
//    private ErrorBean error = null;
//    private void getPinglunOnNet() {
//        Map<String, Object> parms = new HashMap<>();
//        parms.put("waiter_id",su.showUid());
//        Xutils.Post(BaseData.PINGLUN, parms, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                if (result.substring(0, 19).contains("Error")) {
//                    return;
//                }
//                bean = gson.fromJson(result, PingLunBean.class);
//                if (bean == null) {
//                    return;
//                }
//                if (bean.getData().size() > su.showPinglun()) {
//                    msg.setImageResource(R.mipmap.message_havemsg);
//                    su.savePinlun(bean.getData().size());
//                } else {
//                    msg.setImageResource(R.mipmap.message_nomes);
//                }
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                loge(ex.toString());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
//
//}
