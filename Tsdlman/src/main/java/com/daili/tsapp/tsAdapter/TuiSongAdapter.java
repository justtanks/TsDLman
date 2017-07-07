package com.daili.tsapp.tsAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.TuiSongUserBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 * 展示所有新注册用户的列表的listview的adapter
 */

public class TuiSongAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<TuiSongUserBean.MsgBean> datas;

    public TuiSongAdapter(Context context,List<TuiSongUserBean.MsgBean> datas){
        this.mContext=context;
        mInflater=LayoutInflater.from(context);
        this.datas=datas;

    }
    public void setDatas( List<TuiSongUserBean.MsgBean> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (datas==null)
            return 0;
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_tuisong_lv,null);
            holder.num= (TextView) convertView.findViewById(R.id.tuisong_lv_num);
            holder.time= (TextView) convertView.findViewById(R.id.tuisong_lv_time);
            holder.phone= (ImageView) convertView.findViewById(R.id.tuisong_lv_phone);
            holder.biaoji= (ImageView) convertView.findViewById(R.id.tuisong_lv_hongdian);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.num.setText(datas.get(position).getUser_telphone());
        holder.time.setText(datas.get(position).getRegister_date());
        if(datas.get(position).getNew_gays()==1){
            holder.biaoji.setVisibility(View.VISIBLE);
        }else{
            holder.biaoji.setVisibility(View.GONE);
        }
        return convertView;
    }
    class ViewHolder{
        TextView num;
        TextView time;
        ImageView phone;
        ImageView biaoji;
    }
}
