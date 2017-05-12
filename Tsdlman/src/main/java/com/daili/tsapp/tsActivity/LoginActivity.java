package com.daili.tsapp.tsActivity;

import android.app.ProgressDialog;
import android.content.Intent;

import android.support.annotation.Nullable;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.TabHomeBinding;
import com.daili.tsapp.jsBean.netBean.ErrorBean;
import com.daili.tsapp.jsBean.netBean.LoginBean2;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsDB.DButils;
import com.daili.tsapp.tsNet.Xutils;
import com.daili.tsapp.utils.SystemUtil;

import com.google.gson.Gson;

import com.umeng.analytics.MobclickAgent;

import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {
    @ViewInject(R.id.login_back_back)
    private ImageView backImg;
    @ViewInject(R.id.login_regist)
    private Button regist;
    @ViewInject(R.id.login_relative1)
    private RelativeLayout phoneLayout;
    @ViewInject(R.id.login_relative2)
    private RelativeLayout passLayout;
    @ViewInject(R.id.login_edit_phone)
    private EditText phone;
    @ViewInject(R.id.login_edit_pass)
    private EditText pass;
    @ViewInject(R.id.login_forgetpass)
    private ImageView forgetPass;
    @ViewInject(R.id.login_loginbutton)
    private Button loginbt;

    Callback.Cancelable cancel;
    private SystemUtil su = new SystemUtil(this);
    ProgressDialog dialog = null;
    Map<String, Boolean> isEnable = new HashMap<>();
    public static String DATAS_KEY = "datas";//使用合伙人代码时候加入的 暂时不要删除
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();
        autoLogin();
    }

    private void init() {
        backImg.setOnClickListener(this);
        regist.setOnClickListener(this);
        forgetPass.setOnClickListener(this);
        loginbt.setOnClickListener(this);
        phone.setOnFocusChangeListener(this);
        pass.setOnFocusChangeListener(this);
        phone.addTextChangedListener(new NameWatcher());
        pass.addTextChangedListener(new PWatcher());
        isEnable.put("name", false);
        isEnable.put("pass", false);

    }

    //跳转到首页activity
    private void jumpToFindforActivity() {
        Intent intent = new Intent(this, TabHomeActivity.class);
        startActivityForResult(intent, 1122);

    }

    //自动登录 首先处理好数据传输问题 因为是所哟数据一起进行传递，所以需要找个地方访问数据
    private void autoLogin() {
        if ("".equals(su.showPhone()) || su.showPhone() == null || "".equals(su.showPwd()) || su.showPwd() == null) {
            return;
        } else {
            intent = new Intent(this, TabHomeActivity.class);
            intent.putExtra("isfresh", getIntent().getIntExtra("isfresh", 0));
            startActivityForResult(intent, 1122);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 3333) {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back_back:
                onBackPressed();
                break;
            case R.id.login_regist:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.login_forgetpass:
                getPass();
                break;
            case R.id.login_loginbutton:
                loginToNet();
                break;
        }
    }


    //登录操作
    private void loginToNet() {
        final String name = phone.getText().toString();
        final String pasword = pass.getText().toString();
        if (null == name || "".equals(name)) {
            Toast.makeText(this, getResources().getString(R.string.right_phone), Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == name || "".equals(name)) {
            toast(getResources().getString(R.string.inputpassword));
            return;
        }
        dialog = ProgressDialog.show(this, "", "正在登陆。。。");
        dialog.show();
        Map<String, String> passkey = new HashMap<>();
        passkey.put("agent_tel", name);
        passkey.put("agent_password", pasword);
        cancel = Xutils.Get(BaseData.LOGIN, passkey, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    if (result.substring(0, 18).contains("Error")) {
                        ErrorBean bean = gson.fromJson(result, ErrorBean.class);
                        toast(bean.getMsg());
                        return;
                    }
                    final LoginBean2 bean = gson.fromJson(result, LoginBean2.class);
                    if ("Success".equals(bean.getFlag())) {
                        saveNameAndPass(name, pasword, bean.getData().get(0).getWaiter_id(), bean.getData().get(0).getIs_renzheng());
                        MobclickAgent.onProfileSignIn(name);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    DButils.DB.dropTable(LoginBean2.DataBean.class);
                                    DButils.DB.save(bean.getData().get(0));
                                } catch (DbException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        jumpToFindforActivity();

                    } else {
                        toast(getResources().getString(R.string.passwordError));
                    }

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                toast(getResources().getString(R.string.net_err));

            }

            @Override
            public void onCancelled(CancelledException cex) {
                cancel.cancel();
            }

            @Override
            public void onFinished() {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            cancel.cancel();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    //保存用户名和密码
    private void saveNameAndPass(String name, String pass, String uid, int isrenzheng) {
        su.savePhone(name);
        su.savePwd(pass);
        su.saveUid(Integer.parseInt(uid));
        su.saveIsRenZheng(isrenzheng);
        su.saveIsRenZheng(isrenzheng);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {
            case R.id.login_edit_phone:
                if (hasFocus) {
                    phoneLayout.setBackground(getResources().getDrawable(R.mipmap.login_edittext));
                } else {
                    phoneLayout.setBackground(getResources().getDrawable(R.mipmap.login_beforeedit));
                }
                break;
            case R.id.login_edit_pass:
                if (hasFocus) {
                    passLayout.setBackground(getResources().getDrawable(R.mipmap.login_edittext));

                } else {
                    passLayout.setBackground(getResources().getDrawable(R.mipmap.login_beforeedit));
                }
                break;

        }
    }

    //跳转到修改密码界面
    public void getPass() {
        Intent intent = new Intent(this, GetPassActivity.class);
        startActivity(intent);
    }

    class NameWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = phone.getText().toString();
            if (null != text && !"".equals(text)) {
                isEnable.put("name", true);
            } else {
                isEnable.put("name", false);
            }
            judge();
        }
    }

    class PWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = pass.getText().toString();
            if (null != text && !"".equals(text)) {
                isEnable.put("pass", true);
            } else {
                isEnable.put("pass", false);
            }
            judge();

        }
    }

    //设置点击按钮可以点击 及变化颜色
    private void judge() {
        if (isEnable.get("name") && isEnable.get("pass")) {
            loginbt.setBackgroundResource(R.drawable.selector_tixianbutton);
            loginbt.setEnabled(true);
        } else {
            loginbt.setBackgroundResource(R.mipmap.tixain_nonumber);
            loginbt.setEnabled(false);
        }
    }
}