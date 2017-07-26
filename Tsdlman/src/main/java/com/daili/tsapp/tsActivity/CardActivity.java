package com.daili.tsapp.tsActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.CardBinding;
import com.daili.tsapp.jsBean.netBean.CardsBean;
import com.daili.tsapp.jsBean.netBean.DeleteCardSuccess;
import com.daili.tsapp.jsBean.netBean.IsHadPassBean;
import com.daili.tsapp.jsBean.netBean.NetError;
import com.daili.tsapp.jsView.PwdEditText;
import com.daili.tsapp.tsAdapter.CardListviewAdatper;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的银行卡界面 展示所有银行卡信息
 */
public class CardActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, SwipeRefreshLayout.OnRefreshListener {
    CardBinding bingding;
    CardListviewAdatper adatper;
    SystemUtil su = new SystemUtil(this);
    CardsBean carddatas = new CardsBean();
    AlertDialog.Builder builder;
    ProgressDialog progressDialog;
    PopupWindow mPopuwindow;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setCircle();
    }

    private void init() {
        builder = new AlertDialog.Builder(this);
        bingding = DataBindingUtil.setContentView(this, R.layout.activity_card);
        bingding.cardAddnewcard.setOnClickListener(this);
        bingding.cardBacktext.setOnClickListener(this);
        bingding.cardLv.setOnItemLongClickListener(this);
        adatper = new CardListviewAdatper(this, carddatas.getData());
        bingding.cardLv.setAdapter(adatper);
    }

    private void setCircle() {
        bingding.cardFresh.setOnRefreshListener(this);
        bingding.cardFresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        bingding.cardFresh.setDistanceToTriggerSync(300);
        bingding.cardFresh.setSize(SwipeRefreshLayout.DEFAULT);
        bingding.cardLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if (bingding.cardLv != null && bingding.cardLv.getChildCount() > 0) {
                    boolean firstItemVisible = bingding.cardLv.getFirstVisiblePosition() == 0;
                    boolean topOfFirstItemVisible = bingding.cardLv.getChildAt(0).getTop() == 0;
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                bingding.cardFresh.setEnabled(enable);
                if(bingding.cardLv.getVisibility()!=View.VISIBLE){
                    bingding.cardFresh.setEnabled(true);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getCardOnNET();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
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
                IsHadPassBean bean = gson.fromJson(result, IsHadPassBean.class);
                if (bean.getData() == 1) {
                    //已经设置密码
                    Intent intent = new Intent(CardActivity.this, AddcardStep1Activity.class);
                    startActivity(intent);
                } else if (bean.getData() == 2) {
                    //未设置密码 先设置密码
                    Intent intent = new Intent(CardActivity.this, AddpassWordActivity.class);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        su = null;
        adatper = null;
        bingding = null;
    }

    //长按移除银行卡功能
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        popDialog(position);
        return true;
    }

    //弹出对话框 是否进行删除
    private void popDialog(final int position) {
        builder.setTitle("确定移除银行卡吗？");
        builder.setCancelable(false).setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showPassword(carddatas.getData().get(position).getCard_num());
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

    //显示输入密码的弹出框 然后访问网络重新刷新数据
    private void showPassword(final String cardnum) {
        View popupView = getLayoutInflater().inflate(R.layout.dialog_shanka_password, null);
        PwdEditText password = (PwdEditText) popupView.findViewById(R.id.dialog_tixian_pet_pwd);
        RelativeLayout relBack = (RelativeLayout) popupView.findViewById(R.id.dialog_relaBack);
        mPopuwindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        mPopuwindow.setTouchable(true);
        mPopuwindow.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        mPopuwindow.setBackgroundDrawable(dw);
        relBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopuwindow.dismiss();
            }
        });
        password.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {
            @Override
            public void onInputFinish(String password) {
                changeCardforNet(cardnum, password);
                mPopuwindow.dismiss();
            }
        });
        mPopuwindow.showAtLocation(bingding.cardBack, Gravity.CENTER, 0, 0);
    }

    //请求网络进行删除操作
    private void changeCardforNet(String cardnum, String password) {
        progressDialog = ProgressDialog.show(this, "", "正在执行删除操作");
        progressDialog.show();
        Map<String, Object> param = new HashMap<>();
        param.put("waiter_id", su.showUid());
        param.put("card_num", cardnum);
        param.put("password", password);
        NetUtils.Post(BaseData.DELETECARD, param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.substring(0, 18).contains("Error")) {
                    NetError error = gson.fromJson(result, NetError.class);
                    toast(error.getMsg());
                } else {
                    DeleteCardSuccess sumsg = gson.fromJson(result, DeleteCardSuccess.class);
                    toast(sumsg.getMsg());
                    getCardOnNET();
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
                progressDialog.dismiss();
            }
        });
    }

    //下拉刷新的回调
    @Override
    public void onRefresh() {
        getCardOnNET();
    }


    //获取到所有的银行卡信息
    private void getCardOnNET() {
        bingding.cardFresh.setRefreshing(true);
        Map<String, Object> parm = new HashMap<>();
        parm.put("waiter_id", su.showUid());
        NetUtils.Post(BaseData.GETCARDS, parm, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                if (result.substring(0, 18).contains("Error")) {
                    NetError error = gson.fromJson(result, NetError.class);
//                    if (error.getMsg().equals("0")) {
//                        //没有银行卡
//                        toast("您现在没有银行卡银行卡");
//                        carddatas=new CardsBean();
//                        adatper.setDatas(carddatas.getData());
//                    } else if (error.getMsg().equals("1")) {
//                        Toast.makeText(CardActivity.this, "您还没有进行认证，无法获取银行卡信息", Toast.LENGTH_SHORT).show();
//                    }
                    bingding.cardLv.setVisibility(View.GONE);
                    bingding.cardRelative.setVisibility(View.VISIBLE);
                    bingding.cardTishi.setText(error.getMsg());
//                    carddatas = new CardsBean();
//                    adatper.setDatas(carddatas.getData());
                } else {
                    bingding.cardLv.setVisibility(View.VISIBLE);
                    bingding.cardRelative.setVisibility(View.GONE);
                    carddatas = gson.fromJson(result, CardsBean.class);
                    adatper.setDatas(carddatas.getData());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                toast(getString(R.string.getcardagain));
                bingding.cardLv.setVisibility(View.GONE);
                bingding.cardRelative.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                bingding.cardFresh.setRefreshing(false);
            }
        });

    }
}
