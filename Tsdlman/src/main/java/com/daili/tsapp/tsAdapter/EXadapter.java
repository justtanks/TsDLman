//package com.daili.tsapp.tsAdapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//import com.daili.tsapp.R;
//import com.daili.tsapp.jsBean.netBean.XiangqingStepBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Administrator on 2017/2/20.
// */
//
//public class EXadapter extends BaseExpandableListAdapter {
//    Context context;
//    List<XiangqingStepBean.MsgBean> msgBeen=new ArrayList<>();  //第一级
//    List<List<XiangqingStepBean.MsgBean.TypeBelongBean>> belogbeans=new ArrayList<>();
//    LayoutInflater inflater;
//    int lowid=-1;
//
//
//    public  void setLowid(int lowid){
//        this.lowid=lowid;
//        notifyDataSetChanged();
//    }
//    public EXadapter(Context context, List<XiangqingStepBean.MsgBean> msgBeen, List<List<XiangqingStepBean.MsgBean.TypeBelongBean>> belogbeans) {
//        this.context = context;
//        this.msgBeen = msgBeen;
//        this.belogbeans = belogbeans;
//        this.inflater=LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getGroupCount() {
//        return msgBeen.size();
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return belogbeans.get(groupPosition).size();
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return msgBeen.get(groupPosition);
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return belogbeans.get(groupPosition).get(childPosition);
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        if(convertView==null){
//            convertView=inflater.inflate(R.layout.item_parent_ex,parent,false);
//        }
//        TextView tx= (TextView) convertView.findViewById(R.id.expend_par_title);
//        ImageView image= (ImageView) convertView.findViewById(R.id.expend_par_shou);
//        tx.setText(msgBeen.get(groupPosition).getType_name());
//        if(isExpanded){
//            image.setImageResource(R.mipmap.expend_yes);
//        }else {
//            image.setImageResource(R.mipmap.expend_no);
//        }
//        return convertView;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        if(convertView==null){
//          convertView=inflater.inflate(R.layout.item_child_expend,parent,false);
//        }
//        TextView te= (TextView) convertView.findViewById(R.id.expend_child_text);
//        te.setText(belogbeans.get(groupPosition).get(childPosition).getSection_name());
//        ImageView duihao= (ImageView) convertView.findViewById(R.id.expend_child_duihao);
//        int chiildId=belogbeans.get(groupPosition).get(childPosition).getId();
//        if(chiildId>lowid){
//            duihao.setVisibility(View.INVISIBLE);
//        }else {
//            duihao.setVisibility(View.VISIBLE);
//        }
//        return convertView;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//}
