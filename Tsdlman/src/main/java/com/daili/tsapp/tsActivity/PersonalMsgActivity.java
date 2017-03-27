package com.daili.tsapp.tsActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.LoginBean2;
import com.daili.tsapp.jsBean.netBean.PersonShanchang;
import com.daili.tsapp.jsBean.netBean.ShanchangBean;
import com.daili.tsapp.jsView.ItemContainer;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsDB.DButils;
import com.daili.tsapp.tsNet.Xutils;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PersonalMsgActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.person_back)
    ImageView back;
    @ViewInject(R.id.persionan_head)
    ImageView head;
    @ViewInject(R.id.persion_name)
    TextView name;
    @ViewInject(R.id.persion_phone)
    TextView phone;
    @ViewInject(R.id.persion_email)
    TextView email;
    @ViewInject(R.id.persion_sex)
    TextView sex;
    @ViewInject(R.id.persion_location)
    TextView location;
    @ViewInject(R.id.persion_sign)
    TextView sign;
    @ViewInject(R.id.persion_textback)
    TextView textBack;
    @ViewInject(R.id.persion_shanchang)
    RelativeLayout shanchang;
    @ViewInject(R.id.persion_container)
    ItemContainer container;
    LoginBean2.DataBean bean;//从数据库拿出个人信息
    ShanchangBean shanchangbean;// 擅长领域设置的bean
    Intent intent;
    ArrayList<Integer> gerenshanchan;//从网络请求的个人擅长领域
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_msg);
        x.view().inject(this);
        init();

    }

    private void init() {
        back.setOnClickListener(this);
        textBack.setOnClickListener(this);
        shanchang.setOnClickListener(this);
        gerenshanchan = (ArrayList<Integer>) getIntent().getIntegerArrayListExtra("gerenshanchang");
        if (gerenshanchan != null && gerenshanchan.size() != 0) {
            setContainer();
        }
        try {
            bean = DButils.DB.findFirst(LoginBean2.DataBean.class);
            if (bean.getWaiter_pic() != null) {
                String image = bean.getWaiter_pic();
                if (image.startsWith(".")) {
                    String simg = bean.getWaiter_pic().substring(1, image.length());
                    x.image().bind(head, BaseData.BASEURL + simg);
                } else {
                    Picasso.with(this).load(BaseData.BASEURL + "//" + image).into(head);
                }

            }
            if (bean.getWaiter_name() != null) {
                name.setText(bean.getWaiter_name());
            }
            if (bean.getWaiter_phone_number() != null) {
                phone.setText(bean.getWaiter_phone_number());
            }
            String sextring = bean.getWaiter_sex();
            if (sextring != null) {
                if (sextring.equals("1")) {
                    sex.setText("男");
                } else {
                    sex.setText("女");
                }

            }
            if (bean.getWaiter_address() != null) {
                location.setText(bean.getWaiter_address());
            }
            if (bean.getWaiter_work_time() != null) {

            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    //从数据库拿到数据，然后进行显示
    List<String> showItems;

    private void setContainer() {
        Gson gson = new Gson();
        showItems = new ArrayList<>();
        shanchangbean = gson.fromJson(msg, ShanchangBean.class);
        for (ShanchangBean.MsgBean m : shanchangbean.getMsg()) {
            for (ShanchangBean.MsgBean.SidSideBean s : m.getSid_side()) {
                if (gerenshanchan.contains(s.getId())) {
                    showItems.add(s.getSide_name());
                }
            }
        }
        addItemTocontainer(showItems);
    }

    private void addItemTocontainer(List<String> btnames) {
     container.removeAllViews();
        for (String s : btnames) {
            addBt(s);
        }
    }

    private void addBt(String name) {
        Button button = new Button(getApplicationContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , 0);
        button.setLayoutParams(params);
        button.setText(name);
        button.setGravity(Gravity.CENTER);
        int s = new Random().nextInt(7)%(7-1+1) + 1;
        switch (s){
            case 1:
                button.setBackgroundResource(R.mipmap.msg_1);
                break;
            case 2:
                button.setBackgroundResource(R.mipmap.msg_2);
                break;
            case 3:
                button.setBackgroundResource(R.mipmap.msg_3);
                break;
            case 4:
                button.setBackgroundResource(R.mipmap.msg_4);
                break;
            case 5:
                button.setBackgroundResource(R.mipmap.msg_5);
                break;
            case 6:
                button.setBackgroundResource(R.mipmap.msg_6);
                break;
            case 7:
                button.setBackgroundResource(R.mipmap.msg_7);
                break;
        }
        button.setTextSize(8);
        button.setTextColor(Color.argb(255, 47, 79, 79));
        container.addView(button);
    }

    //更新网络擅长领域
    //waiter_id/71/good_work/1,2,3,4,5,6,7,8,9
    SystemUtil su = new SystemUtil(this);

    private void changOnnet() {
        dialog = ProgressDialog.show(this, "", getString(R.string.gengxinshuju));
        dialog.show();
        Map<String, Object> parms = new HashMap<>();
        parms.put("waiter_id",su.showUid());
        parms.put("good_work", ControlJinDuActivity.list2Str(nowShanchang));
        Xutils.Post(BaseData.GENGGAISHANCHANG, parms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                gerenshanchan = (ArrayList<Integer>) nowShanchang;
                setContainer();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                 toast(getString(R.string.gengxinshibai));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person_back:
                onBackPressed();
                break;
            case R.id.persion_textback:
                onBackPressed();
                break;
            case R.id.persion_shanchang:
                toShanchang();
                break;

        }
    }

    //跳到设置擅长领域界面
    private void toShanchang() {

//        NetUtils.Post(BaseData.GOODWORK, null, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
                Gson gson = new Gson();
                shanchangbean = gson.fromJson(msg, ShanchangBean.class);//ischoise 默认为0 所以。。
                intent = new Intent(PersonalMsgActivity.this, ShanChangActivity.class);
                intent.putExtra("shanchang", shanchangbean);
                intent.putIntegerArrayListExtra("gerenshanchang", gerenshanchan);
                startActivityForResult(intent, 10);
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                toast(getResources().getString(R.string.net_err));
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

    }

    List<Integer> nowShanchang = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 12) {
            nowShanchang = data.getIntegerArrayListExtra("shanchang1");
            changOnnet();
        }
    }

    //从服务器上请求的字符串
    String msg = "{\n" +
            "    \"flag\": \"Success\",\n" +
            "    \"msg\": [\n" +
            "        {\n" +
            "            \"sid\": \"1\",\n" +
            "            \"name\": \"商标基础业务\",\n" +
            "            \"time\": \"2016-12-27 11:28:56\",\n" +
            "            \"sid_side\": [\n" +
            "                {\n" +
            "                    \"id\": \"1\",\n" +
            "                    \"side_name\": \"普通注册\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 11:34:09\",\n" +
            "                    \"side_price\": \"597\",\n" +
            "                    \"percentage_price\": \"200\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/putongzhuce.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"2\",\n" +
            "                    \"side_name\": \"担保注册\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 11:35:25\",\n" +
            "                    \"side_price\": \"1700\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/danbaozhuce.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"3\",\n" +
            "                    \"side_name\": \"无忧注册\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 11:36:08\",\n" +
            "                    \"side_price\": \"1200\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/wuyouzhuce.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"4\",\n" +
            "                    \"side_name\": \"变更\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 11:36:25\",\n" +
            "                    \"side_price\": \"1000\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/biangeng.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"5\",\n" +
            "                    \"side_name\": \"更正\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 11:36:45\",\n" +
            "                    \"side_price\": \"1000\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/gengzheng.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"6\",\n" +
            "                    \"side_name\": \"续展\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 11:37:17\",\n" +
            "                    \"side_price\": \"2700\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/xuzhan.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"7\",\n" +
            "                    \"side_name\": \"宽展\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 11:37:43\",\n" +
            "                    \"side_price\": \"3000\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/kuanzhan.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"8\",\n" +
            "                    \"side_name\": \"转让\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:20:11\",\n" +
            "                    \"side_price\": \"1500\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/zhuanrang.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"9\",\n" +
            "                    \"side_name\": \"许可备案\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:20:15\",\n" +
            "                    \"side_price\": \"800\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/xukebeian.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"10\",\n" +
            "                    \"side_name\": \"商标证补证\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:20:58\",\n" +
            "                    \"side_price\": \"1500\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/shangbiaobuzheng.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"11\",\n" +
            "                    \"side_name\": \"转变续补证\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:21:26\",\n" +
            "                    \"side_price\": \"600\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/zhuanbianxubuzheng.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"12\",\n" +
            "                    \"side_name\": \"商标注销\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:21:45\",\n" +
            "                    \"side_price\": \"500\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/shangbiaozhuxiao.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"34\",\n" +
            "                    \"side_name\": \"智能商标注册\",\n" +
            "                    \"side_belong_id\": \"1\",\n" +
            "                    \"side_add_time\": \"2017-02-13 15:13:35\",\n" +
            "                    \"side_price\": \"597\",\n" +
            "                    \"percentage_price\": \"200\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/zhinengshangbiaozhuce.png\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"sid\": \"2\",\n" +
            "            \"name\": \"商标案件业务\",\n" +
            "            \"time\": \"2016-12-27 11:29:12\",\n" +
            "            \"sid_side\": [\n" +
            "                {\n" +
            "                    \"id\": \"13\",\n" +
            "                    \"side_name\": \"驳回复审\",\n" +
            "                    \"side_belong_id\": \"2\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:22:20\",\n" +
            "                    \"side_price\": \"3500\",\n" +
            "                    \"percentage_price\": \"200\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/70c5fb6f14ba1d217fcfdc03c4076ac0.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/bohuifushen.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"14\",\n" +
            "                    \"side_name\": \"商标异议\",\n" +
            "                    \"side_belong_id\": \"2\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:22:45\",\n" +
            "                    \"side_price\": \"2700\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/70c5fb6f14ba1d217fcfdc03c4076ac0.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/shangbiaoyiyi.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"15\",\n" +
            "                    \"side_name\": \"撤三\",\n" +
            "                    \"side_belong_id\": \"2\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:23:05\",\n" +
            "                    \"side_price\": \"2700\",\n" +
            "                    \"percentage_price\": \"200\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/70c5fb6f14ba1d217fcfdc03c4076ac0.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/chesan.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"16\",\n" +
            "                    \"side_name\": \"撤三复审\",\n" +
            "                    \"side_belong_id\": \"2\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:23:23\",\n" +
            "                    \"side_price\": \"3500\",\n" +
            "                    \"percentage_price\": \"200\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/70c5fb6f14ba1d217fcfdc03c4076ac0.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/chesanfushen.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"17\",\n" +
            "                    \"side_name\": \"无效宣告\",\n" +
            "                    \"side_belong_id\": \"2\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:23:41\",\n" +
            "                    \"side_price\": \"4000\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/70c5fb6f14ba1d217fcfdc03c4076ac0.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/wuxiaoxuangao.png\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"sid\": \"3\",\n" +
            "            \"name\": \"版权业务\",\n" +
            "            \"time\": \"2016-12-27 11:29:46\",\n" +
            "            \"sid_side\": [\n" +
            "                {\n" +
            "                    \"id\": \"18\",\n" +
            "                    \"side_name\": \"一般作品登记（美术、音乐、文字、模型等）\",\n" +
            "                    \"side_belong_id\": \"3\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:24:50\",\n" +
            "                    \"side_price\": \"498\",\n" +
            "                    \"percentage_price\": \"200\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/yibanzuopindengji.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"19\",\n" +
            "                    \"side_name\": \"一般作品加急登记（美术、音乐、文字、模型等）\",\n" +
            "                    \"side_belong_id\": \"3\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:25:13\",\n" +
            "                    \"side_price\": \"898\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/yibanzuopinjiajidnegji.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"20\",\n" +
            "                    \"side_name\": \"一般作品普特急登记\",\n" +
            "                    \"side_belong_id\": \"3\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:25:39\",\n" +
            "                    \"side_price\": \"2000\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/yibanzuopintejidengji.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"21\",\n" +
            "                    \"side_name\": \"一般作品变更\",\n" +
            "                    \"side_belong_id\": \"3\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:26:07\",\n" +
            "                    \"side_price\": \"700\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/yibanzuopingenggai.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"22\",\n" +
            "                    \"side_name\": \"一般作品转让\",\n" +
            "                    \"side_belong_id\": \"3\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:26:37\",\n" +
            "                    \"side_price\": \"900\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/yibanzuopinzhuanrang.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"23\",\n" +
            "                    \"side_name\": \"一般作品补证\",\n" +
            "                    \"side_belong_id\": \"3\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:27:01\",\n" +
            "                    \"side_price\": \"600\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/yibanzuopinbuzheng.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"24\",\n" +
            "                    \"side_name\": \"软件著作权登记\",\n" +
            "                    \"side_belong_id\": \"3\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:27:37\",\n" +
            "                    \"side_price\": \"2400\",\n" +
            "                    \"percentage_price\": \"200\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/ruanjianzzhuzuoquan.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"25\",\n" +
            "                    \"side_name\": \"软件著作权加急登记\",\n" +
            "                    \"side_belong_id\": \"3\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:28:01\",\n" +
            "                    \"side_price\": \"4600\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/ruanjianzhuzuoquanjiaji.png\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"sid\": \"4\",\n" +
            "            \"name\": \"专利申请\",\n" +
            "            \"time\": \"2016-12-27 11:29:57\",\n" +
            "            \"sid_side\": [\n" +
            "                {\n" +
            "                    \"id\": \"26\",\n" +
            "                    \"side_name\": \"发明专利申请\",\n" +
            "                    \"side_belong_id\": \"4\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:28:44\",\n" +
            "                    \"side_price\": \"2999\",\n" +
            "                    \"percentage_price\": \"600\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/a82e2958c13f4d90ab9aeae8c076f740.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/famingzhuanlishenqing.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"27\",\n" +
            "                    \"side_name\": \"实用新型专利\",\n" +
            "                    \"side_belong_id\": \"4\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:29:12\",\n" +
            "                    \"side_price\": \"1500\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/a82e2958c13f4d90ab9aeae8c076f740.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/shiyongxinxizhuanli.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"28\",\n" +
            "                    \"side_name\": \"外观专利申请\",\n" +
            "                    \"side_belong_id\": \"4\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:29:29\",\n" +
            "                    \"side_price\": \"500\",\n" +
            "                    \"percentage_price\": \"100\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/a82e2958c13f4d90ab9aeae8c076f740.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/waiguanzhuanlishenqing.png\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"sid\": \"5\",\n" +
            "            \"name\": \"法律服务\",\n" +
            "            \"time\": \"2016-12-27 11:30:11\",\n" +
            "            \"sid_side\": [\n" +
            "                {\n" +
            "                    \"id\": \"29\",\n" +
            "                    \"side_name\": \"法律年度顾问\",\n" +
            "                    \"side_belong_id\": \"5\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:29:55\",\n" +
            "                    \"side_price\": \"5999\",\n" +
            "                    \"percentage_price\": \"500\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/a82e2958c13f4d90ab9aeae8c076f740.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/falvnianduguwen.png\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"sid\": \"6\",\n" +
            "            \"name\": \"科技项目\",\n" +
            "            \"time\": \"2016-12-27 11:30:25\",\n" +
            "            \"sid_side\": [\n" +
            "                {\n" +
            "                    \"id\": \"30\",\n" +
            "                    \"side_name\": \"高新技术企业认证\",\n" +
            "                    \"side_belong_id\": \"6\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:30:24\",\n" +
            "                    \"side_price\": \"20000\",\n" +
            "                    \"percentage_price\": \"800\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/a82e2958c13f4d90ab9aeae8c076f740.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/gaoxijishuqiyerenzheng.png\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"sid\": \"7\",\n" +
            "            \"name\": \"国际商标注册\",\n" +
            "            \"time\": \"2016-12-27 11:30:38\",\n" +
            "            \"sid_side\": [\n" +
            "                {\n" +
            "                    \"id\": \"31\",\n" +
            "                    \"side_name\": \"一标一类\",\n" +
            "                    \"side_belong_id\": \"7\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:30:46\",\n" +
            "                    \"side_price\": \"1000\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/b457446046d1b887c8c3fe05c2e575be.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/yibiaoyilei.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"32\",\n" +
            "                    \"side_name\": \"一标两类\",\n" +
            "                    \"side_belong_id\": \"7\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:31:15\",\n" +
            "                    \"side_price\": \"2400\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/b457446046d1b887c8c3fe05c2e575be.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/yibiaolianglei.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": \"33\",\n" +
            "                    \"side_name\": \"一标三类\",\n" +
            "                    \"side_belong_id\": \"7\",\n" +
            "                    \"side_add_time\": \"2016-12-27 16:31:32\",\n" +
            "                    \"side_price\": \"1000\",\n" +
            "                    \"percentage_price\": \"300\",\n" +
            "                    \"side_pic\": \"/Public/Home/img/side/b457446046d1b887c8c3fe05c2e575be.jpg\",\n" +
            "                    \"side_jieshao_pic\": \"/Public/Home/img/sides/yibiaosanlei.png\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"data\": [],\n" +
            "    \"num\": 1\n" +
            "}";
}
