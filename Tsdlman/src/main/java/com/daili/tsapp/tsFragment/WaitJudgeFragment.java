package com.daili.tsapp.tsFragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daili.tsapp.BR;
import com.daili.tsapp.R;
import com.daili.tsapp.databinding.WaitJudgeBinding;
import com.daili.tsapp.jsBean.netBean.FormlistDateBean;
import com.daili.tsapp.jsBean.netBean.OrdersBean;
import com.daili.tsapp.tsAdapter.simpleAdapter.ListAdapter;
import com.daili.tsapp.tsBase.BaseFragment;

import org.xutils.view.annotation.ContentView;

import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 * 待评价界面
 */
public class WaitJudgeFragment extends BaseFragment {
    WaitJudgeBinding b;
    List<OrdersBean.DataBean.OrderBean> allOrderdatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_waitjudge, container, false);
        OrdersBean bean = (OrdersBean) getActivity().getIntent().getSerializableExtra("allorders");
        allOrderdatas = bean.getData().get(0).getNohave_evaluate();
        if (null != allOrderdatas && allOrderdatas.size() != 0) {
            ListAdapter<OrdersBean.DataBean.OrderBean> adapter = new ListAdapter<>(getActivity(), allOrderdatas, BR.myallorders, R.layout.item_myorders);
            b.alldayLv.setAdapter(adapter);
        }
        return b.getRoot();
    }
}
