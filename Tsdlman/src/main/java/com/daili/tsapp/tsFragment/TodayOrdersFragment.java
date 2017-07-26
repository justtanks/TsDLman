package com.daili.tsapp.tsFragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.daili.tsapp.BR;
import com.daili.tsapp.R;
import com.daili.tsapp.databinding.TodayBinding;
import com.daili.tsapp.jsBean.bindingbean.ShowOrdersTypeBean;
import com.daili.tsapp.jsBean.netBean.FormlistDateBean;
import com.daili.tsapp.tsActivity.OrderXiangqingActivity;
import com.daili.tsapp.tsActivity.XiangqingActivity;
import com.daili.tsapp.tsAdapter.simpleAdapter.ListAdapter;
import com.daili.tsapp.tsBase.BaseFragment;
import com.daili.tsapp.tsBase.impl.OnOrdersChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 */

public class TodayOrdersFragment extends BaseFragment implements AdapterView.OnItemClickListener, OnOrdersChangeListener {
    TodayBinding b;
    List<FormlistDateBean.DataBean.OrderBean> allOrderdatas = new ArrayList<>();
    ShowOrdersTypeBean datas;
    ListAdapter<FormlistDateBean.DataBean.OrderBean> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_order_today, container, false);
        b.todayLv.setOnItemClickListener(this);
        adapter = new ListAdapter<>(getActivity(), allOrderdatas, BR.allorder, R.layout.item_allorder);
        b.todayLv.setAdapter(adapter);
        return b.getRoot();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), OrderXiangqingActivity.class);
        intent.putExtra("datas", allOrderdatas.get(position));
        startActivity(intent);
    }

    @Override
    public void onOrdersChange(FormlistDateBean orders) {
        allOrderdatas = orders.getData().get(0).getToday_order();
        adapter.setDatas(allOrderdatas);
        if (allOrderdatas != null) {
            datas = new ShowOrdersTypeBean(allOrderdatas.size() + "");
        } else {
            datas = new ShowOrdersTypeBean(0 + "");
        }
        b.setDatas(datas);
    }
}
