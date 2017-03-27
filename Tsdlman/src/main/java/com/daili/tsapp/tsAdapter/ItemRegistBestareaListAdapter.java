package com.daili.tsapp.tsAdapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.daoBean.RegistArea;
/*
  展示擅长领域的listview的adapter
 */
public class ItemRegistBestareaListAdapter extends BaseAdapter {
    private List<RegistArea> objects = new ArrayList<RegistArea>();
    private Context context;
    private LayoutInflater layoutInflater;

    public ItemRegistBestareaListAdapter(Context context, List<RegistArea> objects) {
        this.context = context;
        this.objects = objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public RegistArea getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_regist_bestarea_list, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((RegistArea) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }
    private void initializeViews(RegistArea object, ViewHolder holder) {
        holder.registBestareatext.setText(object.getText());
    }
    protected class ViewHolder {
        private TextView registBestareatext;

        public ViewHolder(View view) {
            registBestareatext = (TextView) view.findViewById(R.id.regist_bestareatext);
        }
    }
}
