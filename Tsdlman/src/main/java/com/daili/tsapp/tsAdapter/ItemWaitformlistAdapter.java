package com.daili.tsapp.tsAdapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.daoBean.Testuser;
/*
所有订单界面中等待接单界面listview的adapter
 */
public class ItemWaitformlistAdapter extends BaseAdapter {

    private List<Testuser> objects = new ArrayList<Testuser>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemWaitformlistAdapter(Context context,List<Testuser> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_waitformlist, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    }

     class ViewHolder {

    private TextView waitformPhonenum;
    private ImageView myformHead;
    private TextView myformWaitreceiveTitle;
    private TextView myformWaitreceiveFuwufeinum;
    private TextView myformWaitreceiveGuanfei;
    private TextView waitformAllmoney;
    private TextView waitformWaitmoney;
   private  TextView dingdanhao;
    private TextView waitformFormnum;
    private ImageView waitformJiedanbt;
         private ImageView waitformcancel;

        public ViewHolder(View view) {

            waitformPhonenum = (TextView) view.findViewById(R.id.waitform_phonenum);
            myformHead = (ImageView) view.findViewById(R.id.myform_head);
            myformWaitreceiveTitle = (TextView) view.findViewById(R.id.myform_waitreceive_title);
            myformWaitreceiveFuwufeinum = (TextView) view.findViewById(R.id.myform_waitreceive_fuwufeinum);

            myformWaitreceiveGuanfei = (TextView) view.findViewById(R.id.myform_waitreceive_guanfei);
            dingdanhao= (TextView) view.findViewById(R.id.waitform_dingdannum);
            waitformAllmoney = (TextView) view.findViewById(R.id.waitform_allmoney);
            waitformWaitmoney = (TextView) view.findViewById(R.id.waitform_waitmoney);
            waitformFormnum = (TextView) view.findViewById(R.id.waitform_formnum);
            waitformJiedanbt = (ImageView) view.findViewById(R.id.waitform_jiedanbt);
            waitformcancel= (ImageView) view.findViewById(R.id.waitform_cancelform);
        }
    }
