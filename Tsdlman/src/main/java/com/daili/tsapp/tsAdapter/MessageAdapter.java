package com.daili.tsapp.tsAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.PingLunBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 * 评论界面的listview的adapter
 */
public class MessageAdapter  extends BaseAdapter {
    private List<PingLunBean.DataBean> datas = new ArrayList();
    private Context context;
    private LayoutInflater layoutInflater;

    public MessageAdapter(Context context, List<PingLunBean.DataBean> datas) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    public void setDatas(List<PingLunBean.DataBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (datas==null)
            return 0;
        return datas.size();
    }

    @Override
    public PingLunBean.DataBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_pinglun_lv, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((PingLunBean.DataBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(PingLunBean.DataBean object, ViewHolder holder) {
         holder.itemMsgHead.setImageResource(R.mipmap.head_img);
         holder.itemMsgContent.setText("      "+object.getEvaluate_nav());
         holder.itemMsgTitle.setText(object.getUser_phone());
    }

    protected class ViewHolder {
        private ImageView itemMsgHead;
        private TextView itemMsgTitle;
        private TextView itemMsgContent;

        public ViewHolder(View view) {
            itemMsgHead = (ImageView) view.findViewById(R.id.item_msg_head);
            itemMsgTitle = (TextView) view.findViewById(R.id.item_msg_title);
            itemMsgContent = (TextView) view.findViewById(R.id.item_msg_content);
        }
    }
}
