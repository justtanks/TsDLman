package com.daili.tsapp.tsActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.daoBean.RegistArea;
import com.daili.tsapp.jsBean.daoBean.RegistUserMsg;
import com.daili.tsapp.jsBean.netBean.RegistResult;
import com.daili.tsapp.jsBean.netBean.Regist_phoneback;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsNet.Xutils;
import com.daili.tsapp.utils.ImageUtil2;
import com.daili.tsapp.utils.StringUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_regist)
public class RegistActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    @ViewInject(R.id.regist_back)
    private RelativeLayout registBack;
    @ViewInject(R.id.regist_headimg)
    private ImageView registHeadimg;
    @ViewInject(R.id.regist_radiogroup_sex)
    private RadioGroup registRadiogroupSex;
    @ViewInject(R.id.regist_radio_man)
    private RadioButton registRadioMan;
    @ViewInject(R.id.regist_radio_woman)
    private RadioButton registRadioWoman;
//    @ViewInject(R.id.regist_pull_dowm)
//    private ImageView registPullDowm;
    @ViewInject(R.id.regist_name)
    private EditText username;
    @ViewInject(R.id.regist_idnum)
    private EditText userid;
    @ViewInject(R.id.regist_phonenum)
    private EditText userphone;
    @ViewInject(R.id.regist_yanzheng)
    private EditText useryanzheng;
    @ViewInject(R.id.regist_worktime)
    private EditText userworktime;
    @ViewInject(R.id.regist_registbt)
    private Button registBt;
//    @ViewInject(R.id.regist_shanchanglingyu)
//    private TextView registText;
    @ViewInject(R.id.regist_password)
    private EditText userpassword;
    @ViewInject(R.id.regist_yanzhengobtainimg)
    private Button regist_obtainyanzheng;
    RegistUserMsg userMsg;
    PopupWindow mPopuWindow;
    //设置短信获取的时间
    private final  int duanxin=60;
    private List<RegistArea> bestArea = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();
    }

    private void init() {
        userMsg = new RegistUserMsg();
        registBack.setOnClickListener(this);
        registHeadimg.setOnClickListener(this);
        regist_obtainyanzheng.setOnClickListener(this);
        registBt.setOnClickListener(this);
//        registPullDowm.setOnClickListener(this);
        registRadiogroupSex.setOnCheckedChangeListener(this);
//        registText.setOnClickListener(this);
//        getBestArea();
    }

    //获取擅长领域第一层的标示   不用了
//    Callback.Cancelable cancelable;
//
//    private void getBestArea() {
//        cancelable = Xutils.Get(BaseData.GOODWORK1, new HashMap<String, String>(), new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Gson gson = new Gson();
//                BestWorkBean beW1 = gson.fromJson(result, BestWorkBean.class);
//                List<BestWorkBean.MsgBean> beans = beW1.getMsg();
//                for (BestWorkBean.MsgBean be : beans) {
//                    RegistArea are = new RegistArea(be.getSid(), be.getName());
//                    bestArea.add(are);
//                }
//                showPopuwindow();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                toast(getResources().getString(R.string.net_err));
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }

    //设置验证码的发送时间间隔
    private int beginTime = duanxin;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    regist_obtainyanzheng.setText("获取验证码" + "(" + beginTime + ")");
                    break;
                case 2:
                    beginTime=duanxin;
                    regist_obtainyanzheng.setEnabled(true);
                    regist_obtainyanzheng.setText(null);
                    regist_obtainyanzheng.setBackgroundResource(R.mipmap.yanzhengma);
                    break;
            }

        }
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
            if (beginTime > 0) {
                beginTime--;
                handler.postDelayed(runnable, 1000);
            } else {
                handler.sendEmptyMessage(2);
            }

        }
    };


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regist_back:
                onBackPressed();
                break;
            case R.id.regist_headimg:
                //点击选择图片
                choiseImag();
                break;
            case R.id.regist_yanzhengobtainimg:
                //点击获取验证码
                getWordFromNet();
                break;

//            case R.id.regist_pull_dowm:
//                //点击选择擅长领域
//
//                break;
//            case R.id.regist_shanchanglingyu:
//                getBestArea();
//                break;
            case R.id.regist_registbt:
                registExam();
                break;

        }
    }


    //点击之后弹出弹窗选择擅长领域

   /*
   展示第二层的popuwindow   显示擅长领域
    */
//    private void show2popuwindow(List<BestWork2Bean.MsgBean> beans) {
//        View popupView = getLayoutInflater().inflate(R.layout.popuwindow_regist_bestarea, null);
//        ListView mList = (ListView) popupView.findViewById(R.id.regist_bestarea_list);
//        mPopuWindow = new PopupWindow(popupView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);
//        mPopuWindow.setTouchable(true);
//        mPopuWindow.setOutsideTouchable(true);
//        mPopuWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.tab_background));
//        final List<RegistArea> ares = new ArrayList<>();
//        for (BestWork2Bean.MsgBean be : beans) {
//            ares.add(new RegistArea(be.getId(), be.getSide_name()));
//        }
//        ItemRegistBestareaListAdapter adapter = new ItemRegistBestareaListAdapter(this, ares);
//        mList.setAdapter(adapter);
//        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                userMsg.setFavouraeID(ares.get(position).getId());
//                registText.setText(ares.get(position).getText());
//                mPopuWindow.dismiss();
//
//            }
//        });
//        mPopuWindow.showAtLocation(regist_obtainyanzheng, Gravity.CENTER, 0, 0);
//    }

    /*
    检查是否为正确格式
     */
    private void registExam() {
        String userName = username.getText().toString();
        String userId = userid.getText().toString();
        String userPhone = userphone.getText().toString();
        String userpas = userpassword.getText().toString();
        String userback = useryanzheng.getText().toString();
        String userworkTime = userworktime.getText().toString();
        if (userMsg.getImageBase() == null) {
            toast(getResources().getString(R.string.choise_head));
            return;
        }

        if (null == userName || "".equals(userName)) {
            toast(getResources().getString(R.string.put_yourname));
            return;
        }

        if (null == userId || "".equals(userId)) {
            toast(getResources().getString(R.string.putyourid));
            return;
        }
        if (userpas == null && userpas.equals("")) {
            toast(getResources().getString(R.string.put_password));
            return;
        }
        if (userPhone == null && userPhone.equals("")) {
            toast(getResources().getString(R.string.put_yourphone));
            return;
        }
        if (userMsg.getSex() == null || "".equals(userMsg.getSex())) {
            toast(getResources().getString(R.string.choise_sex));
            return;
        }

        if (userback == null || !userback.equals(userMsg.getBackMsg())) {
            toast(getResources().getString(R.string.put_yanzhengma));
            return;
        }
        if (userworkTime == null || "".equals(userworkTime) || !StringUtils.isNumeric(userworkTime)) {
            toast(getResources().getString(R.string.choise_worktime));
            return;

        }
//        if (null == userMsg.getFavouraeID() || "".equals(userMsg.getFavouraeID())) {
//            toast(getResources().getString(R.string.putyourbestwork));
//            return;
//        }
        userMsg.setName(userName);
        userMsg.setPersionId(userId);
        userMsg.setPhoneNum(userPhone);
        userMsg.setWorkTime(userworkTime);
        userMsg.setPassword(userpas);
        registOnNet(userMsg);
    }

    /*
      网络注册
      agent_name/shaowei/agent_id_card/222222222222222
/agent_pic/000000/agent_sex/1)/agent_tel/15621295399/agent_work_time/工作年限(int)/agent_good_work/擅长业务(int)/agent_password/(密码)
     */
    private void registOnNet(RegistUserMsg userMsg) {
        Map<String, Object> parms = new HashMap<>();
        parms.put("agent_name", userMsg.getName());
        parms.put("agent_id_card", userMsg.getPersionId());
        parms.put("agent_pic", userMsg.getImageBase());
        parms.put("agent_sex", userMsg.getSex());
        parms.put("agent_tel", userMsg.getPhoneNum());
        parms.put("agent_work_time", userMsg.getWorkTime());
//        parms.put("agent_good_work", userMsg.getFavouraeID());
        parms.put("agent_password", userMsg.getPassword());
        registBt.setEnabled(false);
        Xutils.Post(BaseData.REGISTER, parms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                RegistResult success = gson.fromJson(result, RegistResult.class);
                if (success != null) {
                    if (success.getFlag().equals("Success")) {
                        toast(getString(R.string.regist_success));
                        //跳转登录界面
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        toast(success.getMsg().toString());
                    }
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
                registBt.setEnabled(true);
            }
        });

    }


    /*
    打开相册选择图片
     */
    private static final int IMAGE = 1;

    private void choiseImag() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            if (selectedImage != null) {
                File file = null;
                file = ImageUtil2.getFileFromMediaUri(this, selectedImage);
                try {
                    Bitmap bitmap = ImageUtil2.getBitmapFormUri(this, selectedImage);
                    loge(bitmap.getByteCount() + "");
                    int degree = ImageUtil2.getBitmapDegree(file.getAbsolutePath());
                    Bitmap newbitmap = ImageUtil2.rotateBitmapByDegree(bitmap, degree);
                    registHeadimg.setImageBitmap(newbitmap);
                    userMsg.setImageBase(newbitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    private void getWordFromNet() {

        String phone = userphone.getText().toString();
        if (null == phone || "".equals(phone)) {
            toast(getString(R.string.right_phonenum));
        } else {
            regist_obtainyanzheng.setBackground(null);
            handler.post(runnable);
            regist_obtainyanzheng.setEnabled(false);
            userMsg.setPhoneNum(phone);
            Map<String, String> apends = new HashMap<>();
            apends.put("agent_tel", phone);
            Xutils.Get(BaseData.DUANXINYANZHENG, apends, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Gson gson = new Gson();
                    Regist_phoneback phoneMsg = gson.fromJson(result, Regist_phoneback.class);
                    userMsg.setBackMsg(phoneMsg.getMsg());

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    toast(getString(R.string.check_thenet));

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

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Log.e("text_json", i + "");
        switch (i) {

            case R.id.regist_radio_man:
                userMsg.setSex(1 + "");
                break;
            case R.id.regist_radio_woman:
                userMsg.setSex(2 + "");
                break;
        }

    }

}
