package com.daili.tsapp.tsActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.CardsBean;
import com.daili.tsapp.jsBean.netBean.DrawCashDoneBean;
import com.daili.tsapp.jsBean.netBean.LoginBean;
import com.daili.tsapp.jsBean.netBean.NetError;
import com.daili.tsapp.jsView.PwdEditText;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/*
余额提现界面
 */
public class DrawCashActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.tixian_number)
    EditText moneyEdit;
    @ViewInject(R.id.tixian_current)
    TextView currentnum;
    @ViewInject(R.id.tixian_quanbutixian)
    TextView quanbutixian;
    @ViewInject(R.id.tixian_button)
    Button buttonTixian;
    @ViewInject(R.id.drawcash_changecard)
    RelativeLayout changecard;
    @ViewInject(R.id.drawcash_back_rela)
    RelativeLayout backre;
    @ViewInject(R.id.draw_back_text)
    TextView backText;
    @ViewInject(R.id.tixian_bank)
    TextView bankName;
    //初始数据
    private CardsBean  datas = null;
    private String bankname;
    private String bankNum;
    SystemUtil su = new SystemUtil(this);
    //进入银行卡选择界面后返回的标识
    public static final int TOGETCARD = 1;
    ProgressDialog waitDialog = null;
    int largestMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_cash);
        x.view().inject(this);
        init();
    }

    private void init() {
        EventBus.getDefault().register(this);
        changecard.setOnClickListener(this);
        backre.setOnClickListener(this);
        backText.setOnClickListener(this);
        quanbutixian.setOnClickListener(this);
        buttonTixian.setOnClickListener(this);
        moneyEdit.addTextChangedListener(new MyTextWathcer());
        datas = (CardsBean) getIntent().getSerializableExtra("cards");
        largestMoney=getIntent().getIntExtra("allmoney",0);
        currentnum.setText(largestMoney+"元");
        setName(datas.getData().get(0));
    }

    //设置界面内容 包括银行卡号和银行名称
    private void setName(CardsBean.DataBean card) {
        bankNum = card.getCard_num();
        bankname = card.getBrank_name();
        bankName.setText(bankname + "(" + getLast4String(bankNum) + ")");
    }
//获取到银行卡后四位
    private String getLast4String(String num) {
        if (num.length() >= 4) {
            String strsub1 = num.substring(num.length() - 4, num.length());
            return strsub1;
        } else {
            return num;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawcash_changecard:
                getNewCard();
                break;
            case R.id.draw_back_text:
            case R.id.drawcash_back_rela:
                onBackPressed();
                break;
            case R.id.tixian_quanbutixian:
                quanbutixian();
                break;
            case R.id.tixian_button:
                tixian();
                break;

        }

    }

    /*
    选择新的卡进行提现 需要将银行卡信息传到上面
    这里就需要对网络进行访问 然后传递银行卡信息
     */
    private void getNewCard() {
        Intent intent = new Intent(this, ChoiseCardActivity.class);
       // 选择后返回，将数据设置到text上
        intent.putExtra("datas", datas);
        startActivityForResult(intent, TOGETCARD);
    }

    //返回数据时将数据设置到textview上,并修改传入到接口的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == TOGETCARD&&data.getExtras()!=null) {
            Bundle bundle = data.getExtras();
            CardsBean.DataBean datas = (CardsBean.DataBean) bundle.getSerializable("datas1");
            if(data!=null){
                setName(datas);
            }
        }
    }

    //添加银行卡之后的刷新
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventList(CardsBean datas) {
        this.datas= datas;
    }
        /*
         弹出对话框输入按钮
         */
    private PopupWindow mPopuwindow;
// 弹出对话框 输入密码
    private void tixian() {
        View popupView = getLayoutInflater().inflate(R.layout.dialog_tixian_password, null);
        ImageView backimg = (ImageView) popupView.findViewById(R.id.dialog_mima_back);
        TextView moneyNum = (TextView) popupView.findViewById(R.id.dialog_tixian_textView4);
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
        String text = moneyEdit.getText().toString();
        final int money = Integer.parseInt(text);
        moneyNum.setText("￥" + money);
        password.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {
            @Override
            public void onInputFinish(String password) {
                drawMoneyFromNet(money, password);
                mPopuwindow.dismiss();
            }
        });
        mPopuwindow.showAtLocation(backre, Gravity.CENTER, 0, 0);

    }


    ///Waiter/waiter_get_money/waiter_id/71/money/100/payment_password/123456  card_num ,bank_name
    private void drawMoneyFromNet(int money, String password) {
        waitDialog = ProgressDialog.show(this, "", "正在执行提现操作");
        waitDialog.show();
        Map<String, Object> parms = new HashMap<>();
        parms.put("waiter_id", su.showUid());
        parms.put("bank_name", bankname);
        parms.put("card_num", bankNum);
        parms.put("money", money);
        parms.put("payment_password", password);
        NetUtils.Post(BaseData.DRAW_MONEY, parms, new Callback.CommonCallback<String>() {
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
                    DrawCashDoneBean bean = gson.fromJson(result, DrawCashDoneBean.class);
                    if (bean != null && "Success".equalsIgnoreCase(bean.getFlag())) {
                        toast("提现成功，两小时之内到帐");
                        update();
                    }
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
                waitDialog.dismiss();
            }
        });


    }

    private void quanbutixian() {
        moneyEdit.setText(largestMoney+"");
    }

/*
  提现按钮变化监听
 */
    private class MyTextWathcer implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            buttonTixian.setBackground(getResources().getDrawable(R.drawable.selector_tixianbutton));
        }

        @Override
        public void afterTextChanged(Editable s) {

            String money = moneyEdit.getText().toString();

            if (money == null || "".equals(money)) {
                setButton(false);
                return;
            }
            int num = Integer.parseInt(money);
            if (num > 0) {
                if (num > largestMoney) {
                    toast("超出可提现余额，不能提现");
                    setButton(false);
                    return;
                } else {
                    setButton(true);
                }

            } else {
                setButton(false);
            }
        }
    }

    /*
       设置button的点击状态  默认没有文字不可以点击
     */
    private void setButton(boolean used) {
        if (used) {
            buttonTixian.setEnabled(true);
            buttonTixian.setBackgroundResource(R.drawable.selector_tixianbutton);
        } else {
            buttonTixian.setEnabled(false);
            buttonTixian.setBackgroundResource(R.mipmap.tixain_nonumber);
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    private void setButtonBack(int text) {

    }

    Callback.Cancelable cancel;
    /*
     更新首界面数据 通知首页重新设置数据
     */
    private void update() {
       EventBus.getDefault().post(new Integer(111));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        datas=null;
        su=null;
        mPopuwindow=null;
        cancel=null;
        waitDialog=null;
    }
}
