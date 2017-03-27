package com.ts.databending.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ts.databending.bean.FormListnew;

import java.util.IdentityHashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/1/14.
 */

public class MyAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int layoutId;
    private int variableId;
    private List<FormListnew.DataBean> list;
    private Context context;
    public MyAdapter(Context context,int layoutId, List<FormListnew.DataBean> list, int resId){
          inflater=LayoutInflater.from(context);
        this.list=list;
        this.variableId= resId;
        this.layoutId=layoutId;
        this.context=context;


    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewDataBinding dataBinding;
        if (convertView == null) {
            dataBinding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        }else{
            dataBinding = DataBindingUtil.getBinding(convertView);
        }
        dataBinding.setVariable(variableId, list.get(position));
        return dataBinding.getRoot();
    }

}
