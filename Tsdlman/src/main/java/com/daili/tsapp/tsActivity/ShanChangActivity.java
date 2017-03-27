package com.daili.tsapp.tsActivity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.ActivityShanChangBinding;
import com.daili.tsapp.jsBean.netBean.PersonShanchang;
import com.daili.tsapp.jsBean.netBean.ShanchangBean;
import com.daili.tsapp.tsAdapter.ShanChangadapter;
import com.daili.tsapp.tsBase.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ShanChangActivity extends BaseActivity implements View.OnClickListener ,ExpandableListView.OnChildClickListener{
    ActivityShanChangBinding b;
    ShanchangBean shanchanglist;
    ArrayList<Integer> gerenshanchang;
    Intent intent;
    List<String> titles;
    List<List<ShanchangBean.MsgBean.SidSideBean>> items;
    ShanChangadapter adapter;
    //我需要一组值控制所有的显示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init(){
        b= DataBindingUtil.setContentView(this,R.layout.activity_shan_chang);
        b.shanchangBack.setOnClickListener(this);
        intent=getIntent();
        shanchanglist= (ShanchangBean) intent.getSerializableExtra("shanchang");
        gerenshanchang= ( ArrayList<Integer>) intent.getSerializableExtra("gerenshanchang");
        titles=getTitles(shanchanglist);
        items=getItems(shanchanglist);
        b.jinduExpendlistview.setGroupIndicator(null);
        b.jinduExpendlistview.setOnChildClickListener(this);
        adapter=new ShanChangadapter(this,titles,items);
        b.jinduExpendlistview.setAdapter(adapter);
        setAdapter(gerenshanchang);
    }

  //设置adapter的显示状态
    private void setAdapter(ArrayList<Integer> ints){
        for(List<ShanchangBean.MsgBean.SidSideBean> gs:items){
            for(ShanchangBean.MsgBean.SidSideBean s:gs){
                if(ints.contains(s.getId())){
                    s.setIsChoise(1);
                }else{
                    s.setIsChoise(0);
                }
            }
        }
        adapter.setChildbeans(items);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shanchang_back:
                backToLastActivity();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        backToLastActivity();
    }

    //将选中的信息反馈给上一个界面 并同步网络数据
      void backToLastActivity(){
          intent.putIntegerArrayListExtra("shanchang1",gerenshanchang);
          setResult(12,intent);
          finish();
      }

      //处理获取到title
      private List<String> getTitles( ShanchangBean ls){
          List<String> ms=new ArrayList<>();
          List<ShanchangBean.MsgBean> msgBeens=ls.getMsg();
          for(ShanchangBean.MsgBean m:msgBeens){
              ms.add(m.getName());
          }
          return ms;
      }
      //返回所有条目 然后通过操作这些数据 对选择和没有选中的进行区分
      private List<List<ShanchangBean.MsgBean.SidSideBean>> getItems(ShanchangBean ls){
          List<List<ShanchangBean.MsgBean.SidSideBean>>  items=new ArrayList<>();
          List<ShanchangBean.MsgBean> msgBeens=ls.getMsg();
          for(ShanchangBean.MsgBean m:msgBeens){
                 items.add(m.getSid_side());
          }
          return items;
      }
      //设置被选中的条目
      private void setChoiseItem(){

      }
      //在网络上更新选择的条目
      private void updataOnNet(){
      }
  //子项被选中的变化
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        int itemid=items.get(groupPosition).get(childPosition).getId();
        if(gerenshanchang.contains(itemid)){
            gerenshanchang.remove(new Integer(itemid));
        }else{
            gerenshanchang.add(itemid);
        }
        setAdapter(gerenshanchang);
        return true;
    }
}
