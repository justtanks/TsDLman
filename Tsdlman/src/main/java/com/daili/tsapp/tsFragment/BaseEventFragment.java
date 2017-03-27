package com.daili.tsapp.tsFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daili.tsapp.tsBase.BaseFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/12/28.
 * 需要进行传递事件的fragment
 */

public class BaseEventFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
