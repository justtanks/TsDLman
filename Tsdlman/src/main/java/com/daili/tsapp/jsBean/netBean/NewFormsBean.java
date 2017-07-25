package com.daili.tsapp.jsBean.netBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class NewFormsBean {

    /**
     * flag : Success
     * msg : [{"order_id":"141","order_num":"2017072155994957","order_name":"普通注册","order_put_time":"2017-07-21 16:08:39","who_put_order":"18266142739","order_price":"1494","order_minor_term_count":"17","order_minor_term_nav":[{"s_num":"09","s_name":"科学仪器","sss_detail":[{"sss_name":"DVD驱动器","sssid":"5480"},{"sss_name":"销售终端机（POS机）","sssid":"5795"},{"sss_name":"制蓝图机","sssid":"5839"},{"sss_name":"磅秤","sssid":"5866"},{"sss_name":"电子游标卡尺","sssid":"5882"},{"sss_name":"运载工具故障警告灯","sssid":"5918"},{"sss_name":"成套电话器具","sssid":"5958"},{"sss_name":"便携式收音机","sssid":"6135"},{"sss_name":"电影放映机","sssid":"6313"},{"sss_name":"比色计","sssid":"6419"},{"sss_name":"黄铜丝","sssid":"3521"},{"sss_name":"高架缆车的缆绳","sssid":"3527"},{"sss_name":"捆扎用金属带","sssid":"3533"},{"sss_name":"装卸用金属带","sssid":"3536"},{"sss_name":"金属捆扎线","sssid":"3543"}]},{"s_num":"15","s_name":"乐器","sss_detail":[{"sss_name":"次中音号","sssid":"9170"},{"sss_name":"乐器用簧片","sssid":"9265"}]}],"order_picture":"/Uploads/2017-07-21/5971b687c1807.png"}]
     * data : []
     * num : 1
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
         * order_id : 141
         * order_num : 2017072155994957
         * order_name : 普通注册
         * order_put_time : 2017-07-21 16:08:39
         * who_put_order : 18266142739
         * order_price : 1494
         * order_minor_term_count : 17
         * order_minor_term_nav : [{"s_num":"09","s_name":"科学仪器","sss_detail":[{"sss_name":"DVD驱动器","sssid":"5480"},{"sss_name":"销售终端机（POS机）","sssid":"5795"},{"sss_name":"制蓝图机","sssid":"5839"},{"sss_name":"磅秤","sssid":"5866"},{"sss_name":"电子游标卡尺","sssid":"5882"},{"sss_name":"运载工具故障警告灯","sssid":"5918"},{"sss_name":"成套电话器具","sssid":"5958"},{"sss_name":"便携式收音机","sssid":"6135"},{"sss_name":"电影放映机","sssid":"6313"},{"sss_name":"比色计","sssid":"6419"},{"sss_name":"黄铜丝","sssid":"3521"},{"sss_name":"高架缆车的缆绳","sssid":"3527"},{"sss_name":"捆扎用金属带","sssid":"3533"},{"sss_name":"装卸用金属带","sssid":"3536"},{"sss_name":"金属捆扎线","sssid":"3543"}]},{"s_num":"15","s_name":"乐器","sss_detail":[{"sss_name":"次中音号","sssid":"9170"},{"sss_name":"乐器用簧片","sssid":"9265"}]}]
         * order_picture : /Uploads/2017-07-21/5971b687c1807.png
         */

        private String order_id;
        private String order_num;
        private String order_name;
        private String order_put_time;
        private String who_put_order;
        private String order_price;
        private String order_minor_term_count;
        private String order_picture;
        private List<OrderMinorTermNavBean> order_minor_term_nav;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getOrder_name() {
            return order_name;
        }

        public void setOrder_name(String order_name) {
            this.order_name = order_name;
        }

        public String getOrder_put_time() {
            return order_put_time;
        }

        public void setOrder_put_time(String order_put_time) {
            this.order_put_time = order_put_time;
        }

        public String getWho_put_order() {
            return who_put_order;
        }

        public void setWho_put_order(String who_put_order) {
            this.who_put_order = who_put_order;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getOrder_minor_term_count() {
            return order_minor_term_count;
        }

        public void setOrder_minor_term_count(String order_minor_term_count) {
            this.order_minor_term_count = order_minor_term_count;
        }

        public String getOrder_picture() {
            return order_picture;
        }

        public void setOrder_picture(String order_picture) {
            this.order_picture = order_picture;
        }

        public List<OrderMinorTermNavBean> getOrder_minor_term_nav() {
            return order_minor_term_nav;
        }

        public void setOrder_minor_term_nav(List<OrderMinorTermNavBean> order_minor_term_nav) {
            this.order_minor_term_nav = order_minor_term_nav;
        }

        public static class OrderMinorTermNavBean {
            /**
             * s_num : 09
             * s_name : 科学仪器
             * sss_detail : [{"sss_name":"DVD驱动器","sssid":"5480"},{"sss_name":"销售终端机（POS机）","sssid":"5795"},{"sss_name":"制蓝图机","sssid":"5839"},{"sss_name":"磅秤","sssid":"5866"},{"sss_name":"电子游标卡尺","sssid":"5882"},{"sss_name":"运载工具故障警告灯","sssid":"5918"},{"sss_name":"成套电话器具","sssid":"5958"},{"sss_name":"便携式收音机","sssid":"6135"},{"sss_name":"电影放映机","sssid":"6313"},{"sss_name":"比色计","sssid":"6419"},{"sss_name":"黄铜丝","sssid":"3521"},{"sss_name":"高架缆车的缆绳","sssid":"3527"},{"sss_name":"捆扎用金属带","sssid":"3533"},{"sss_name":"装卸用金属带","sssid":"3536"},{"sss_name":"金属捆扎线","sssid":"3543"}]
             */

            private String s_num;
            private String s_name;
            private List<SssDetailBean> sss_detail;

            public String getS_num() {
                return s_num;
            }

            public void setS_num(String s_num) {
                this.s_num = s_num;
            }

            public String getS_name() {
                return s_name;
            }

            public void setS_name(String s_name) {
                this.s_name = s_name;
            }

            public List<SssDetailBean> getSss_detail() {
                return sss_detail;
            }

            public void setSss_detail(List<SssDetailBean> sss_detail) {
                this.sss_detail = sss_detail;
            }

            public static class SssDetailBean {
                /**
                 * sss_name : DVD驱动器
                 * sssid : 5480
                 */

                private String sss_name;
                private String sssid;

                public String getSss_name() {
                    return sss_name;
                }

                public void setSss_name(String sss_name) {
                    this.sss_name = sss_name;
                }

                public String getSssid() {
                    return sssid;
                }

                public void setSssid(String sssid) {
                    this.sssid = sssid;
                }
            }
        }
    }
}
