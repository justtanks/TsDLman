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
import com.daili.tsapp.jsBean.netBean.ShanchangBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7
 * 展示商标申请进度进度的expandblelistview 的adapter
 */

public class ShanChangadapter extends BaseExpandableListAdapter {
    Context context;
    List<String> groupBeen = new ArrayList<>();  //第一级
    List<List<ShanchangBean.MsgBean.SidSideBean>>  childbeans= new ArrayList<>();
    LayoutInflater inflater;

    public ShanChangadapter(Context context, List<String> groupBeen ,   List<List<ShanchangBean.MsgBean.SidSideBean>>   childbeans) {
        this.context = context;
        this.groupBeen = groupBeen;
        this.childbeans = childbeans;
        this.inflater = LayoutInflater.from(context);
    }

    public List<List<ShanchangBean.MsgBean.SidSideBean>>  getChildbeans() {
        return childbeans;
    }

    public void setChildbeans(List<List<ShanchangBean.MsgBean.SidSideBean>> childbeans) {
        this.childbeans = childbeans;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return groupBeen.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childbeans.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupBeen.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childbeans.get(groupPosition).get(childPosition);
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
            convertView = inflater.inflate(R.layout.item_parent_shanchagn, parent, false);
        }
        TextView tx = (TextView) convertView.findViewById(R.id.expend_shanchang_title);
        ImageView image = (ImageView) convertView.findViewById(R.id.expend_shanchang_shou);
        tx.setText(groupBeen.get(groupPosition));
        if (isExpanded) {
            image.setImageResource(R.mipmap.jindu_xiala);
        } else {
            image.setImageResource(R.mipmap.jindu_noxiala);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHoolder holder = null;
        if (convertView == null) {
            holder = new ViewHoolder();
            convertView = inflater.inflate(R.layout.item_child_shanchang , parent, false);
            holder.textView = (TextView) convertView.findViewById(R.id.expend_shanchang_text);
            holder.titleImage = (ImageView) convertView.findViewById(R.id.expend_shanchang_duihao);
            convertView.setTag(holder);

        } else {
            holder = (ViewHoolder) convertView.getTag();
        }
         ShanchangBean.MsgBean.SidSideBean thisItem=childbeans.get(groupPosition).get(childPosition);
           holder.textView.setText(thisItem.getSide_name());
        if(thisItem.getIsChoise()==1){
            holder.titleImage.setImageResource(R.mipmap.shanchang_yes);
            holder.textView.setTextColor(context.getResources().getColor(R.color.jindu_press));
        }else {
            holder.titleImage.setImageResource(R.mipmap.shanchang_no);
            holder.textView.setTextColor(context.getResources().getColor(R.color.gray1));
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHoolder {
        TextView textView;
        ImageView titleImage;
    }
}
