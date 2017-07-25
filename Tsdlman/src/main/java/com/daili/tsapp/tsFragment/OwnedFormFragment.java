package com.daili.tsapp.tsFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.HadFormNum;
import com.daili.tsapp.jsBean.daoBean.FormEvent;
import com.daili.tsapp.jsBean.netBean.NewFormsBean;
import com.daili.tsapp.jsBean.netBean.OwnFormsBean;
import com.daili.tsapp.tsActivity.XiangqingActivity;
import com.daili.tsapp.tsAdapter.HadFormAdapter;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsDB.DButils;
import com.daili.tsapp.tsNet.Xutils;
import com.daili.tsapp.utils.SystemUtil;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2017/1/4.
 * 已经抢到订单界面 展示所有的订单
 */
@ContentView(R.layout.fragment_hadform)
public class OwnedFormFragment extends BaseEventFragment implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.hadform_linear)
    RelativeLayout relativeLayout;
    @ViewInject(R.id.hadform_pullview)
    PtrClassicFrameLayout pulltorefresh;
    @ViewInject(R.id.fragment_hadform_list)
    ListView listView;
    private HadFormAdapter hadFormAdapter;
    private SystemUtil su;
    private  static  int REFRESH=3;
    private OwnFormsBean mOwnFormsBean;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    public void setPulltorefresh() {
        pulltorefresh.setLastUpdateTimeRelateObject(this);
        pulltorefresh.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getAllFormsFromNet();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }

    //初始化
    private void init() {
        su=new SystemUtil(getContext());
        setPulltorefresh();
        hadFormAdapter = new HadFormAdapter(new ArrayList<OwnFormsBean.DataBean>(), getContext());
        listView.setAdapter(hadFormAdapter);
        listView.setOnItemClickListener(this);
        getAllFormsFromNet();

    }

//    // 刷新数据
//    private void fresh() {
//        //访问网络，然后将数据下载,更新数据库  改变从数据库获取数据的方式
//        users = getFormFromDB();
//        if (users != null) {
//            EventBus.getDefault().post(new HadFormNum(users.size()));
//            listView.setVisibility(View.VISIBLE);
//            relativeLayout.setVisibility(View.GONE);
//            hadFormAdapter.setData(users);
//            hadFormAdapter.notifyDataSetChanged();
//        } else {
//            EventBus.getDefault().post(new HadFormNum(0));
//            listView.setVisibility(View.GONE);
//            relativeLayout.setVisibility(View.VISIBLE);
//
//
//        }
//        pulltorefresh.refreshComplete();
//    }

    //调用更新数据库和界面的程序
    @Subscribe
    public void onEvent(FormEvent event) {
       getAllFormsFromNet();
    }


    //返回订单，将网络请求数据返回，然后删掉旧表，新建表  这段程序太烂了 明天删掉
    private void getAllFormsFromNet() {
        Map<String,String> parems=new HashMap<>();
        parems.put("waiter_id",su.showUid()+"");
        Xutils.Get(BaseData.YIJIEDINGDAN, parems, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String flat=  result.substring(9,14);
                if(flat.equals("Error")){
//                  fresh();
                    return;
                }
                Gson gson = new Gson();

                    mOwnFormsBean = gson.fromJson(result, OwnFormsBean.class);
                    //获取到数据之后进行展示  并通知顶部数字变化
                if(null!=mOwnFormsBean&&mOwnFormsBean.getData().size()!=0){
                    hadFormAdapter.setData(mOwnFormsBean.getData());
                    hadFormAdapter.notifyDataSetChanged();
                    EventBus.getDefault().post(new HadFormNum(mOwnFormsBean.getData().size()));
                    relativeLayout.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }else{
                    relativeLayout.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }


//                    //删除表，然后写入
//                    try {
//                        DbManager manager = DButils.DB;
//                        manager.dropTable(FormListnew.DataBean.class);
//                        for (FormListnew.DataBean be : bean.getData()) {
//                            manager.save(be);
//                        }
//                    } catch (DbException e) {
//                        e.printStackTrace();
//                    } finally {
//                        fresh();
//                    }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getContext(),getString(R.string.net_err),Toast.LENGTH_SHORT).show();
//                fresh();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                pulltorefresh.refreshComplete();
            }
        });

    }
    //将从网络的hadformnew 中的databean的数据处理，将其list转化为string处理后存储在数据库 操蛋的设计
//    private void  setDatasInbean(FormListnew.DataBean be){
//        List<FormListnew.DataBean.OrderAcceptanceTypeBean> beans=be.getOrder_acceptance_type();
//        StringBuilder bu=new StringBuilder();
//        if(beans!=null&&beans.size()!=0){
//            for(FormListnew.DataBean.OrderAcceptanceTypeBean b:beans){
//               bu.append(b.getOrder_type()+",");
//            }
//            be.setOrder_step(bu.substring(0,bu.length()-1));
//            return ;
//        }else{
//            be.setOrder_step("0");
//        }
//    }
    //从数据库中获取数据
//    private  List<FormListnew.DataBean> getFormFromDB(){
//        try {
//            DbManager manager = x.getDb(DButils.getDaoConfig());
//            List<FormListnew.DataBean> fs = manager.findAll(FormListnew.DataBean.class);
//            if (null != fs) {
//                return fs;
//            }
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    //单项点击，将对象传递过去，而不是通过数据库进行查询
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), XiangqingActivity.class);
        //传递订单对象
        intent.putExtra("orderobject",mOwnFormsBean.getData().get(position));
        startActivityForResult(intent,REFRESH);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REFRESH) {
            getAllFormsFromNet();
        }

    }
}
