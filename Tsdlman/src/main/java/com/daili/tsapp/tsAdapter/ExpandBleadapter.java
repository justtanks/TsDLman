package com.daili.tsapp.tsAdapter;

import android.content.Context;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.ExpendListChildBean;
import com.daili.tsapp.jsBean.ExpendListGroupBean;
import com.daili.tsapp.jsBean.netBean.XiangqingStepBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7
 * 展示商标申请进度进度的expandblelistview 的adapter
 */

public class ExpandBleadapter extends BaseExpandableListAdapter {
    Context context;
    List<ExpendListGroupBean> groupBeen = new ArrayList<>();  //第一级
    List<List<ExpendListChildBean>> childbeans = new ArrayList<>();
    LayoutInflater inflater;

    public ExpandBleadapter(Context context, List<ExpendListGroupBean> groupBeen, List<List<ExpendListChildBean>> childbeans) {
        this.context = context;
        this.groupBeen = groupBeen;
        this.childbeans = childbeans;
        this.inflater = LayoutInflater.from(context);
    }

    public List<List<ExpendListChildBean>> getChildbeans() {
        return childbeans;
    }

    public void setChildbeans(List<List<ExpendListChildBean>> childbeans) {
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
            convertView = inflater.inflate(R.layout.item_parent_ex, parent, false);
        }
        TextView tx = (TextView) convertView.findViewById(R.id.expend_par_title);
        ImageView image = (ImageView) convertView.findViewById(R.id.expend_par_shou);
        tx.setText(groupBeen.get(groupPosition).getText());
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
            convertView = inflater.inflate(R.layout.item_child_expend, parent, false);
            holder.textView = (TextView) convertView.findViewById(R.id.expend_child_text);
            holder.titleImage = (ImageView) convertView.findViewById(R.id.expend_child_duihao);
            holder.topLine = (ImageView) convertView.findViewById(R.id.jindu_topline);
            holder.underInvisible = (ImageView) convertView.findViewById(R.id.jindu_undervisibleline);
            holder.underLine = (ImageView) convertView.findViewById(R.id.jindu_underline);
            convertView.setTag(holder);

        } else {
            holder = (ViewHoolder) convertView.getTag();
        }
        ExpendListChildBean thisItem=childbeans.get(groupPosition).get(childPosition);
        holder.textView.setText(thisItem.getText());

        if (childPosition == 0) {
            holder.topLine.setVisibility(View.VISIBLE);
        } else {
            holder.topLine.setVisibility(View.GONE);
        }
        if(thisItem.ispressed()){
            holder.titleImage.setImageResource(R.mipmap.jindu_jinxing);
            holder.textView.setTextColor(context.getResources().getColor(R.color.jindu_press));
        }else {
            holder.titleImage.setImageResource(R.mipmap.jindu_nojinxing);
            holder.textView.setTextColor(context.getResources().getColor(R.color.gray1));
        }
        if (isLastChild) {
            holder.underLine.setVisibility(View.GONE);
        }else {
            holder.underLine.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHoolder {
        TextView textView;
        ImageView topLine;
        ImageView underLine;
        ImageView underInvisible;
        ImageView titleImage;
    }
}
