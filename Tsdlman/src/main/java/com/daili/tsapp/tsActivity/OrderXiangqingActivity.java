package com.daili.tsapp.tsActivity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.DingdanJinDuBean;
import com.daili.tsapp.jsBean.netBean.ErrorBean;
import com.daili.tsapp.jsBean.netBean.FormlistDateBean;
import com.daili.tsapp.jsBean.netBean.OwnFormsBean;
import com.daili.tsapp.tsAdapter.XiangQingAdapter;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsNet.Xutils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

//展示首页订单明细中的所有订单的详情
public class OrderXiangqingActivity extends BaseActivity {
    String phone;
    PopupWindow mPopuwindow;
    FormlistDateBean.DataBean.OrderBean data;
    Gson gson = new Gson();
    ProgressDialog dialog;
    ExpandableListView mExpandableListView;
    RelativeLayout back;
    XiangQingAdapter mXiangQingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdanxiangqing);
        init();
    }

    private void init() {
        data = (FormlistDateBean.DataBean.OrderBean) getIntent().getSerializableExtra("datas");
        mExpandableListView = (ExpandableListView) findViewById(R.id.xiangq_expendlistview);
        back = (RelativeLayout) findViewById(R.id.dingdanxiangqing_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        View view = LayoutInflater.from(this).inflate(R.layout.head_xiangqing_view, null);
        TextView orderNum = (TextView) view.findViewById(R.id.xiangqing_dingdanhaovalue);
        RelativeLayout call = (RelativeLayout) view.findViewById(R.id.xiangqing_call);
        ImageView head = (ImageView) view.findViewById(R.id.xiangqing_shangbiao);
        TextView orderName = (TextView) view.findViewById(R.id.xiangqing_namevalue);
        TextView isPay = (TextView) view.findViewById(R.id.xiangqing_zizhivalue);
        TextView money = (TextView) view.findViewById(R.id.xiangqing_feiyongvalue);
        TextView userName = (TextView) view.findViewById(R.id.xiangqing_username);
        TextView orderTime = (TextView) view.findViewById(R.id.xiangqing_time);
        Button getJindu = (Button) view.findViewById(R.id.xiangqing_addjindu);
        getJindu.setVisibility(View.GONE);
        TextView leibienum = (TextView) view.findViewById(R.id.xiangqing_fuwuvalue);
        if (data != null) {
            orderNum.setText(data.getOrder_num());
            x.image().bind(head, BaseData.BASEIMG + data.getOrder_picture());
            orderName.setText(data.getOrder_name());
            isPay.setText(data.getOrder_wait_pay().equals("1") ? "已支付" : "未支付");
            money.setText(data.getOrder_price());
            userName.setText(data.getWho_put_order());
            orderTime.setText(data.getOrder_put_time());
            leibienum.setText("小类数量:" + data.getOrder_minor_term_count());
            phone = data.getWho_put_order();
        }
        if (phone != null) {
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + phone);
                    intent.setData(data);
                    if (ActivityCompat.checkSelfPermission(OrderXiangqingActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        toast(getResources().getString(R.string.allow_phone));
                        return;
                    }
                    OrderXiangqingActivity.this.startActivity(intent);
                }
            });
        }
        getJindu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findJinduOnNet();
            }
        });
        mExpandableListView.addHeaderView(view);
        OwnFormsBean.DataBean beteData = new OwnFormsBean.DataBean();
        beteData.setOrder_minor_term_nav(data.getOrder_minor_term_nav());
        mXiangQingAdapter = new XiangQingAdapter(this, beteData);
        mExpandableListView.setAdapter(mXiangQingAdapter);


    }

    //通过dialog查看大图
    private void showBydialog(String url) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.xiangqing_bigimge);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.xiangqing_bigimage);
//        x.image().bind(imageView,BaseData.BASEIMG+data.getOrder_qiye_yingyezhizhao());
        Picasso.with(this).load(BaseData.BASEIMG + url).into(imageView);
        Window win = dialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        win.setAttributes(lp);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //显示并且添加进度 跳转到进度展示界面 通过网络获取到现在的进度，并传递两个对象
    //一个是网络获取的进度数据，一个是订单数据
    private void addstep(DingdanJinDuBean bean) {
        Intent intent = new Intent(this, ControlJinDuActivity.class);
        intent.putExtra("xiangqing", data);
        intent.putExtra("jindu", bean2String(bean));
        startActivity(intent);
    }

    /*
    将订单实体类转化为字符串传递
     */
    private String bean2String(DingdanJinDuBean bean) {
        StringBuilder bu = new StringBuilder();
        if (bean == null || bean.getMsg().size() == 0) {
            return "1";
        }
        for (DingdanJinDuBean.MsgBean be : bean.getMsg()) {
            bu.append(be.getType() + ",");
        }
        return bu.substring(0, bu.length() - 1);
    }

    /*
    从网络请求订单进度信息
     */
    Callback.Cancelable cancel;

    private void findJinduOnNet() {
        dialog = ProgressDialog.show(this, "", "正在请求订单信息");
        dialog.show();
        Map<String, Object> parms = new HashMap<>();
        parms.put("order_id", data.getOrder_id());
        cancel = Xutils.Post(BaseData.JINDU, parms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.substring(0, 19).contains("Error")) {
                    ErrorBean error = gson.fromJson(result, ErrorBean.class);
                    toast(error.getMsg());
                    return;
                }
                DingdanJinDuBean bean = gson.fromJson(result, DingdanJinDuBean.class);
                addstep(bean);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                toast(getString(R.string.net_err));
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
    public void onBackPressed() {
        if (cancel != null) {
            cancel.cancel();
        }
        if (dialog != null) {
            dialog.dismiss();
        }
        super.onBackPressed();
    }
}
