package com.daili.tsapp.tsFragment;

import android.app.ProgressDialog;
import android.bluetooth.le.ScanSettings;
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
import com.daili.tsapp.jsBean.TuiSongBusBean;
import com.daili.tsapp.jsBean.netBean.CardsBean;
import com.daili.tsapp.jsBean.netBean.FormlistDateBean;
import com.daili.tsapp.jsBean.netBean.LoginBean2;
import com.daili.tsapp.jsBean.netBean.NetError;
import com.daili.tsapp.jsBean.netBean.OrdersBean;
import com.daili.tsapp.jsBean.netBean.PersonShanchang;
import com.daili.tsapp.tsActivity.CardActivity;
import com.daili.tsapp.tsActivity.FankuiActivity;
import com.daili.tsapp.tsActivity.KeFuActivity;
import com.daili.tsapp.tsActivity.MyFormActivity;
import com.daili.tsapp.tsActivity.NewOrderActivity;
import com.daili.tsapp.tsActivity.PersonalMsgActivity;
import com.daili.tsapp.tsActivity.SettingActivity;
import com.daili.tsapp.tsActivity.ShowPersonCardActivity;
import com.daili.tsapp.tsActivity.TabHomeActivity;
import com.daili.tsapp.tsActivity.TuiSongActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsBase.BaseFragment;
import com.daili.tsapp.tsDB.DButils;
import com.daili.tsapp.utils.NetUtils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
   我的界面  显示代理人信息
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    FragmentMineBinding b;
    SystemUtil su;
    ProgressDialog dialog;
    TabHomeActivity activity;
    Gson gson = new Gson();
    LoginBean2.DataBean bean;

    //这个地方的东西使用eventbus实现更好一点
    public MineFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        b.homeMyset.setOnClickListener(this);
        b.homeMykefu.setOnClickListener(this);
        b.homeMyyijian.setOnClickListener(this);
        b.mineHead.setOnClickListener(this);
        b.homeCard.setOnClickListener(this);
        b.homeMyform.setOnClickListener(this);
        b.mineYijiedan.setOnClickListener(this);
        b.mineDaipingjia.setOnClickListener(this);
        b.mineYipingjia.setOnClickListener(this);
        b.mineShoucard.setOnClickListener(this);
        b.homeTuisong.setOnClickListener(this);
        su = new SystemUtil(getActivity());
        activity = (TabHomeActivity) getActivity();

        if(su.showHaveUser()==1){
            b.homeHongdian.setVisibility(View.VISIBLE);

        }else{
            b.homeHongdian.setVisibility(View.GONE);
        }
        if(su.showHaveNewAppOrder()==1){
            b.mineOrdershow.setVisibility(View.VISIBLE);
        }else{
            b.mineOrdershow.setVisibility(View.GONE);
        }
        setName();
        return b.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventMainThread(TuiSongBusBean event) {
        if(event.getSs()==1){
          b.homeHongdian.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(),"您有新的注册用户",Toast.LENGTH_SHORT).show();
        }
        if(event.getSs()==2){
            b.mineOrdershow.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(),"有人下单了请及时联系",Toast.LENGTH_SHORT).show();

        }

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
                tocard();
                break;
            case R.id.home_myform:
              toNewOrder();
                break;
            case R.id.mine_yijiedan:
                toform(0);
                break;
            case R.id.mine_daipingjia:
                toform(1);
                break;
            case R.id.mine_yipingjia:
                toform(2);
                break;
            case R.id.mine_shoucard:
                showcard();
                break;
            case R.id.home_tuisong:
                toTuisong();
                break;
        }
    }
    //跳转到新订单界面
    private void  toNewOrder(){
        b.mineOrdershow.setVisibility(View.GONE);
        su.saveHaveNewAppOrder(0);
        startActivity(new Intent(getActivity(), NewOrderActivity.class));
    }

    //跳转到后台推送的用户信息界面
    private void toTuisong() {
        b.homeHongdian.setVisibility(View.GONE);
        su.saveHaveUser(0);
        startActivity(new Intent(activity, TuiSongActivity.class));
    }

    //展示个人的名片信息
    private void showcard() {
        intent = new Intent(activity, ShowPersonCardActivity.class);
        intent.putExtra("pic", BaseData.WEBSITE + bean.getWaiter_pic());
        startActivity(intent);
    }

    private void toform(int id) {
        intent = new Intent(activity, MyFormActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    Callback.Cancelable cancel;
//跳转到我的订单界面
//    private void toForms(final int id) {
//        dialog = ProgressDialog.show(activity, "", "正在获取订单信息");
//        dialog.show();
//        Map<String, Object> parm = new HashMap<>();
//        parm.put("waiter_id", su.showUid());
//        cancel=    NetUtils.Post(BaseData.ALLORDERS, parm, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                if (result.substring(0, 18).contains("Error")) {
//                    NetError error = gson.fromJson(result, NetError.class);
//                    Toast.makeText(getActivity(), error.getMsg(), Toast.LENGTH_SHORT).show();
//                } else {
//                    OrdersBean forms = gson.fromJson(result, OrdersBean.class);
//                    intent = new Intent(activity, MyFormActivity.class);
//                    intent.putExtra("allorders", forms);
//                    intent.putExtra("id",id);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//               dialog.dismiss();
//            }
//        });
//    }


    //跳转到我的银行卡界面
    private void tocard() {
        intent = new Intent(activity, CardActivity.class);
        startActivity(intent);
    }

    //跳转到个人信息界面
    private void toPersonMsg() {
        dialog = ProgressDialog.show(activity, "", "正在获取个人信息");
        dialog.show();
        Map<String, Object> parm = new HashMap<>();
        parm.put("waiter_id", su.showUid());
        cancel = NetUtils.Post(BaseData.SHANCHANG, parm, new Callback.CommonCallback<String>() {
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
            bean = DButils.DB.findFirst(LoginBean2.DataBean.class);
            if (bean.getWaiter_pic() != null) {
                String image = bean.getWaiter_pic();
                if (image.startsWith(".")) {
                    String simg = bean.getWaiter_pic().substring(1, image.length());
                    x.image().bind(b.mineHead, BaseData.BASEURL + simg);
                } else {
                    Picasso.with(getActivity()).load(BaseData.BASEURL + "//" + image).into(b.mineHead);
                }
                if (su.showIsRenzheng() == 1) {
                    b.mineRenzhengtext.setText("代理人");
                } else {
                    b.mineRenzhengtext.setText("尚未认证");
                }
            }
            if (bean.getWaiter_name() != null) {
                b.mineName.setText(bean.getWaiter_name());
            }
//            Log.e("receiver",""+bean.getNew_gays()+"newgay");
//            if(bean.getNew_gays()==1){
//                b.homeHongdian.setVisibility(View.VISIBLE);
//            }else{
//                b.homeHongdian.setVisibility(View.GONE);
//                su.saveHaveUser(0);
//            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
