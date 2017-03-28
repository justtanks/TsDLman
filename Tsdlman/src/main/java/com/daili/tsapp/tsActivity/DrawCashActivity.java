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
import com.daili.tsapp.jsBean.netBean.LoginBean;
import com.daili.tsapp.jsBean.netBean.NetError;
import com.daili.tsapp.jsView.PwdEditText;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
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
    private LoginBean.DataBean datas = null;
    private String bankname;
    private String bankNum;
    SystemUtil su = new SystemUtil(this);
    //进入银行卡选择界面后返回的标识
    public static final int TOGETCARD = 1;

    ProgressDialog waitDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_cash);
        x.view().inject(this);
        init();
    }

    private void init() {

        changecard.setOnClickListener(this);
        backre.setOnClickListener(this);
        backText.setOnClickListener(this);
        quanbutixian.setOnClickListener(this);
        buttonTixian.setOnClickListener(this);
        moneyEdit.addTextChangedListener(new MyTextWathcer());
        datas = (LoginBean.DataBean) getIntent().getSerializableExtra("datas");
        currentnum.setText("可提现余额" + datas.getPartner_account_balance() + "元");
        setName(datas.getPartner_bank_card().get(0));
    }

    //设置界面内容
    private void setName(LoginBean.DataBean.PartnerBankCardBean data) {
        bankNum = data.getCard_num();
        bankname = data.getBank_name();
        bankName.setText(bankname + "(" + getLast4String(bankNum) + ")");
    }

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
//                quanbutixian();
                break;
            case R.id.tixian_button:
                tixian();
                break;

        }

    }

    /*
    选择新的卡进行提现
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
        if (resultCode == TOGETCARD) {
            Bundle bundle = data.getExtras();
            LoginBean.DataBean.PartnerBankCardBean datas = (LoginBean.DataBean.PartnerBankCardBean) bundle.getSerializable("datas1");
            setName(datas);
        }
    }

    /*
         弹出对话框输入按钮
         */
    private PopupWindow mPopuwindow;

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

    //访问网络进行提现 ：partner_id/3/bank_name/银行名称/bank_card_num/123/withdrawals_money/100000/withdrawals_password/123456
    //完成之后重新登录，返回主界面 重新更新数据
    private void drawMoneyFromNet(int money, String password) {
        waitDialog = ProgressDialog.show(this, "", "正在执行提现操作");
        waitDialog.show();
        Map<String, Object> parms = new HashMap<>();
        parms.put("partner_id", su.showUid());
        parms.put("bank_name", bankname);
        parms.put("bank_card_num", bankNum);
        parms.put("withdrawals_money", money);
        parms.put("withdrawals_password", password);
        loge("name" + bankname + "---num:" + bankNum);
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
//                    DrawMoneyBean bean = gson.fromJson(result, DrawMoneyBean.class);
//
//                    if (bean != null && "Success".equalsIgnoreCase(bean.getFlag())) {
//                        toast("提现成功，两小时之内到帐");
//                        update();
//                    }
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

//    private void quanbutixian() {
//        moneyEdit.setText(datas.getPartner_account_balance()+"");
//    }


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
                if (num > datas.getPartner_account_balance()) {
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
       设置button的点击状态
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

    private void update() {
        Map<String, String> params = new HashMap<>();
        params.put("partner_tel", su.showPhone());
        params.put("partner_password", su.showPwd());
        cancel = NetUtils.Get(BaseData.LOGIN, params, new Callback.CommonCallback<String>() {
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
                    LoginBean login = gson.fromJson(result, LoginBean.class);
                    if ("Success".equals(login.getFlag())) {
                        EventBus.getDefault().post(login);
                        Intent intenm = new Intent(DrawCashActivity.this, HomeActivity.class);
                        startActivity(intenm);
                    } else {
                        login = null;
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

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        datas=null;
        su=null;
        mPopuwindow=null;
        cancel=null;
        waitDialog=null;
    }
}
