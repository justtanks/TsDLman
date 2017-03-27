package com.daili.tsapp.tsActivity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.daili.tsapp.R;
import com.daili.tsapp.databinding.CardBinding;
import com.daili.tsapp.jsBean.netBean.CardsBean;
import com.daili.tsapp.jsBean.netBean.IsHadPassBean;
import com.daili.tsapp.tsAdapter.CardListviewAdatper;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的银行卡界面
 */
public class CardActivity extends BaseActivity implements View.OnClickListener {

    private CardBinding bingding;
    CardListviewAdatper adatper;
    SystemUtil su = new SystemUtil(this);
    CardsBean carddatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        EventBus.getDefault().register(this);
        bingding = DataBindingUtil.setContentView(this, R.layout.activity_card);
        bingding.cardAddnewcard.setOnClickListener(this);
        bingding.cardBacktext.setOnClickListener(this);
        carddatas= (CardsBean) getIntent().getSerializableExtra("cards");
        if (carddatas != null && carddatas.getData()!= null) {
            adatper = new CardListviewAdatper(this,carddatas.getData());
            bingding.cardLv.setAdapter(adatper);
        }
//
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_backtext:
                onBackPressed();
                break;
            case R.id.card_addnewcard:
                addCard();
                break;
        }
    }

    //添加银行卡
    private void addCard() {
        su.saveModle1(2);
        Map<String, Object> params = new HashMap<>();
        params.put("waiter_id", su.showUid() + "");
        NetUtils.Post(BaseData.HAVAPASS, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                IsHadPassBean bean=gson.fromJson(result,IsHadPassBean.class);
                if(bean.getData()==1){
                    //已经设置密码
                    Intent intent=new Intent(CardActivity.this,AddcardStep1Activity.class);
                    startActivity(intent);
                }else if(bean.getData()==2){
                 //未设置密码 先设置密码
                    Intent intent=new Intent(CardActivity.this,AddpassWordActivity.class);
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventList(CardsBean datas) {
        adatper.setDatas(datas.getData());
        adatper.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        su = null;
        adatper = null;
        bingding = null;
    }
}
