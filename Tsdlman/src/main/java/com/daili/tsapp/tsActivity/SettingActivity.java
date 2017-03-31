package com.daili.tsapp.tsActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.GetversionBean;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsService.UpdateService;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
设置界面
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.setting_back)
    RelativeLayout back;
//    @ViewInject(R.id.setting_persionnal_msg)
//    RelativeLayout persionmsg;
    @ViewInject(R.id.settint_idsafe)
    RelativeLayout safety;
    @ViewInject(R.id.setting_removegarbage)
    RelativeLayout clean;
    @ViewInject(R.id.setting_quitlogin)
    RelativeLayout quitLogin;
    @ViewInject(R.id.setting_gengxin)
    RelativeLayout gengxin;
    ProgressDialog dialog;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        x.view().inject(this);
        init();

    }

    private void init() {
        back.setOnClickListener(this);
//        persionmsg.setOnClickListener(this);
        safety.setOnClickListener(this);
        clean.setOnClickListener(this);
        quitLogin.setOnClickListener(this);
        gengxin.setOnClickListener(this);
        builder = new AlertDialog.Builder(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.setting_back:
                onBackPressed();
                break;
            case R.id.setting_gengxin:
                  fresh();
//                showDialog();
                break;
            case  R.id.settint_idsafe:
                safe();
                break;
            case  R.id.setting_removegarbage:
                clean();
                break;
            case R.id.setting_quitlogin:
                quitlogin();
        }

    }

    /**
     * 获取到当前的版本号
     * 以及获取到网络的版本号
     * 然后对比进行更新
     */
    private void fresh() {
        dialog=ProgressDialog.show(this,"","正在获取版本信息");
        dialog.show();
        NetUtils.Get(BaseData.GETVERSION, null, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                GetversionBean bean=new Gson().fromJson(result,GetversionBean.class);
                if(bean.getData().get(0).getNumber()>SystemUtil.getVersionCode()){
                    //显示是否更新的对话框
                    showDialog();
                }else {
                    toast("当前已经是最新版本");
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
        })    ;

    }
    //显示下载对话框
    private void showDialog() {
        builder.setTitle("已经有新版本");
        builder.setMessage("是否进行更新？").
                setCancelable(false).setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                downAndFresh();
                downLoad();
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
    //下载操作 从服务中做下载操作
    private void downLoad(){
        Intent service = new Intent(this,UpdateService.class);
        startService(service);
    }

    //清除su 的关于名称密码数据  退出到login activity
    SystemUtil su=new SystemUtil(this);
    private void quitlogin() {
        su.savePhone(null);
        su.savePwd(null);
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void clean() {
        toast(getResources().getString(R.string.noneed_toclean));
    }

    private void safe() {
        toast(getResources().getString(R.string.waittosee));
    }
}
