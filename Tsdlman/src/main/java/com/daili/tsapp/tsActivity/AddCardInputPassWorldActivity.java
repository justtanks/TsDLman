package com.daili.tsapp.tsActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.AddCardBean;
import com.daili.tsapp.jsBean.netBean.NetError;
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
添加完银行卡之后 确认密码
 */
public class AddCardInputPassWorldActivity extends BaseActivity {

    PwdEditText editText;
    String cardnum="";
    String cardcity;
    SystemUtil su=new SystemUtil(this);
    ProgressDialog waitDialog=null;
    RelativeLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_input_pass_world);
        editText = (PwdEditText) this.findViewById(R.id.input_passpet_pwd);
        back= (RelativeLayout) this.findViewById(R.id.inputpass_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cardnum=getIntent().getStringExtra("cardnum");
        cardcity=getIntent().getStringExtra("cardcity");
        editText.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {
            @Override
            public void onInputFinish(String password) {
                 addCardToNet(password);
            }
        });
    }
  ///waiter_id/71/brank_name/1%E5%93%88%E5%93%88%E5%93%88/card_num/11111111111111111111
    //还缺少一个密码
    private void addCardToNet(String pass) {
        if(cardcity!=null&&cardnum!=null&&pass!=null){
            waitDialog=ProgressDialog.show(this,"","正在添加银行卡");
            waitDialog.show();
            Map<String,Object> parms=new HashMap<>();
             parms.put("waiter_id",su.showUid());
            parms.put("brank_name",cardcity);
            parms.put("card_num",cardnum);
            parms.put("withdrawals_password",pass);
            NetUtils.Post(BaseData.ADDCARD, parms, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Gson gson=new Gson();
                    if(result.substring(0,18).contains("Success")){
                        AddCardBean bean=gson.fromJson(result,AddCardBean.class);
                        if("Success".equals(bean.getFlag())){
                            Intent intent=new Intent(AddCardInputPassWorldActivity.this,AddcardDoneActivity.class);
                            startActivity(intent);
                        }
                    }else {
                        NetError error=gson.fromJson(result,NetError.class);
                        toast(error.getMsg());
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
                    waitDialog.dismiss();

                }
            });

        }

    }
}
