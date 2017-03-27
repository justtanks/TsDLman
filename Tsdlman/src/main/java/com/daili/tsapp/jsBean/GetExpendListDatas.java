package com.daili.tsapp.jsBean;

import android.content.Intent;

import com.daili.tsapp.jsBean.ExpendListGroupBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/7.
 * 构建所有的expendlistview  数据，用于在进度展示中使用。。。。。。
 * 传入字符串，转换为数组之后，将点击状态设置为true
 * 然后根据具体逻辑将具体的某项设置为不可以点击
 * 先将所有的放到一个map中是不是更好点
 * manmanweihubasu
 */

public class GetExpendListDatas {
    ExpendListChildBean child1 = new ExpendListChildBean(1, "商标局受理申请", 1);
    ExpendListChildBean child2 = new ExpendListChildBean(2, "申报材料符合要求，进入实质审查", 2);
    ExpendListChildBean child3 = new ExpendListChildBean(3, "审查未通过，延期补正", 2);
    ExpendListChildBean child4 = new ExpendListChildBean(4, "商标符合要求，正在初步审定公告", 3);
    ExpendListChildBean child5 = new ExpendListChildBean(5, "商标被驳回，请申请复审", 3);
    ExpendListChildBean child6 = new ExpendListChildBean(6, "复审申请失败，不予受理", 3);
    ExpendListChildBean child7 = new ExpendListChildBean(7, "复审通过，进入公告期", 3);
    ExpendListChildBean child8 = new ExpendListChildBean(8, "初步审定公告中", 4);
    ExpendListChildBean child9 = new ExpendListChildBean(9, "提出异议中", 4);
    ExpendListChildBean child10 = new ExpendListChildBean(10, "异议理由成立，不予核准审核", 4);
    ExpendListChildBean child11 = new ExpendListChildBean(11, "异议答辩中", 4);
    ExpendListChildBean child12 = new ExpendListChildBean(12, "答辩通过，进入公告期", 4);
    ExpendListChildBean child13 = new ExpendListChildBean(13, "答辩未通过，提出申诉", 4);
    ExpendListChildBean child14 = new ExpendListChildBean(14, "核准通过，正在下证中", 5);
    ExpendListChildBean child15 = new ExpendListChildBean(15, "已下证", 6);
    private List<ExpendListChildBean> allchils = new ArrayList<>();
    private List<Integer> ins = new ArrayList<>();
    //将string 转换为arraylist
    public List<List<ExpendListChildBean>> setIndex(String str) {
        StringBuffer sf = new StringBuffer(str);
        if (str != null && str.startsWith("[") && str.endsWith("]")) {
            String s = str.substring(1, str.length() - 1);
            String[] strs = s.split(",");
            for (String s1 : strs) {
                ins.add(Integer.parseInt(s1));
            }
            return setListBackData(ins);
        }
        return null;
    }

    //实际使用的设置list获取到数据的
    public List<List<ExpendListChildBean>> setListBackData(List<Integer> ins) {
        getchilds(ins);
        return getAllchild();
    }

    public List<ExpendListGroupBean> getGroups() {
        List<ExpendListGroupBean> datas = new ArrayList<>();
        ExpendListGroupBean bean1 = new ExpendListGroupBean(1, "商标已送报国家商标局");
        ExpendListGroupBean bean2 = new ExpendListGroupBean(2, "形式审查中（约3~4个月）");
        ExpendListGroupBean bean3 = new ExpendListGroupBean(3, "实质审查中（约9~10个月）");
        ExpendListGroupBean bean4 = new ExpendListGroupBean(4, "公告期（3个月）");
        ExpendListGroupBean bean5 = new ExpendListGroupBean(5, "核准期");
        ExpendListGroupBean bean6 = new ExpendListGroupBean(6, "收到注册证");
        //给每组数据添加自数据 cao
        List<ExpendListChildBean> datas1 = new ArrayList<>();
        datas1.add(child1);
        bean1.setBeens(datas1);
        List<ExpendListChildBean> datas2 = new ArrayList<>();
        datas2.add(child2);
        datas2.add(child3);
        bean2.setBeens(datas2);

        List<ExpendListChildBean> datas3 = new ArrayList<>();
        datas3.add(child4);
        datas3.add(child5);
        datas3.add(child6);
        datas3.add(child7);
        bean3.setBeens(datas3);

        List<ExpendListChildBean> datas4 = new ArrayList<>();
        datas4.add(child8);
        datas4.add(child9);
        datas4.add(child10);
        datas4.add(child11);
        datas4.add(child12);
        datas4.add(child13);
        bean4.setBeens(datas4);

        List<ExpendListChildBean> datas5 = new ArrayList<>();
        datas5.add(child14);
        bean5.setBeens(datas5);

        List<ExpendListChildBean> datas6 = new ArrayList<>();
        datas6.add(child15);
        bean6.setBeens(datas6);

        datas.add(bean1);
        datas.add(bean2);
        datas.add(bean3);
        datas.add(bean4);
        datas.add(bean5);
        datas.add(bean6);
        return datas;
    }

    public List<List<ExpendListChildBean>> getAllchild() {
        List<List<ExpendListChildBean>> datas0 = new ArrayList<>();
        List<ExpendListChildBean> datas1 = new ArrayList<>();
        datas1.add(allchils.get(0));
        List<ExpendListChildBean> datas2 = new ArrayList<>();
        datas2.add(allchils.get(1));
        datas2.add(allchils.get(2));


        List<ExpendListChildBean> datas3 = new ArrayList<>();
        datas3.add(allchils.get(3));
        datas3.add(allchils.get(4));
        datas3.add(allchils.get(5));
        datas3.add(allchils.get(6));


        List<ExpendListChildBean> datas4 = new ArrayList<>();
        datas4.add(allchils.get(7));
        datas4.add(allchils.get(8));
        datas4.add(allchils.get(9));
        datas4.add(allchils.get(10));
        datas4.add(allchils.get(11));
        datas4.add(allchils.get(12));

        List<ExpendListChildBean> datas5 = new ArrayList<>();
        datas5.add(allchils.get(13));
        List<ExpendListChildBean> datas6 = new ArrayList<>();
        datas6.add(allchils.get(14));

        datas0.add(datas1);
        datas0.add(datas2);
        datas0.add(datas3);
        datas0.add(datas4);
        datas0.add(datas5);
        datas0.add(datas6);
        return datas0;
    }

    //总的现在使用的
    List<ExpendListChildBean> datas = new ArrayList<>();
    public void getchilds(List<Integer> ss) {
        datas.add(child1);
        datas.add(child2);
        datas.add(child3);
        datas.add(child4);
        datas.add(child5);
        datas.add(child6);
        datas.add(child7);
        datas.add(child8);
        datas.add(child9);
        datas.add(child10);
        datas.add(child11);
        datas.add(child12);
        datas.add(child13);
        datas.add(child14);
        datas.add(child15);
        for (ExpendListChildBean ex : datas) {
            if (ss.contains(ex.getChildId())) {
                ex.setIspressed(true);
            }else{
                ex.setIspressed(false);
            }
        }
        allchils = datas;
    }
    //传入点击得最后步，然后设置
    public String getTextByIndex(int index){
        if(index<1){
            return "受理申请";
        }
           for(ExpendListChildBean bean:datas){
               if(bean.getChildId()==index){
                   return bean.getText();
               }
           }
            return null;
    }
}
