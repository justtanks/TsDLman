package com.ts.databending.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ts.databending.R;
import com.ts.databending.activity.Myclick;
import com.ts.databending.bean.ReccycleBean;
import com.ts.databending.databinding.Ficker;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/1/19.
 * databinding 使用的listview的adapter   修改下就可以作为一般的adapter进行使用。不需要重新写赋值事件
 */

public class ListViewAdapter extends BaseAdapter {
   Context context;
    List<ReccycleBean> beans;
    int BRid;
    private LayoutInflater inflater;
   public ListViewAdapter( List<ReccycleBean> beans,int BRid,Context context){
  this.context=context;
       this.inflater=LayoutInflater.from(context);
       this.beans=beans;
       this.BRid=BRid;

   }
    @Override
    public int getCount() {
        return beans==null?0:beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ficker ficker;
        if(convertView==null){
            ficker= DataBindingUtil.inflate(inflater, R.layout.item_listview_testbinding,parent,false);
        }else {
            ficker=DataBindingUtil.getBinding(convertView);
        }
        ficker.setEvent(new Myclick());
        ficker.setVariable(BRid,beans.get(position));
        return ficker.getRoot();
    }
}
