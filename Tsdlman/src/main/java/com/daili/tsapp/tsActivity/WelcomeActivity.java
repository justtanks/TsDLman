package com.daili.tsapp.tsActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.GetversionBean;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;

public class WelcomeActivity extends BaseActivity {
    SystemUtil su = new SystemUtil(this);
    Intent intent;
    int isFresh=0;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStates();
        setContentView(R.layout.activity_welcome);
        fresh();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toLogin();
                WelcomeActivity.this.finish();
            }
        }, 2000);
    }

//跳转到登录界面
    private void toLogin() {
            intent = new Intent(this, LoginActivity.class);
            intent.putExtra("isfresh",isFresh);
            startActivity(intent);

    }
    //设置全屏展示
      private void setStates(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
    Callback.Cancelable cancelable;
    private void fresh() {

       cancelable= NetUtils.Get(BaseData.GETVERSION, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                GetversionBean bean=new Gson().fromJson(result,GetversionBean.class);
                if(bean.getData().get(0).getNumber()>SystemUtil.getVersionCode()){
                    //显示是否更新的对话框
                    isFresh=1;
                }else {
                    isFresh=0;

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                toast(getString(R.string.net_error));
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
