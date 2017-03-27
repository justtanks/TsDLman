package com.daili.tsapp.tsFragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.daili.tsapp.R;
import com.daili.tsapp.databinding.GetOrderBinding;
import com.daili.tsapp.jsBean.HadFormNum;
import com.daili.tsapp.jsBean.NewFormNum;
import com.daili.tsapp.tsActivity.TabHomeActivity;
import com.daili.tsapp.tsBase.BaseFragment;
import com.daili.tsapp.utils.SystemUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
/**
 *  首界面抢单界面使用的fragment
 *  结构 这个界面还有一个加载fragment的选项
 */
public class GetOrderFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener{
    GetOrderBinding b;
    TabHomeActivity activity;
    private NewFormFragment newFormFragment = new NewFormFragment();
    private OwnedFormFragment ownedFormFragment = new OwnedFormFragment();
    private FragmentManager manager;

    private SystemUtil su = new SystemUtil(activity);
    public GetOrderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b= DataBindingUtil.inflate(inflater,R.layout.fragment_get_order,container,false);
        init();
         return b.getRoot();
    }
    //初始化操作
    private void init() {
        activity= (TabHomeActivity) getActivity();
        b.toptabRadiogroup.setOnCheckedChangeListener(this);
        manager = activity.getSupportFragmentManager();
        EventBus.getDefault().register(this);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.qiangdan_framecontent, ownedFormFragment, "ownedform");
        transaction.add(R.id.qiangdan_framecontent, newFormFragment, "newform");
        transaction.hide(ownedFormFragment);
        transaction.commit();

        //跳转到评论界面
//        msg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                msg.setImageResource(R.mipmap.message_nomes);
//                Intent intent = new Intent(FindFormsActivity.this, MessageActivity.class);
//                intent.putExtra("pinglun", bean);
//                startActivity(intent);
//            }
//        });
//         handler.post(runnable);
    }
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1:
//                    break;
//            }
//        }
//    };
//    //执行访问网络的子线程 相当于定时器
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
////            getPinglunOnNet();
//            handler.postDelayed(runnable, 1 * 20 * 1000);
//        }
//    };

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (checkedId == R.id.toptab_new) {
            b.toptabNew.setTextColor(getResources().getColor(R.color.qingdan_toptabtex_seleter));
            b.toptabHave.setTextColor(getResources().getColor(R.color.qiangdan_toptabtex_notselect));
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(ownedFormFragment);
            transaction.show(newFormFragment);
            transaction.commit();


        } else if (checkedId == R.id.toptab_have) {
            b.toptabNew.setTextColor(getResources().getColor(R.color.qiangdan_toptabtex_notselect));
            b.toptabHave.setTextColor(getResources().getColor(R.color.qingdan_toptabtex_seleter));
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(newFormFragment);
            transaction.show(ownedFormFragment);
            transaction.commit();
        }
    }

    //来订单之后更新tab 文字
    @Subscribe
    public void onEvent(HadFormNum num) {
        b.toptabHave.setText("已抢订单" + "(" + num.getNum() + ")");
    }

    @Subscribe
    public void onEvent(NewFormNum num) {
        b.toptabNew.setText("新订单" + "(" + num.getNum() + ")");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //获取评论部分，如果去掉就不进行添加

//    private PingLunBean bean = new PingLunBean();
//    private ErrorBean error = null;
//    private void getPinglunOnNet() {
//        Map<String, Object> parms = new HashMap<>();
//        parms.put("waiter_id",su.showUid());
//        Xutils.Post(BaseData.PINGLUN, parms, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                if (result.substring(0, 19).contains("Error")) {
//                    return;
//                }
//                bean = gson.fromJson(result, PingLunBean.class);
//                if (bean == null) {
//                    return;
//                }
//                if (bean.getData().size() > su.showPinglun()) {
//                    msg.setImageResource(R.mipmap.message_havemsg);
//                    su.savePinlun(bean.getData().size());
//                } else {
//                    msg.setImageResource(R.mipmap.message_nomes);
//                }
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                loge(ex.toString());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
}
