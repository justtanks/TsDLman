package com.ts.databending.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ts.databending.R;
import com.ts.databending.bean.XiangqingStepBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */

public class EXadapter extends BaseExpandableListAdapter {
    Context context;
    List<XiangqingStepBean.MsgBean> msgBeen=new ArrayList<>();  //第一级
    List<List<XiangqingStepBean.MsgBean.TypeBelongBean>> belogbeans=new ArrayList<>();
    int selectParentItem = -1;
    int selectChildItem = -1;
    LayoutInflater inflater;
    int lowid;
    public  void setLowid(int lowid){
        this.lowid=lowid;
        notifyDataSetChanged();
    }
//    public EXadapter(Context context) {
//        this.context = context;
//        this.inflater=LayoutInflater.from(context);
//    }
//     public void setPos(int selectParentItem,int selectChildItem){
//         this.selectChildItem=selectChildItem;
//         this.selectParentItem=selectParentItem;
//         notifyDataSetChanged();
//     }

    public EXadapter(Context context, List<XiangqingStepBean.MsgBean> msgBeen, List<List<XiangqingStepBean.MsgBean.TypeBelongBean>> belogbeans) {
        this.context = context;
        this.msgBeen = msgBeen;
        this.belogbeans = belogbeans;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return msgBeen.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return belogbeans.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return msgBeen.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return belogbeans.get(groupPosition).get(childPosition);
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
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_parent_ex,parent,false);
        }
        TextView tx= (TextView) convertView.findViewById(R.id.expend_par_title);
        ImageView image= (ImageView) convertView.findViewById(R.id.expend_par_shou);
        tx.setText(msgBeen.get(groupPosition).getType_name());
        if(isExpanded){
            image.setImageResource(R.mipmap.expend_yes);
        }else {
            image.setImageResource(R.mipmap.expend_no);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null){
          convertView=inflater.inflate(R.layout.item_child_expend,parent,false);
        }
        TextView te= (TextView) convertView.findViewById(R.id.expend_child_text);
        te.setText(belogbeans.get(groupPosition).get(childPosition).getSection_name());
        ImageView duihao= (ImageView) convertView.findViewById(R.id.expend_child_duihao);
        Log.e("position",belogbeans.get(groupPosition).get(childPosition).getSection_belong_receipt()+"");
        Log.e("chilidid",belogbeans.get(groupPosition).get(childPosition).getId()+"");
        if(belogbeans.get(groupPosition).get(childPosition).getId()<lowid){
                duihao.setVisibility(View.VISIBLE);
        }else {
            duihao.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
