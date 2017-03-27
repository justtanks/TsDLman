package com.daili.tsapp.tsAdapter;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.daoBean.FormEvent;
import com.daili.tsapp.jsBean.netBean.ChaseFormResult;
import com.daili.tsapp.jsBean.netBean.FormListnew;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsDB.DButils;
import com.daili.tsapp.tsNet.Xutils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/1/5.\
 * 新的订单的listview的adapter
 */

public class NewFormAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<FormListnew.DataBean> users = new ArrayList<>();
    SystemUtil su;

    public NewFormAdapter(Context context, List<FormListnew.DataBean> users) {
        this.context = context;
        this.users = users;
        inflater = LayoutInflater.from(context);
        su = new SystemUtil(context);
    }

    public NewFormAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        su = new SystemUtil(context);
    }

    public void setUsers(List<FormListnew.DataBean> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(users==null){
            return 0;
        }
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    //抢单成功弹出的成功界面
    private void showSuccessDialog(String userName, String time, String headUrl, final String phonenum) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_qiangdan_success);
        ImageView back = (ImageView) dialog.findViewById(R.id.qiangdan_closebt_success);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView name = (TextView) dialog.findViewById(R.id.qiangdan_username);
        TextView timeText = (TextView) dialog.findViewById(R.id.qiangdan_time);
        ImageView headview = (ImageView) dialog.findViewById(R.id.qiangdan_head);
        RelativeLayout phoneview = (RelativeLayout) dialog.findViewById(R.id.qiangdan_call);
        name.setText(userName);
        timeText.setText(time);
        if (headUrl != null) {
            x.image().bind(headview, headUrl);
        } else {
            headview.setImageResource(R.mipmap.head_img);
        }
        phoneview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + phonenum);
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(intent);
            }
        });


        dialog.show();
    }

    //抢单失败弹出的界面
    private void showFalseDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_qiangdan_noform);
        ImageView closeButton = (ImageView) dialog.findViewById(R.id.qiangdan_closebt_false);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    //抢单成功之后将数据存入数据库 然后通知界面让界面刷新
//    private void saveToDB(FormListnew.DataBean data){
//        try {
//           DbManager manager= x.getDb(DButils.getDaoConfig());
//            manager.save(data);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View contentview, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (contentview == null) {
            holder = new ViewHolder();
            contentview = inflater.inflate(R.layout.item_newformlist, null);
            holder.allmoney = (TextView) contentview.findViewById(R.id.newform_thirdmoney);
            holder.businessimg = (ImageView) contentview.findViewById(R.id.newform_teamimg);
            holder.businesscontent = (TextView) contentview.findViewById(R.id.newform_businesscontent);
            holder.firstkey = (TextView) contentview.findViewById(R.id.newform_firsttext);
            holder.headImg = (ImageView) contentview.findViewById(R.id.item_newform_head);
            holder.name = (TextView) contentview.findViewById(R.id.newform_username);
            holder.phone = (RelativeLayout) contentview.findViewById(R.id.item_newform_call);
            holder.qiangdanBt = (Button) contentview.findViewById(R.id.item_newform_qiangdanbt);
            holder.time = (TextView) contentview.findViewById(R.id.newform_time);
            holder.title = (TextView) contentview.findViewById(R.id.newform_title);
            holder.typeview = (TextView) contentview.findViewById(R.id.newform_usertype);
            contentview.setTag(holder);
        } else {
            holder = (ViewHolder) contentview.getTag();

        }
        holder.allmoney.setText(users.get(i).getOrder_price() + "￥");
//        holder.businessimg.setImageResource(R.mipmap.sample);//暂时使用
        if (users.get(i).getOrder_pic() != null) {
            x.image().bind(holder.businessimg, BaseData.BASEIMG + users.get(i).getOrder_pic());
        } else {
            holder.businessimg.setImageResource(R.mipmap.sample);//暂时使用
        }
        String typeText = users.get(i).getOrder_types().substring(0, 4);
        holder.firstkey.setText(typeText + "...");


        if (users.get(i).getOrder_type().equals("企业注册")) {

            if (users.get(i).getOrder_qiye_yingyezhizhao() != null) {
                x.image().bind(holder.headImg, BaseData.BASEIMG + users.get(i).getOrder_qiye_yingyezhizhao());
                holder.headurl = users.get(i).getOrder_qiye_yingyezhizhao();
            } else {
                holder.headImg.setImageResource(R.mipmap.head_img);
            }
            holder.phonenum = users.get(i).getOrder_qiye_phone();


        } else if (users.get(i).getOrder_type().equals("个人注册")) {
            if (users.get(i).getOrder_personal_id_card_pic() != null) {
                x.image().bind(holder.headImg, BaseData.BASEIMG + users.get(i).getOrder_personal_id_card_pic());
                holder.headurl = users.get(i).getOrder_personal_id_card_pic();
            } else {
                holder.headImg.setImageResource(R.mipmap.head_img);
            }
            holder.phonenum = users.get(i).getOrder_personal_tel();
        }

        holder.name.setText(users.get(i).getWho_put_order());
        holder.time.setText(users.get(i).getOrder_time());
        holder.title.setText("状态:等待接单..");
        holder.typeview.setText("(" + users.get(i).getOrder_type() + ")");
        holder.businesscontent.setText(users.get(i).getShangbiao_name());
        final View finalContentview = contentview;
        holder.qiangdanBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //抢单之后操作
                MobclickAgent.onEvent(context,"Qiangdan");
                Map<String, String> params = new HashMap<String, String>();
                params.put("order_id", users.get(i).getOrder_id());
                params.put("waiter_id", su.showUid() + "");
                Xutils.Get( BaseData.QIANGDAN, params, new Callback.CacheCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        ChaseFormResult rel = gson.fromJson(result, ChaseFormResult.class);
                        if (rel.getFlag().equals("Success")) {
                            showSuccessDialog(users.get(i).getWho_put_order(), users.get(i).getOrder_time(), holder.headurl, holder.phonenum);
                            EventBus.getDefault().post(new FormEvent());
                        } else {
                            showFalseDialog();
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(context, context.getResources().getText(R.string.net_err), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }

                    @Override
                    public boolean onCache(String result) {
                        return false;
                    }
                });


            }
        });
        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打电话
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + holder.phonenum);
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context,"请设置应用允许拨打电话权限",Toast.LENGTH_SHORT);
                    return;
                }
                context.startActivity(intent);
            }
        });
        return contentview;
    }

    class ViewHolder {
        ImageView headImg;
        TextView name;
        TextView time;
        RelativeLayout phone;
        TextView title;
        ImageView businessimg;
        TextView firstkey;
        TextView allmoney;
        Button qiangdanBt;
        TextView businesscontent;
        String phonenum;
        TextView typeview;
        String headurl;
    }

}
