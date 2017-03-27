package com.daili.tsapp.tsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.SetPassBean;
import com.daili.tsapp.jsView.BaseData1;
import com.daili.tsapp.jsView.PwdEditText;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;


import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

/*
 添加密码后重新确认密码
 */
public class SurePasswordActivity extends BaseActivity {

    PwdEditText text;
    TextView back;
    SystemUtil su = new SystemUtil(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_password);
        init();
    }

    private void init() {
        text = (PwdEditText) this.findViewById(R.id.surepass_pet_pwd);
        back = (TextView) this.findViewById(R.id.surepass_textback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        text.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {

            @Override
            public void onInputFinish(String password) {
                String firstpass = getIntent().getStringExtra("firstpass");
                if (password.equals(firstpass)) {
                    //设置密码并跳转到添加银行卡界面
                    setPassWord(password);
                } else {
                    onBackPressed();
                    toast(getString(R.string.mimabuyizhi));
                }

            }
        });
    }
    //访问接口  设置密码 waiter_id/71/password/123456

    private void setPassWord(String passWord) {
        Map<String, Object> params = new HashMap<>();
        params.put("waiter_id", su.showUid() + "");
        params.put("password", passWord);
        NetUtils.Post(BaseData.SETPASS, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                SetPassBean bean = gson.fromJson(result, SetPassBean.class);
                toast(bean.getMsg());
                if (null != bean && "Success".equals(bean.getFlag())) {
                    Intent intent = new Intent(SurePasswordActivity.this, AddcardStep1Activity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                toast(getResources().getString(R.string.net_error));
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
