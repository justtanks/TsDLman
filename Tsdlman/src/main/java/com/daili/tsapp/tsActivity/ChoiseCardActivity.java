package com.daili.tsapp.tsActivity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import com.daili.tsapp.R;
import com.daili.tsapp.databinding.ChoiseCardBinding;
import com.daili.tsapp.jsBean.netBean.CardsBean;
import com.daili.tsapp.jsBean.netBean.IsHadPassBean;
import com.daili.tsapp.tsAdapter.ChoiseCardLvAdapter;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;

import java.security.interfaces.DSAKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 选择银行卡界面
 */
public class ChoiseCardActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ChoiseCardBinding b;
    CardsBean datas = null;
    ChoiseCardLvAdapter adapter;
    private Intent intent;
    public static final int FROM_CHOISCARD = 11111;
    Handler handler = new Handler();
    SystemUtil su = new SystemUtil(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        b = DataBindingUtil.setContentView(this, R.layout.activity_choise_card);
        b.choisecardNewcard.setOnClickListener(this);
        b.choisecardReback.setOnClickListener(this);
        intent = getIntent();
        datas = (CardsBean) intent.getSerializableExtra("datas");
        adapter = new ChoiseCardLvAdapter(this, datas.getData());
        b.choisecardLb.setAdapter(adapter);
        b.choisecardLb.setOnItemClickListener(this);
        EventBus.getDefault().register(this);

    }
//实现暂停一下然后进行跳转的效果
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(ChoiseCardActivity.this, AddcardStep1Activity.class);
            intent.putExtra("ids", FROM_CHOISCARD);
            startActivity(intent);
//            Map<String, Object> params = new HashMap<>();
//            params.put("waiter_id", su.showUid() + "");
//            NetUtils.Post(BaseData.HAVAPASS, params, new Callback.CommonCallback<String>() {
//                @Override
//                public void onSuccess(String result) {
//                    Gson gson = new Gson();
//                    IsHadPassBean bean=gson.fromJson(result,IsHadPassBean.class);
//                    if(bean.getData()==1){
//                        //已经设置密码
//
//                    }else if(bean.getData()==2){
//                        //未设置密码 先设置密码
//                        Intent intent=new Intent(ChoiseCardActivity.this,AddpassWordActivity.class);
//                        startActivity(intent);
//                    }
//                }
//
//                @Override
//                public void onError(Throwable ex, boolean isOnCallback) {
//                    toast(getResources().getString(R.string.net_error));
//                }
//
//                @Override
//                public void onCancelled(CancelledException cex) {
//
//                }
//
//                @Override
//                public void onFinished() {
//
//                }
//            });
        }
    };
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            finish();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choisecard_newcard:
                addcard();
                break;
            case R.id.choisecard_reback:
                onBackPressed();
                break;
        }
    }

    //刷新添加的银行卡
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventList(CardsBean datas) {
        this.datas= datas;
        adapter.setDatas(datas.getData());
        adapter.notifyDataSetChanged();
    }
    //跳转到添加银行卡界面银行卡
    private void addcard() {
        b.choisecardDuihao.setVisibility(View.VISIBLE);
        adapter.setPos(-1);
        su.saveModle1(1);
        handler.postDelayed(runnable1, 600);
    }

    //选择新的银行卡
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.setPos(position);
        b.choisecardDuihao.setVisibility(View.INVISIBLE);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datas1", datas.getData().get(position));
        intent.putExtras(bundle);
        setResult(DrawCashActivity.TOGETCARD, intent);
        handler.postDelayed(runnable2, 600);
    }


    @Override
    protected void onStop() {
        super.onStop();
        b.choisecardDuihao.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        handler.removeCallbacks(runnable1);
        handler.removeCallbacks(runnable2);
        adapter = null;
        su = null;
        b = null;
    }
}
