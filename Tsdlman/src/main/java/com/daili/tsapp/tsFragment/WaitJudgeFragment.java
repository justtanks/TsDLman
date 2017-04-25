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
import com.daili.tsapp.tsBase.impl.OnFromsChangeListener;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 * 待评价界面
 */
public class WaitJudgeFragment extends BaseFragment implements OnFromsChangeListener{
    WaitJudgeBinding b;
    List<OrdersBean.DataBean.OrderBean> allOrderdatas=new ArrayList<>();
    ListAdapter<OrdersBean.DataBean.OrderBean> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_waitjudge, container, false);
          adapter = new ListAdapter<>(getActivity(), allOrderdatas, BR.myallorders, R.layout.item_myorders);
          b.alldayLv.setAdapter(adapter);
        return b.getRoot();
    }
    @Override
    public void onFormChange(OrdersBean ordersBean) {
        allOrderdatas = ordersBean.getData().get(0).getNohave_evaluate();
        if (null != allOrderdatas && allOrderdatas.size() != 0) {
            adapter.setDatas(allOrderdatas);
        }
    }
}
