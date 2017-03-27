package com.ts.databending.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ts.databending.R;
import com.ts.databending.bean.ReccycleBean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 * recycleview的adapter 强制实现    recycleview的adapter 普通的recycleview
 * 没有使用databinding
 */

public class NormolRecyclerAdapter extends RecyclerView.Adapter<NormolRecyclerAdapter.MyViewHolder> {
    Context context;
    List<ReccycleBean> strs;
    LayoutInflater inflater;
    public NormolRecyclerAdapter(Context context, List<ReccycleBean> strs) {
          this.context=context;
           this.strs=strs;
        this.inflater=LayoutInflater.from(context);
        Log.e("test",strs.toString());
    }

    //添加时启动动画
    public void addData(int position) {
//        strs.add(position, "Insert One");
        notifyItemInserted(position);
    }
//启动移除动画
    public void removeData(int position) {
        strs.remove(position);
        notifyItemRemoved(position);
    }
    //设置点击事件和长按事件接口
    public interface  OnItemClickLitener{
        public  void onItemClick(View view,int position);
        public  void onItemLongClick(View view,int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=    new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.normol_reccyleview_item,parent,false)) ;
       new MyViewHolder(inflater.inflate(R.layout.item_recycleview,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
          holder.textView2.setText(strs.get(position).getText2());
         Picasso.with(context).load(strs.get(position).getText1()).into(holder.imageView);
        /*
        添加点击监听和长按监听
         */
        if(mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView,pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }
// 提升自己工作技能，能够更快的做好事情，有更多空余时间
    //  五年：
    @Override
    public int getItemCount() {
        return strs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
          TextView textView2;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.normal_image);
            this.textView2= (TextView) itemView.findViewById(R.id.normal_text);
        }
    }
}
