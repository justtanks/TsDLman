package com.daili.tsapp.tsAdapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.OwnFormsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 * 已经抢到的订单界面的listview的adapter
 */
public class HadFormAdapter extends BaseAdapter {
    private Context context;
    private List<OwnFormsBean.DataBean> mlist = new ArrayList<OwnFormsBean.DataBean>();
    private LayoutInflater inflater;

    public HadFormAdapter(List<OwnFormsBean.DataBean> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<OwnFormsBean.DataBean> mlist) {
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        if (mlist == null) {
            return 0;
        }
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (null == view) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_hadformlist, null);
            holder.title = (TextView) view.findViewById(R.id.hadform_title);
            holder.headImage = (ImageView) view.findViewById(R.id.item_hadform_head);
            holder.ispay = (TextView) view.findViewById(R.id.hadform_paystyle);
            holder.money = (TextView) view.findViewById(R.id.hadform_paymoney);
            holder.phone = (RelativeLayout) view.findViewById(R.id.item_hadform_call);
            holder.name = (TextView) view.findViewById(R.id.hadform_username);
            holder.time = (TextView) view.findViewById(R.id.hadform_time);
            holder.zhiding = (TextView) view.findViewById(R.id.hadform_zhiding);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(mlist.get(i).getWho_put_order());//
        holder.time.setText(mlist.get(i).getOrder_put_time() + "");
        holder.money.setText(mlist.get(i).getOrder_price() + "元");

        //支付状态修改  网络修改和数据库修改
        if (mlist.size() != 0) {
            if (mlist.get(i).getOrder_wait_pay() == 1) {
                holder.ispay.setText("已支付");
                holder.ispay.setTextColor(context.getResources().getColor(R.color.item_hadformtx2));
                holder.zhiding.setVisibility(View.INVISIBLE);
            } else {
                holder.ispay.setText("未支付");
                holder.ispay.setTextColor(context.getResources().getColor(R.color.color_ff0000));
                holder.zhiding.setVisibility(View.VISIBLE);
            }

//            if(mlist.get(i).getOrder_type().equals("企业注册")){
//
//                if(mlist.get(i).getOrder_qiye_yingyezhizhao()!=null){
//                    x.image().bind(holder.headImage, BaseData.BASEIMG+mlist.get(i).getOrder_qiye_yingyezhizhao());
//                }
//                holder.phonenum=mlist.get(i).getWho_put_order();
//
//            }else if(mlist.get(i).getOrder_type().equals("个人注册")){
//                if(mlist.get(i).getOrder_personal_id_card_pic()!=null){
//                    x.image().bind(holder.headImage, BaseData.BASEIMG+mlist.get(i).getOrder_personal_id_card_pic());
//                }
//                holder.phonenum=mlist.get(i).getWho_put_order();
//            }
//            if (mlist.get(i).getOrder_put_form() == 1) {
//                holder.title.setText("网站下单");
//            } else if (mlist.get(i).getOrder_put_form() == 2) {
//                holder.title.setText("微信下单");
//            } else if (mlist.get(i).getOrder_put_form() == 3) {
//                holder.title.setText("APP下单");
//            }
            holder.title.setText(mlist.get(i).getOrder_name());
            holder.phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //打电话
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + holder.phonenum);
                    intent.setData(data);
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(context, "请设置应用允许拨打电话权限", Toast.LENGTH_SHORT);
                        return;
                    }
                    context.startActivity(intent);
                }
            });
        }
        return view;
    }

    class ViewHolder {
        TextView title;
        TextView ispay;
        ImageView headImage;
        RelativeLayout phone;
        TextView name;
        TextView time;
        TextView money;
        String phonenum;
        TextView zhiding;
    }
}
