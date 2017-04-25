package com.daili.tsapp.tsActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.CardsBean;
import com.daili.tsapp.jsBean.netBean.LoginBean;
import com.daili.tsapp.jsBean.netBean.NetError;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import java.util.HashMap;
import java.util.Map;

/*
添加银行卡成功的activity
 */
public class AddcardDoneActivity extends BaseActivity {
    Button done;
    SystemUtil su = new SystemUtil(this);
    ProgressDialog dialog;
    int processid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard_done);
        done = (Button) this.findViewById(R.id.carddone_complete);
        processid=su.showModle();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }

    @Override
    public void onBackPressed() {
        update();
    }

    /*
    同步关于银行卡的信息
     */
    Callback.Cancelable cancel;
    //18253487693

    private void update() {
        dialog = ProgressDialog.show(this, "", "同步信息中");
        dialog.show();
        Map<String, Object> parm = new HashMap<>();
        parm.put("waiter_id", su.showUid());
     cancel=   NetUtils.Post(BaseData.GETCARDS, parm, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                String cutResult = result.substring(0, 19);
                if (cutResult.contains("Error")) {
                    NetError error = gson.fromJson(result, NetError.class);
                    toast(error.getMsg());
                    error = null;
                    return;
                } else {
                    CardsBean cards = gson.fromJson(result, CardsBean.class);
                    if ("Success".equals(cards.getFlag())) {
                        switch (processid){
                            case 1:
                                Intent intent=new Intent(AddcardDoneActivity.this,ChoiseCardActivity.class);
                                intent.putExtra("datas",cards);
                                startActivity(intent);
                                break;
                            case 2:
                                Intent intent1 = new Intent(AddcardDoneActivity.this, CardActivity.class);
                                startActivity(intent1);
                                break;
                        }

                    } else {
                        cards = null;
                        return;
                    }
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
                dialog.dismiss();
            }
        });
    }
}
