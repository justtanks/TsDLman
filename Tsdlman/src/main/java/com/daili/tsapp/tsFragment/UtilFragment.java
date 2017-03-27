package com.daili.tsapp.tsFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.daili.tsapp.R;
import com.daili.tsapp.tsBase.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 抢单界面
 */
@ContentView(R.layout.fragment_util)
public class UtilFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    @ViewInject(R.id.toptab_radiogroup)
    private RadioGroup mRadioGroup;
    @ViewInject(R.id.toptab_new)
    private RadioButton mNewForm;
    @ViewInject(R.id.toptab_have)
    private  RadioButton mAllForm;
    private NewFormFragment newFormFragment=new NewFormFragment();
    private  OwnedFormFragment ownedFormFragment=new OwnedFormFragment();
    private FragmentManager manager;
    private  BaseFragment [] frags={newFormFragment,ownedFormFragment};

    public UtilFragment() {

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("this","onViewCreated");
//      init();
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private  void init(){
        mRadioGroup.setOnCheckedChangeListener(this);
        manager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.qiangdan_framecontent,ownedFormFragment,"ownedform");
        transaction.add(R.id.qiangdan_framecontent,newFormFragment,"newform");
        transaction.commit();
        mRadioGroup.check(R.id.toptab_new);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i==R.id.toptab_new){
            mNewForm.setTextColor(getResources().getColor(R.color.qingdan_toptabtex_seleter));
            mAllForm.setTextColor(getResources().getColor(R.color.qiangdan_toptabtex_notselect));
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(ownedFormFragment);
            transaction.show(newFormFragment);
            transaction.commit();


        }else if(i==R.id.toptab_have){
            mNewForm.setTextColor(getResources().getColor(R.color.qiangdan_toptabtex_notselect));
            mAllForm.setTextColor(getResources().getColor(R.color.qingdan_toptabtex_seleter));
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(newFormFragment);
            transaction.show(ownedFormFragment);
            transaction.commit();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(newFormFragment);
        transaction.remove(ownedFormFragment);
        transaction.commit();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
