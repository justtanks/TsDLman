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
import com.daili.tsapp.databinding.AlldayBinding;

import com.daili.tsapp.jsBean.netBean.FormlistDateBean;
import com.daili.tsapp.tsActivity.XiangqingActivity;
import com.daili.tsapp.tsAdapter.simpleAdapter.ListAdapter;
import com.daili.tsapp.tsBase.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 * 其中的adapter  缺少 item 的数据 点击之后订单详情数据不同
 */

public class AllOrdersFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    AlldayBinding b;
    List<FormlistDateBean.DataBean.OrderBean> allOrderdatas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_orders_all, container, false);
        FormlistDateBean bean = (FormlistDateBean) getActivity().getIntent().getSerializableExtra("forms");
        allOrderdatas= bean.getData().get(0).getAll_order();
        if (null != allOrderdatas && allOrderdatas.size() != 0) {
            ListAdapter<FormlistDateBean.DataBean.OrderBean> adapter = new ListAdapter<>(getActivity(), allOrderdatas, BR.allorder, R.layout.item_allorder);
            b.alldayLv.setAdapter(adapter);
        }
        return b.getRoot();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(), XiangqingActivity.class);
        intent.putExtra("id",1);
        intent.putExtra("datas",allOrderdatas.get(position));
        startActivity(intent);
    }
}
