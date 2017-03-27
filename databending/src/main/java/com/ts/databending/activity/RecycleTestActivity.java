package com.ts.databending.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ts.databending.R;
import com.ts.databending.adapter.MyrecycleBinderAdapter;
import com.ts.databending.adapter.NormolRecyclerAdapter;
import com.ts.databending.bean.ReccycleBean;
import com.ts.databending.recycleview.RecycleViewDivider;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/18.
 * 确实很好用，但是点击事件  等怎么办
 */

public class RecycleTestActivity extends AppCompatActivity {
    RecyclerView mreyccleviw;
    ArrayList<ReccycleBean> beenS=new ArrayList<ReccycleBean>(){
        {
            for(int i=0;i<40;i++){
//                add(new ReccycleBean("http://121.199.32.4:8088/Uploads/2017-01-18/587ec04d8a27b.jpg","letgo"));
                add(new ReccycleBean(i+"","http://121.199.32.4:8088/Uploads/2017-01-18/587ec04d8a27b.jpg"));
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testrecycle);
        mreyccleviw= (RecyclerView) this.findViewById(R.id.id_recyclerview);
        mreyccleviw.setLayoutManager(new LinearLayoutManager(this));
        mreyccleviw.addItemDecoration(new RecycleViewDivider(this,RecycleViewDivider.VERTICAL_LIST));  //设置分割线
        mreyccleviw.setItemAnimator(new DefaultItemAnimator());//设置添加和移除动画 默认的就挺好的，更新方法 notifyItemInserted(position)与notifyItemRemoved(position)
//        List<String> strs=new ArrayList<>();
//        strs.add("ss");
//        strs.add("ss");
//        strs.add("ss");
//        strs.add("ss");
//        mreyccleviw.setAdapter(new MyrecycleBinderAdapter(beenS));
        final NormolRecyclerAdapter adapter=new NormolRecyclerAdapter(this,beenS);
        //添加recycleradapter  item的点击事件和长按事件
        adapter.setOnItemClickLitener(new NormolRecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecycleTestActivity.this, "嗲机", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
              adapter.removeData(position);
                Toast.makeText(RecycleTestActivity.this, "长按移除", Toast.LENGTH_SHORT).show();
            }
        });
         mreyccleviw.setAdapter(adapter);

    }
}
