package com.daili.tsapp.jsBean.netBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 * 详情界面上传进展步骤的网络的请求数据
 * 现在不使用了
 */

public class XiangqingStepBean {

    /**

     */

    private String flag;
    private int num;
    private List<MsgBean> msg;
    private List<?> data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public static class MsgBean {
        /**
         * type_name : 商标已送报国家商标局
         * type_belong : [{"id":"1","section_belong_receipt":"1","section_name":"商标局受理申请"}]
         */

        private String type_name;
        private List<TypeBelongBean> type_belong;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public List<TypeBelongBean> getType_belong() {
            return type_belong;
        }

        public void setType_belong(List<TypeBelongBean> type_belong) {
            this.type_belong = type_belong;
        }

        public static class TypeBelongBean {
            /**
             * id : 1
             * section_belong_receipt : 1
             * section_name : 商标局受理申请
             */

            private int id;
            private int section_belong_receipt;
            private String section_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSection_belong_receipt() {
                return section_belong_receipt;
            }

            public void setSection_belong_receipt(int section_belong_receipt) {
                this.section_belong_receipt = section_belong_receipt;
            }

            public String getSection_name() {
                return section_name;
            }

            public void setSection_name(String section_name) {
                this.section_name = section_name;
            }
        }
    }
}
