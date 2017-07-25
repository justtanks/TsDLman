package com.daili.tsapp.tsAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.ExpendListChildBean;
import com.daili.tsapp.jsBean.ExpendListGroupBean;
import com.daili.tsapp.jsBean.netBean.OwnFormsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7
 *  订单详情展示界面的expandlistview的adapter
 */

public class XiangQingAdapter extends BaseExpandableListAdapter {
    Context context;
   OwnFormsBean.DataBean datas;

    LayoutInflater inflater;
    public XiangQingAdapter(Context context, OwnFormsBean.DataBean datas) {
        this.context = context;
         this.datas=datas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return datas.getOrder_minor_term_nav()==null?0:datas.getOrder_minor_term_nav().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (datas==null){
            return 0;
        }
        if(datas.getOrder_minor_term_nav()==null){
            return 0;
        }
        if(datas.getOrder_minor_term_nav().get(groupPosition).getSss_detail()==null){
            return 0;
        }
        return datas.getOrder_minor_term_nav().get(groupPosition).getSss_detail().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return datas.getOrder_minor_term_nav().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return datas.getOrder_minor_term_nav().get(groupPosition).getSss_detail().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_xiangqing_dalei, parent, false);
        }
        TextView tx = (TextView) convertView.findViewById(R.id.item_xiangqing_di);
        TextView leiname= (TextView) convertView.findViewById(R.id.item_xiangqing_leibie);
        tx.setText("第"+datas.getOrder_minor_term_nav().get(groupPosition).getS_num()+"大类");
        leiname.setText(datas.getOrder_minor_term_nav().get(groupPosition).getS_name());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHoolder holder = null;
        if (convertView == null) {
            holder = new ViewHoolder();
            convertView = inflater.inflate(R.layout.item_xiangqing_xiaolei, parent, false);
            holder.name= (TextView) convertView.findViewById(R.id.item_xiangqing_leiname);
            holder.num= (TextView) convertView.findViewById(R.id.item_xiangqing_leinum);
            convertView.setTag(holder);

        } else {
            holder = (ViewHoolder) convertView.getTag();
        }
        holder.num.setText(datas.getOrder_minor_term_nav().get(groupPosition).getSss_detail().get(childPosition).getSssid()+":");
        holder.name.setText(datas.getOrder_minor_term_nav().get(groupPosition).getSss_detail().get(childPosition).getSss_name());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHoolder {
        TextView num;
        TextView name;
    }
}
