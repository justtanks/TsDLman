package com.daili.tsapp.tsActivity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
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
import java.util.List;
import java.util.Map;

//订单详情界面 查询数据库并将数据展示到界面 或者从网络获取到数据
//修改通过expendlistview 进行展示所有小项信息
public class XiangqingActivity extends BaseActivity {
    //    private RelativeLayout dingdanxiangqingBack;
//    private TextView xiangqingDingdanhaovalue;
//    private TextView xiangqingUsername;
//    private TextView xiangqingTime;
//    private RelativeLayout itemNewformCall;
//    private ImageView xiangqingHead;
//    private ImageView xiangqingShangbiao;
//    private TextView xiangqingNamevalue;
//    private TextView xiangqingZizhivalue;
//    private TextView xiangqingFeiyongvalue;
//    private TextView xiangqingFuwuvalue;
//    private ImageView xiangqingZhizhaopic;
//    private Button addStep;
    private String phone;
    //    private ImageView shenfenimage;
//    private ImageView zhizhaoimg;
//    private ImageView hetongimg;
//    private ImageView weituoImag;
//    private Button fzshenfen, fzhetong, fzzhizhao, fzweituo;
    PopupWindow mPopuwindow;
    OwnFormsBean.DataBean data;
    Gson gson = new Gson();
    private ProgressDialog dialog;
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
        data = (OwnFormsBean.DataBean) getIntent().getSerializableExtra("orderobject");
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
        TextView leibienum = (TextView) view.findViewById(R.id.xiangqing_fuwuvalue);
        if (data != null) {
            orderNum.setText(data.getOrder_num());
            x.image().bind(head, BaseData.BASEIMG + data.getOrder_picture());
            orderName.setText(data.getOrder_name());
            isPay.setText(data.getOrder_wait_pay() == 1 ? "已支付" : "未支付");
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
                    if (ActivityCompat.checkSelfPermission(XiangqingActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        toast(getResources().getString(R.string.allow_phone));
                        return;
                    }
                    XiangqingActivity.this.startActivity(intent);
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
        mXiangQingAdapter = new XiangQingAdapter(this, data);
        mExpandableListView.setAdapter(mXiangQingAdapter);


    }


//    private void init() {
//        data= (OwnFormsBean.DataBean) getIntent().getSerializableExtra("orderobject");
//        mExpandableListView= (ExpandableListView) findViewById(R.id.xiangqing_expendlistview);
//        dingdanxiangqingBack = (RelativeLayout) findViewById(R.id.dingdanxiangqing_back);
//        xiangqingDingdanhaovalue = (TextView) findViewById(R.id.xiangqing_dingdanhaovalue);
//        xiangqingUsername = (TextView) findViewById(R.id.xiangqing_username);
//        xiangqingTime = (TextView) findViewById(R.id.xiangqing_time);
//        itemNewformCall = (RelativeLayout) findViewById(R.id.xiangqing_call);
//        xiangqingHead = (ImageView) findViewById(R.id.xiangqing_head);
//        xiangqingShangbiao = (ImageView) findViewById(R.id.xiangqing_shangbiao);
//        xiangqingNamevalue = (TextView) findViewById(R.id.xiangqing_namevalue);
//        xiangqingZizhivalue = (TextView) findViewById(R.id.xiangqing_zizhivalue);
//        xiangqingFeiyongvalue = (TextView) findViewById(R.id.xiangqing_feiyongvalue);
////        xiangqingZhizhaopic = (ImageView) findViewById(R.id.xiangqing_zhizhaopic);
//        xiangqingFuwuvalue = (TextView) findViewById(R.id.xiangqing_fuwuvalue);
//        addStep = (Button) findViewById(R.id.xiangqing_addjindu);

//        fzhetong = (Button) this.findViewById(R.id.xiangqing_bt_hetong);
//        fzshenfen = (Button) this.findViewById(R.id.xiangqing_bt_shenfen);
//        fzweituo = (Button) this.findViewById(R.id.xiangqing_bt_weituo);
//        fzzhizhao = (Button) this.findViewById(R.id.xiangqing_bt_zhizhao);
//        shenfenimage = (ImageView) this.findViewById(R.id.xiangqing_shenfenimg);
//        zhizhaoimg = (ImageView) this.findViewById(R.id.xiangqing_zhizhaoimg);
//        hetongimg = (ImageView) this.findViewById(R.id.xiangqing_hetongimg);
//        weituoImag = (ImageView) this.findViewById(R.id.xiangqing_weituoimg);

//        shenfenimage.setOnClickListener(this);
//        zhizhaoimg.setOnClickListener(this);
//        hetongimg.setOnClickListener(this);
//        weituoImag.setOnClickListener(this);
//
//        fzzhizhao.setOnClickListener(this);
//        fzweituo.setOnClickListener(this);
//        fzshenfen.setOnClickListener(this);
//        fzhetong.setOnClickListener(this);

//        addStep.setOnClickListener(this);
//        itemNewformCall.setOnClickListener(this);
//        dingdanxiangqingBack.setOnClickListener(this);
//        xiangqingZhizhaopic.setOnClickListener(this);
//        setdata(data);
//    }

//    private void setdata(OwnFormsBean.DataBean data) {
//        if (data != null) {
//            xiangqingDingdanhaovalue.setText(data.getOrder_num());
//            xiangqingUsername.setText(data.getWho_put_order());
//            xiangqingTime.setText(data.getOrder_put_time());
//            xiangqingShangbiao
//            x.image().bind(xiangqingShangbiao, BaseData.BASEIMG + data.getOrder_picture());
//            if (data.getOrder_type().equals("企业注册")) {
//                x.image().bind(xiangqingHead, BaseData.BASEIMG + data.getOrder_qiye_yingyezhizhao());
//                x.image().bind(xiangqingZhizhaopic, BaseData.BASEIMG + data.getOrder_qiye_yingyezhizhao());
//                phone = data.getWho_put_order();
//            } else {
//                x.image().bind(xiangqingHead, BaseData.BASEIMG + data.getOrder_personal_id_card_pic());
//                x.image().bind(xiangqingZhizhaopic, BaseData.BASEIMG + data.getOrder_personal_getizhizhao());
//                phone = data.getWho_put_order();
//            }
//            xiangqingNamevalue.setText(data.getOrder_name());
//            if(data.getOrder_wait_pay()==1){
//                xiangqingZizhivalue.setText("已经支付");
//            }else{
//                xiangqingZizhivalue.setText("尚未支付");
//            }
//
//            xiangqingFeiyongvalue.setText(data.getOrder_price());
//            String datatypes = data.getOrder_types();
//            if (datatypes != null) {
//                String tex = datatypes.replaceAll("\\s*", "");
//                String text = null;
//                text = tex.replaceAll("第", "    " + "第");
//                xiangqingFuwuvalue.setText(text);
//            }
    //展示所有的大项和小项

////            setImage();
//        }
//
//    }

    //设置合同等图片 如果是pdf 直接展示一个pdf的图片 这个地方先等等
//    private void setImage() {
//        String idcardurl = data.getOrder_personal_id_card_pic();
//        String zhizhaourl = data.getOrder_personal_getizhizhao() == null ? data.getOrder_qiye_yingyezhizhao() : data.getOrder_personal_getizhizhao();
//        String herongurl = data.getOrder_hetong();
//        String weituourl = data.getOrder_weituoshu();
//        configImage(shenfenimage, idcardurl);
//        configImage(zhizhaoimg, zhizhaourl);
//        configImage(hetongimg, herongurl);
//        configImage(weituoImag, weituourl);
//    }

    //判断是否是url或者是否存在
//    private void configImage(ImageView image, String url) {
//        if (url != null) {
//            if (isPdf(url)) {
//
//            } else {
//                x.image().bind(image, BaseData.BASEURL + url);
//            }
//        } else {
//            shenfenimage.setVisibility(View.INVISIBLE);
//        }
//    }

//    private boolean isPdf(String url) {
//        if (url.substring(url.length() - 3, url.length()).equalsIgnoreCase("pdf")) {
//            return true;
//        }
//        return false;
//    }

//    private FormListnew.DataBean getData(String orderid) {
//        try {
//            DbManager manager = x.getDb(DButils.getDaoConfig());
//            FormListnew.DataBean data = manager.selector(FormListnew.DataBean.class).where("order_id", "=", orderid).findFirst();
//            return data;
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    //通过popuwindow 查看大图  暂时没有使用
//    private void showBigImage() {
//        View popupView = getLayoutInflater().inflate(R.layout.xiangqing_bigimge, null);
//        ImageView bigimage = (ImageView) popupView.findViewById(R.id.xiangqing_bigimage);
//        x.image().bind(bigimage, BaseData.BASEIMG + data.getOrder_qiye_yingyezhizhao());
//        mPopuwindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
//        mPopuwindow.setTouchable(true);
//        mPopuwindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopuwindow.setOutsideTouchable(true);
//        bigimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPopuwindow.dismiss();
//            }
//        });
//        mPopuwindow.showAtLocation(dingdanxiangqingBack, Gravity.CENTER, 0, 0);
//    }

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

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.dingdanxiangqing_back:
//                onBackPressed();
//                break;
//            case R.id.xiangqing_call:
//                Intent intent = new Intent(Intent.ACTION_CALL);
//                Uri data = Uri.parse("tel:" + phone);
//                intent.setData(data);
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    toast(getResources().getString(R.string.allow_phone));
//                    return;
//                }
//                this.startActivity(intent);
//                break;
//            case R.id.xiangqing_zhizhaopic:
//                break;
//            case R.id.xiangqing_addjindu:
//                findJinduOnNet();
//                break;
//            case R.id.xiangqing_shenfenimg:
////                showweituo(this.data.getOrder_personal_id_card_pic());
//                break;
//            case R.id.xiangqing_hetongimg:
////                showweituo(this.data.getOrder_hetong());
//                break;
//            case R.id.xiangqing_zhizhaoimg:
////                showweituo(this.data.getOrder_personal_getizhizhao() == null ? this.data.getOrder_qiye_yingyezhizhao() : this.data.getOrder_personal_getizhizhao());
//                break;
//            case R.id.xiangqing_weituoimg:
////                showweituo(this.data.getOrder_weituoshu());
//                break;
//            case R.id.xiangqing_bt_hetong:
//                setHetong();
//                break;
//            case R.id.xiangqing_bt_shenfen:
//                setShenfen();
//                break;
//            case R.id.xiangqing_bt_weituo:
//                setWeituo();
//                break;
//            case R.id.xiangqing_bt_zhizhao:
//                setZhizhao();
//                break;
//        }
//
//    }

//    private void setZhizhao() {
////       setToBoard(data.getOrder_personal_getizhizhao() == null ? BaseData.WEBSITE + data.getOrder_qiye_yingyezhizhao() : BaseData.WEBSITE + data.getOrder_personal_getizhizhao());
//    }
//
//    private void setWeituo() {
////        setToBoard(BaseData.WEBSITE + data.getOrder_weituoshu());
//    }
//
//    private void setShenfen() {
////        setToBoard(BaseData.WEBSITE + data.getOrder_personal_id_card_pic());
//    }
//
//    private void setHetong() {
////        setToBoard(BaseData.WEBSITE + data.getOrder_hetong());
//    }

//    //复制到粘贴板
//    private void setToBoard(String text) {
////        ClipboardManager cm = (ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);
////        // 将文本内容放到系统剪贴板里。
////        cm.setText(text);
////        Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
//        if("http://www.qichengcheng.cnnull".equals(text)){
//            toast("没有图片可以传递");
//            return;
//        }
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
//        sendIntent.setType("text/plain");
//        startActivity(sendIntent);
//    }

//    //展示委托书
//    private void showweituo(String url) {
//        if (url != null) {
//            if (!isPdf(url)) {
//                showBydialog(url);
//            } else {
//                Intent intent = new Intent(this, ShowPdfActivity.class);
//                intent.putExtra("pdf", BaseData.BASEURL + url);
//                startActivity(intent);
//            }
//        } else {
//        }
//    }

//    private void showzhizhao() {
//    }
//
//    private void showhetong() {
//    }
//
//    private void showshenfen() {
//    }

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
        parms.put("order_id", data.getId());
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
