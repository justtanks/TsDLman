package com.ts.databending.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ts.databending.BR;
import com.ts.databending.R;
import com.ts.databending.activity.Myclick;
import com.ts.databending.bean.ReccycleBean;
import com.ts.databending.databinding.MyRecycle;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 * 通过databinding绑定recycleview的adapter、 但是处理点击事件就不知道在哪里添加了
 * 包括了添加图片操作，不过没有展示出来
 */

public class MyrecycleBinderAdapter extends RecyclerView.Adapter<MyrecycleBinderAdapter.MyViewhOLDER> {
    private List<ReccycleBean> users;
    public MyrecycleBinderAdapter(List<ReccycleBean> user) {
        this.users=user;
    }

    @Override
    public MyViewhOLDER onCreateViewHolder(ViewGroup parent, int viewType) {
        MyRecycle bling= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recycleview,parent,false);
        bling.setEvent(new Myclick());//通过这种方式设置点击事件，点击位置正确
        MyViewhOLDER myViewhOLDER=new MyViewhOLDER(bling.getRoot());
        myViewhOLDER.setBinding(bling);
        return myViewhOLDER;
    }

    @Override
    public void onBindViewHolder(MyViewhOLDER holder, int position) {
        holder.getBinding().setVariable(BR.Loser, users.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    class MyViewhOLDER extends RecyclerView.ViewHolder{
        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
        private ViewDataBinding binding;

        public MyViewhOLDER(View itemView) {
            super(itemView);

        }
    }
    class TouchYou{
        public void touch(){

        }
    }
}
