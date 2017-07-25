package com.daili.tsapp.jsBean.netBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 * 接收已接订单的接口的javabean
 */

public class OwnFormsBean implements Serializable{
    private String flag;
    private int msg;
    private int num;
    private List<DataBean> data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements  Serializable{
        /**
         * id : 145
         * order_num : 2017072252544897
         * order_name : 普通注册
         * order_put_form : 1
         * order_put_time : 2017-07-22 09:36:04
         * who_put_order : 18553486025
         * order_price : 3762
         * order_minor_term_count : 47
         * order_minor_term_nav : [{"s_num":"09","s_name":"科学仪器","sss_detail":[{"sss_name":"DVD驱动器","sssid":"5480"},{"sss_name":"销售终端机（POS机）","sssid":"5795"},{"sss_name":"制蓝图机","sssid":"5839"},{"sss_name":"磅秤","sssid":"5866"},{"sss_name":"电子游标卡尺","sssid":"5882"},{"sss_name":"运载工具故障警告灯","sssid":"5918"},{"sss_name":"成套电话器具","sssid":"5958"},{"sss_name":"便携式收音机","sssid":"6135"},{"sss_name":"电影放映机","sssid":"6313"},{"sss_name":"比色计","sssid":"6419"},{"sss_name":"复印机器","sssid":"5845"},{"sss_name":"静电复印设备和装置","sssid":"5848"},{"sss_name":"静电复印机","sssid":"5851"}]},{"s_num":"35","s_name":"广告销售","sss_detail":[{"sss_name":"体育人士的宣传管理","sssid":"15071"},{"sss_name":"工商管理咨询","sssid":"15114"},{"sss_name":"市场营销服务","sssid":"15189"},{"sss_name":"职员安置服务","sssid":"15199"},{"sss_name":"商业企业迁移","sssid":"15216"},{"sss_name":"商业文档管理","sssid":"15217"},{"sss_name":"行政会计","sssid":"15251"},{"sss_name":"自动售货机出租","sssid":"15260"},{"sss_name":"药用、兽医用、卫生用制剂和医疗用品的零售或批发服务","sssid":"15263"}]},{"s_num":"38","s_name":"通讯服务","sss_detail":[{"sss_name":"互联网广播服务","sssid":"15629"},{"sss_name":"提供安全电子邮件的电子传输","sssid":"15638"}]},{"s_num":"41","s_name":"教育娱乐","sss_detail":[{"sss_name":"医疗按摩培训","sssid":"16037"},{"sss_name":"组织和举办速度滑冰锦标赛和比赛","sssid":"16069"},{"sss_name":"文学和文献纪录参考图书馆","sssid":"16090"},{"sss_name":"期刊出版","sssid":"16093"},{"sss_name":"电视机出租","sssid":"16104"},{"sss_name":"动物训练","sssid":"16225"},{"sss_name":"组织抽奖","sssid":"16227"}]},{"s_num":"42","s_name":"科技服务","sss_detail":[{"sss_name":"电信设备和部件的设计","sssid":"16231"},{"sss_name":"水文研究","sssid":"16275"},{"sss_name":"农用化学品研究服务","sssid":"16290"},{"sss_name":"基因筛查（为科学研究目的）","sssid":"16298"},{"sss_name":"提供气候变化方面的科学信息","sssid":"16306"},{"sss_name":"机器功能测试","sssid":"16310"},{"sss_name":"电话设计","sssid":"16315"},{"sss_name":"土木工程制图","sssid":"16322"},{"sss_name":"首饰设计","sssid":"16331"},{"sss_name":"计算机硬件设计","sssid":"16333"}]},{"s_num":"45","s_name":"社会服务","sss_detail":[{"sss_name":"追踪被盗运载工具","sssid":"16597"},{"sss_name":"社交陪伴","sssid":"16613"},{"sss_name":"首饰出租","sssid":"16620"},{"sss_name":"伴有火化的宠物葬礼服务","sssid":"16628"},{"sss_name":"炸弹探测服务","sssid":"16635"},{"sss_name":"专利许可申请（法律服务）","sssid":"16659"}]}]
         * order_picture : /Uploads/2017-07-22/5972ac046080d.jpg
         */

        private String id;
        private String order_num;
        private String order_name;
        private int order_put_form;
        private String order_put_time;
        private String who_put_order;
        private String order_price;
        private String order_minor_term_count;
        private String order_picture;
        private int order_wait_pay;
        private List<OrderMinorTermNavBean> order_minor_term_nav;

        public String getId() {
            return id;
        }

        public int getOrder_wait_pay() {
            return order_wait_pay;
        }

        public void setOrder_wait_pay(int order_wait_pay) {
            this.order_wait_pay = order_wait_pay;
        }

        public void setId(String id) {
            this.id = id;
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

        public int getOrder_put_form() {
            return order_put_form;
        }

        public void setOrder_put_form(int order_put_form) {
            this.order_put_form = order_put_form;
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

        public static class OrderMinorTermNavBean implements Serializable{
            /**
             * s_num : 09
             * s_name : 科学仪器
             * sss_detail : [{"sss_name":"DVD驱动器","sssid":"5480"},{"sss_name":"销售终端机（POS机）","sssid":"5795"},{"sss_name":"制蓝图机","sssid":"5839"},{"sss_name":"磅秤","sssid":"5866"},{"sss_name":"电子游标卡尺","sssid":"5882"},{"sss_name":"运载工具故障警告灯","sssid":"5918"},{"sss_name":"成套电话器具","sssid":"5958"},{"sss_name":"便携式收音机","sssid":"6135"},{"sss_name":"电影放映机","sssid":"6313"},{"sss_name":"比色计","sssid":"6419"},{"sss_name":"复印机器","sssid":"5845"},{"sss_name":"静电复印设备和装置","sssid":"5848"},{"sss_name":"静电复印机","sssid":"5851"}]
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

            public static class SssDetailBean implements  Serializable{
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
