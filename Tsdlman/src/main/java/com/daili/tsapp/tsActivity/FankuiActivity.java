package com.daili.tsapp.tsActivity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.ActivityFankuiBinding;
import com.daili.tsapp.databinding.ActivityKeFuBinding;
import com.daili.tsapp.jsBean.netBean.ErrorBean;
import com.daili.tsapp.jsBean.netBean.FankuiBean;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;

public class FankuiActivity extends BaseActivity implements View.OnClickListener {

    ActivityFankuiBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_fankui);
        init();
    }

    private void init() {
        b.fankuiBack.setOnClickListener(this);
        b.fankuiTijiao.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fankui_back:
                onBackPressed();
                break;
            case R.id.fankui_tijiao:
                tijiao();
                break;
        }
    }

    //提交问题 waiter_id/71/nav/111111111111/tel/11111
    SystemUtil su=new SystemUtil(this);
    private void tijiao() {
        Map<String,Object>params=new HashMap<>();
        params.put("waiter_id",su.showUid());
        params.put("nav",b.fankuiWenti.getText().toString());
        String ph=b.fankuiPhone.getText().toString();
        params.put("tel",b.fankuiPhone.getText().toString());
        NetUtils.Post(BaseData.FANKUI, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
               if(result.substring(0,18).contains("Error")){
                   ErrorBean erro=new Gson().fromJson(result,ErrorBean.class);
                   toast(erro.getMsg());
               }else {
                   FankuiBean bee=new Gson().fromJson(result,FankuiBean.class);
                   toast(bee.getMsg());
               }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                toast(getResources().getString(R.string.net_err));
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
