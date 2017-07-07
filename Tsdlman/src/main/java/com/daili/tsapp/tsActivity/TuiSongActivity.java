package com.daili.tsapp.tsActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.daili.tsapp.R;
import com.daili.tsapp.jsBean.netBean.ErrorBean;
import com.daili.tsapp.jsBean.netBean.TuiSongUserBean;
import com.daili.tsapp.tsAdapter.TuiSongAdapter;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.utils.NetUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.security.interfaces.DSAKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.daili.tsapp.R.mipmap.phone;

public class TuiSongActivity extends BaseActivity {
    ListView mListView;
    private  List<TuiSongUserBean.MsgBean> datas=new ArrayList<>();
    private TuiSongAdapter adapter;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_song);
        init();
    }

    private void init() {
        builder = new AlertDialog.Builder(this);
        mListView= (ListView) findViewById(R.id.tuisong_lv);
       findViewById(R.id.tuisong_back).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onBackPressed();
           }
       });
        adapter=new TuiSongAdapter(this,datas);
        mListView.setAdapter(adapter);
        getDataOnNet();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(datas.get(position).getNew_gays()==0){
//                    toast("用户已经联系过，请自己电话沟通");
//                    return;
//                }
                popDialog(position);
            }
        });

    }
    //弹出dialog 询问是否打电话
    private void popDialog(final int position) {
        builder.setTitle("确定要进行电话沟通？");
        builder.setCancelable(false).setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 changeUserCase(position);
                dialog.cancel();
            }
        }).setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //改变用户的是否打过电话的状态  改变界面状态  然后跳转到打电话界面
    private void changeUserCase(final  int position){
        datas.get(position).setNew_gays(0);
        adapter.setDatas(datas);
        Map<String,Object> param=new HashMap<>();
        param.put("waiter_id",datas.get(position).getId());
        NetUtils.Post(BaseData.QUXIAOBIAOJI, param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+datas.get(position).getUser_telphone()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



    //获取用户列表
    private  void getDataOnNet(){

        NetUtils.Post(BaseData.ALLNEWUSER, new HashMap<String, Object>(), new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if(result.substring(0,18).contains("Error")){
                    ErrorBean err=new Gson().fromJson(result,ErrorBean.class);
                    toast(err.getMsg());
                    return;
                }
                TuiSongUserBean  data=new Gson().fromJson(result,TuiSongUserBean.class);
                datas=data.getMsg();
                adapter.setDatas(datas);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                  toast("出现错误，请检查网络并重新进入界面");
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
