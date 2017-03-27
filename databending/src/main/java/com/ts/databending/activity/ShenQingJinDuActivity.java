package com.ts.databending.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.ts.databending.R;
import com.ts.databending.adapter.EXadapter;
import com.ts.databending.bean.XiangqingStepBean;
import com.ts.databending.utils.Xutils;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShenQingJinDuActivity extends Activity {
   //多级列表demo 显示多级的
    List<XiangqingStepBean.MsgBean> msgBeen=new ArrayList<>();  //第一级
  List<List<XiangqingStepBean.MsgBean.TypeBelongBean>> belogbeans=new ArrayList<>();//第二级数据
    ExpandableListView lv;
    EXadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duo_jiliebioao);
        lv= (ExpandableListView) this.findViewById(R.id.expendlistview);
        lv.setGroupIndicator(null);
        onnet();
        lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                adapter.setPos(groupPosition,childPosition);
                return true;
            }
        });
//        lv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                for (int i = 0; i < msgBeen.size(); i++) {
////                    if (groupPosition != i) {
////                        lv.collapseGroup(i);
//                       lv.expandGroup(i);
////                    }
//                }
//            }
//        });

    }

    private  void onnet(){
        Xutils.Get("http://121.199.32.4:8088/index.php/Home/Index/get_type", new HashMap<String, String>(), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                XiangqingStepBean ben=gson.fromJson(result,XiangqingStepBean.class);
                msgBeen=ben.getMsg();
               for(XiangqingStepBean.MsgBean ms:msgBeen){
                   belogbeans.add(ms.getType_belong());
               }

                 adapter=new EXadapter(ShenQingJinDuActivity.this,msgBeen,belogbeans);
                  adapter.setLowid(7);
                 lv.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

                @Override
                public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
