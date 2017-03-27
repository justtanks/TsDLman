package com.daili.tsapp.tsFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.FragmentMineBinding;
import com.daili.tsapp.jsBean.netBean.CardsBean;
import com.daili.tsapp.jsBean.netBean.LoginBean2;
import com.daili.tsapp.jsBean.netBean.NetError;
import com.daili.tsapp.jsBean.netBean.PersonShanchang;
import com.daili.tsapp.tsActivity.CardActivity;
import com.daili.tsapp.tsActivity.FankuiActivity;
import com.daili.tsapp.tsActivity.KeFuActivity;
import com.daili.tsapp.tsActivity.PersonalMsgActivity;
import com.daili.tsapp.tsActivity.SettingActivity;
import com.daili.tsapp.tsActivity.TabHomeActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsBase.BaseFragment;
import com.daili.tsapp.tsDB.DButils;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
   我的 界面  用户端
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    FragmentMineBinding b;
    SystemUtil su;
    ProgressDialog dialog;
    TabHomeActivity activity;

    public MineFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        b.homeMyset.setOnClickListener(this);
        b.homeMykefu.setOnClickListener(this);
        b.homeMyyijian.setOnClickListener(this);
        b.mineHead.setOnClickListener(this);
        b.homeCard.setOnClickListener(this);
        su = new SystemUtil(getActivity());
        setName();
        activity = (TabHomeActivity) getActivity();
        return b.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_myset:
                setTheSoft();
                break;
            case R.id.home_mykefu:
                toKeFu();
                break;
            case R.id.home_myyijian:
                toFankui();
                break;
            case R.id.mine_head:
                toPersonMsg();
                break;
            case R.id.home_card:
                toCards();
                break;
        }
    }

    //跳转到我的银行卡界面
    private void toCards() {
        dialog = ProgressDialog.show(activity, "", "正在获取银行卡信息");
        dialog.show();
        Map<String, Object> parm = new HashMap<>();
        parm.put("waiter_id", su.showUid());
        NetUtils.Post(BaseData.GETCARDS, parm, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                if (result.substring(0, 18).contains("Error")) {
                    NetError error = gson.fromJson(result, NetError.class);
                    Toast.makeText(getActivity(), error.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    CardsBean cards = gson.fromJson(result, CardsBean.class);
                    intent = new Intent(activity, CardActivity.class);
                    intent.putExtra("cards", cards);
                    startActivity(intent);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                dialog.dismiss();
            }
        });
    }


    //跳转到个人信息界面
    private void toPersonMsg() {
        dialog = ProgressDialog.show(activity, "", "正在获取个人信息");
        dialog.show();
        Map<String, Object> parm = new HashMap<>();
        parm.put("waiter_id", su.showUid());
        NetUtils.Post(BaseData.SHANCHANG, parm, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                PersonShanchang bean = gson.fromJson(result, PersonShanchang.class);
                ArrayList<Integer> ins = new ArrayList<Integer>();
                for (PersonShanchang.DataBean da : bean.getData()) {
                    ins.add(da.getGood_work_id());
                }
                Log.e("result", ins.toString());
                intent = new Intent(getActivity(), PersonalMsgActivity.class);
                intent.putIntegerArrayListExtra("gerenshanchang", ins);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Toast.makeText(getActivity(), getResources().getString(R.string.net_err), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                dialog.dismiss();
            }
        });
    }

    Intent intent;

    //跳转到设置界面
    private void setTheSoft() {
        intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

    //跳转到客服界面
    private void toKeFu() {
        intent = new Intent(getActivity(), KeFuActivity.class);
        startActivity(intent);

    }

    //跳到反馈界面
    private void toFankui() {
        intent = new Intent(getActivity(), FankuiActivity.class);
        startActivity(intent);
    }

    //设置头像和名字
    private void setName() {
        try {
            LoginBean2.DataBean bean = DButils.DB.findFirst(LoginBean2.DataBean.class);
            if (bean.getWaiter_pic() != null) {
                String image = bean.getWaiter_pic();
                if (image.startsWith(".")) {
                    String simg = bean.getWaiter_pic().substring(1, image.length());
                    x.image().bind(b.mineHead, BaseData.BASEURL + simg);
                } else {
                    Picasso.with(getActivity()).load(BaseData.BASEURL + "//" + image).into(b.mineHead);
                }
            }
            if (bean.getWaiter_name() != null) {
                b.mineName.setText(bean.getWaiter_name());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

}
