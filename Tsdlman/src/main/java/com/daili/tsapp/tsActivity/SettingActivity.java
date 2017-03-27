package com.daili.tsapp.tsActivity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daili.tsapp.R;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.utils.SystemUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.setting_back:
                onBackPressed();
                break;
//            case R.id.setting_persionnal_msg:
////                Intent intent=new Intent(this,PersonalMsgActivity.class);
////                startActivity(intent);
//                break;
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
