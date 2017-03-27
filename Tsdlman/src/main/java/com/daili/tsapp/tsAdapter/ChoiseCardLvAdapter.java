package com.daili.tsapp.tsAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.CardsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 * 选择银行卡进行提现的adapter
 * 管理所有的imageview 然后设置image
 */

public class ChoiseCardLvAdapter extends BaseAdapter {
    private Context context;
    LayoutInflater inflater;
    List<CardsBean.DataBean> datas= new ArrayList<>();
    int pos=-1;

    public ChoiseCardLvAdapter(Context context, List<CardsBean.DataBean> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);

    }


    public void  setPos(int pos){
        this.pos=pos;
        notifyDataSetChanged();
    }

    public void setDatas(List<CardsBean.DataBean> datas) {
        this.datas = datas;
    }


    @Override
    public int getCount() {
        return 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_choisecard_allcard,parent,false);
            holder.bankName= (TextView) convertView.findViewById(R.id.item_choisecad_name);
            holder.bankGuid= (TextView) convertView.findViewById(R.id.item_choisecar_linetext);
            holder.duihao= (ImageView) convertView.findViewById(R.id.item_choisecard_duihao);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();

        }
        String bankname=datas.get(position).getBrank_name();
        String cardnum=datas.get(position).getCard_num();
        holder.bankName.setText(bankname+"("+cardnum.substring(cardnum.length()-4,cardnum.length())+")");
        holder.bankGuid.setText("提现到"+bankname+"收取1%手续费");
        if(position==pos){
            holder.duihao.setVisibility(View.VISIBLE);
        }else {
            holder.duihao.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView bankName;
        TextView bankGuid;
        ImageView duihao;
    }
}
