package com.daili.tsapp.tsActivity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.ExpendListChildBean;
import com.daili.tsapp.jsBean.ExpendListGroupBean;
import com.daili.tsapp.jsBean.GetExpendListDatas;
import com.daili.tsapp.jsBean.netBean.FormListnew;
import com.daili.tsapp.jsBean.netBean.NetError;
import com.daili.tsapp.jsBean.netBean.SendMsgResultBean;
import com.daili.tsapp.tsAdapter.ExpandBleadapter;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsNet.Xutils;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 进度变化的activity
 */
public class ControlJinDuActivity extends BaseActivity implements View.OnClickListener, ExpandableListView.OnChildClickListener {
    RelativeLayout back;
    ExpandableListView lv;
    ExpandBleadapter adapter;
    GetExpendListDatas datas = new GetExpendListDatas();//展示expendlistview的数据
    List<ExpendListGroupBean> groupbean = null;
    List<List<ExpendListChildBean>> childbean = null;
    TextView jindu;  //当前进度
    Intent intent;
    FormListnew.DataBean xiangqingdatas;
    //管理进度的List 通过对其中数字的控制，控制点击效果变化
    List<Integer> contrsList = new ArrayList<>();
    List<Integer> savaList;
    AlertDialog.Builder builder;
    //更改状态的时候显示的dialog
    ProgressDialog dialog = null;
    SystemUtil su;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_jin_du);
        getDataFromXiangqingActivity();
        init();
    }

    private void init() {
        su = new SystemUtil(this);
        back = (RelativeLayout) this.findViewById(R.id.jindu_back);
        lv = (ExpandableListView) this.findViewById(R.id.jindu_expendlistview);
        lv.setGroupIndicator(null);
        lv.setOnChildClickListener(this);
        builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.item_expand_headview, null);
        ImageView head = (ImageView) view.findViewById(R.id.jindu_head);
        TextView shanbiaoname = (TextView) view.findViewById(R.id.jindu_shangbiaoname);
        TextView zhucehao = (TextView) view.findViewById(R.id.jindu_zhucehao);
        TextView shenqingren = (TextView) view.findViewById(R.id.jindu_shenqinren);
        //绑定headview中的数据
        if (xiangqingdatas != null) {
            x.image().bind(head, BaseData.BASEURL + xiangqingdatas.getOrder_pic());
            shanbiaoname.setText(xiangqingdatas.getShangbiao_name());
            zhucehao.setText(xiangqingdatas.getOrder_num());
            shenqingren.setText(xiangqingdatas.getWho_put_order());
            childbean = datas.setListBackData(contrsList);
        } else {
            childbean = datas.setIndex("[1]");
        }
        jindu = (TextView) view.findViewById(R.id.jindu_step);
        /*
        设置headview 的内容
         */
        jindu.setText(datas.getTextByIndex(contrsList.get(contrsList.size() - 1)));
        back.setOnClickListener(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        lv.addHeaderView(view);
        groupbean = datas.getGroups();
        adapter = new ExpandBleadapter(this, groupbean, childbean);
        lv.setAdapter(adapter);
    }

    //获取从详情界面传递过来的数据
    void getDataFromXiangqingActivity() {
        intent = getIntent();
        xiangqingdatas = (FormListnew.DataBean) intent.getSerializableExtra("xiangqing");
        loge(intent.getStringExtra("jindu"));
        contrsList = str2list(intent.getStringExtra("jindu"));
        if (!contrsList.contains(1)) {
            contrsList.add(1);
        }
        savaList = new ArrayList(contrsList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jindu_back:
                onBackPressed();
                break;
        }
    }

    //将最初的返回的字符串转换为list
    private List<Integer> str2list(String str) {
        str = str.replaceAll("\\s*", "");
        List<Integer> ints = new ArrayList<>();
        StringBuffer sf = new StringBuffer(str);
        if (str != null) {

            if (str.equals("null")) {
                return ints;
            }
            if (!str.contains(",")) {
            } else {
                String[] strs = str.split(",");
                for (String s1 : strs) {
                    ints.add(Integer.parseInt(s1));
                }
            }
        }
        return ints;
    }

    /*
    点击展开后的item
     */
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        int childid = childbean.get(groupPosition).get(childPosition).getChildId();
        String text = childbean.get(groupPosition).get(childPosition).getText();
        if (contrsList.contains(childid)) {  //设置已经点击的不能重复点击  ，以后可能会添加为将数移除
            return false;
        }
        controlAdapter(childid, text);  //暂时不加入网络数据变化
        return true;
    }

    //根据点击位置的id 控制点击事件  考虑到可能出现错误点击 所以在可能出现错误点击位置设置为可以修改
    private void controlAdapter(int id, String text) {
        if (id != 19 && contrsList.contains(19)) {
            contrsList.remove(new Integer(19));
        }
        if (contrsList.contains(4) || contrsList.contains(5) || contrsList.contains(6) || contrsList.contains(7)) {
            if (id < 4) {
                return;
            }
        }
        if (contrsList.contains(8) || contrsList.contains(9) || contrsList.contains(10) || contrsList.contains(11) || contrsList.contains(12) || contrsList.contains(13)) {
            if (id < 8) {
                return;
            }
        }
        if (contrsList.contains(14) || contrsList.contains(15) || contrsList.contains(16) || contrsList.contains(17) || contrsList.contains(18)) {
            if (id < 14) {
                return;
            }
        }
        switch (id) {
            case 1:
                contrsList.add(1);
                break;
            case 2:
                //符合要求 正在进入实质审查中
                if (contrsList.contains(3)) {
                    contrsList.remove(new Integer(3));
                }
                contrsList.add(2);
                break;
            case 3:
                //不符合要求  延期补正
                if (contrsList.contains(2)) {
                    contrsList.remove(new Integer(2));
                }
                contrsList.add(3);
                break;
            case 4:
                if (contrsList.contains(3)) {
                    toast("需要点击商标符合要求");
                    return;
                }
                if (contrsList.contains(5)) {
                    contrsList.remove(new Integer(5));
                }
                if (contrsList.contains(6)) {
                    contrsList.remove(new Integer(6));
                }
                if (contrsList.contains(7)) {
                    contrsList.remove(new Integer(7));
                }
                contrsList.add(4);
                break;
            case 5:
                if (contrsList.contains(3)) {
                    toast("需要点击商标符合要求");
                    return;
                }
                if (contrsList.contains(4)) {
                    contrsList.remove(new Integer(4));
                }
                contrsList.add(5);

                break;
            case 6:
                if (contrsList.contains(3)) {
                    toast("需要点击商标符合要求");
                    return;
                }
                if (contrsList.contains(4)) {
                    contrsList.remove(new Integer(4));
                    contrsList.add(5);
                }
                if (contrsList.contains(7)) {
                    contrsList.remove(new Integer(7));
                }
                contrsList.add(6);
                break;
            case 7:
                if (contrsList.contains(3)) {
                    toast("需要点击商标符合要求");
                    return;
                }
                if (contrsList.contains(4)) {
                    contrsList.remove(new Integer(4));
                    contrsList.add(5);
                }
                if (contrsList.contains(6)) {
                    contrsList.remove(new Integer(6));
                }
                contrsList.add(7);
                break;
            case 8:
                //初步审定公告
                contrsList.add(8);
                break;
            case 9:
                //提出异议中
                contrsList.add(9);
                break;
            case 10:
                //异议理由成立
                contrsList.add(10);
                break;
            case 11:
                //异议复审中
                contrsList.add(11);
                break;
            case 12:
                //异议成立
                if (contrsList.contains(13)) {
                    contrsList.remove(new Integer(13));
                }
                contrsList.add(12);
                break;
            case 13:
                //异议不成立继续注册
                if (contrsList.contains(12)) {
                    contrsList.remove(new Integer(12));
                }
                contrsList.add(13);
                break;
            case 14:
                //核准通过
                if (contrsList.contains(15)) {
                    contrsList.remove(new Integer(15));
                }
                contrsList.add(14);
                break;
            case 15:
                //注册有异议
                if (contrsList.contains(14)) {
                    contrsList.remove(new Integer(14));
                }
                contrsList.add(15);
                break;
            case 16:
                //提出复审中
                contrsList.add(16);
                break;
            case 17:
                //复审通过申请通过 可以下证
                if (contrsList.contains(18)) {
                    contrsList.remove(new Integer(18));
                }
                contrsList.add(17);
                break;
            case 18:
                //争议 注册失败
                if (contrsList.contains(17)) {
                    contrsList.remove(new Integer(17));
                }
                contrsList.add(18);
                break;
            case 19:
                contrsList.add(19);
                break;
        }
        showDialog(text);
    }

    private void showDialog(String text) {
        builder.setTitle("确定完成了");
        builder.setMessage(text + "吗？").
                setCancelable(false).setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeStep();
                dialog.cancel();
            }
        }).setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contrsList = new ArrayList(savaList);
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setAdapter(List<Integer> ins) {
        adapter.setChildbeans(datas.setListBackData(ins));
    }

    /*
         修改服务器订单的进度
      */
    private void changeStep() {
        dialog = ProgressDialog.show(this, "", "正在更新");
        dialog.show();
        Map<String, Object> parms = new HashMap<>();
        parms.put("order_id", xiangqingdatas.getOrder_id());
        parms.put("order_acceptance_type", list2Str(contrsList));
        Xutils.Post(BaseData.CHANGESTYLE, parms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                setAdapter(contrsList);
                savaList = new ArrayList(contrsList);
                String nowMsg = datas.getTextByIndex(contrsList.get(contrsList.size() - 1));
                jindu.setText(nowMsg);
                //调用短信接口发送
                sendMsgToUser(nowMsg);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //成功之后将之前的list转换为添加新的 失败后将新的变为之前的
                toast(getString(R.string.checkthenet));
                contrsList = new ArrayList(savaList);
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

    //将list排序后转化为字符串上传服务器
    public static String list2Str(List<Integer> ins) {
        Collections.sort(ins);
        int s[] = new int[ins.size()];
        for (int i = 0; i < ins.size(); i++) {
            s[i] = ins.get(i);
        }
        String sk = array2String(s);
        return sk.substring(1, sk.length() - 1);
    }

    //将数组转化为string
    private static String array2String(int[] a) {
        if (a == null)
            return "[]";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(",");
        }
    }

    //向用户手机发送短信验证码
    private void sendMsgToUser(final String value) {
        String xvalue = "";
        try {
            xvalue = URLEncoder.encode("#code#=" + value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<String, String> parms = new HashMap<>();
        parms.put("mobile", xiangqingdatas.getWho_put_order());
        parms.put("tpl_id", "30472");
        parms.put("key", BaseData.DUANXINKEY);
        parms.put("tpl_value", xvalue);
        NetUtils.Get(BaseData.FASONGDUANXIN, parms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                SendMsgResultBean msg = gson.fromJson(result, SendMsgResultBean.class);
                if (msg.getError_code() == 0) {
                    toast("短信发送成功");
                    changJinDuOnNet(value);
                } else {
                    toast("通知用户失败:" + msg.getReason());
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

    /**
     * 返回时将传过来的对象返回给上一个activity
     * 因为返回接收订单界面会自动刷新，所以不需要做向更上一级进行
     * 数据变化
     * 修改接口之后，不需要更新上一个界面数据
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //waiter_id/71/order_num/1/nav/1  推送成功后给服务器的记录
    private void changJinDuOnNet(String nav) {
        Map<String, Object> param = new HashMap<>();
        param.put("waiter_id", su.showUid());
        param.put("order_num", xiangqingdatas.getOrder_num() + "");
        param.put("nav", nav);
        NetUtils.Post(BaseData.CHANGJINDU, param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("changejincu", result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
