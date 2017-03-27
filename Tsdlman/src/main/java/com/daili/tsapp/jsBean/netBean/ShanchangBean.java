package com.daili.tsapp.jsBean.netBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 * 请求得到所有的擅长领域的bean
 */

public class ShanchangBean implements Serializable {
    /**
     * flag : Success
     * msg : [{"sid":"1","name":"商标基础业务","time":"2016-12-27 11:28:56","sid_side":[{"id":"1","side_name":"普通注册","side_belong_id":"1","side_add_time":"2016-12-27 11:34:09","side_price":"597","percentage_price":"200","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/putongzhuce.png"},{"id":"2","side_name":"担保注册","side_belong_id":"1","side_add_time":"2016-12-27 11:35:25","side_price":"1700","percentage_price":"300","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/danbaozhuce.png"},{"id":"3","side_name":"无忧注册","side_belong_id":"1","side_add_time":"2016-12-27 11:36:08","side_price":"1200","percentage_price":"300","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/wuyouzhuce.png"},{"id":"4","side_name":"变更","side_belong_id":"1","side_add_time":"2016-12-27 11:36:25","side_price":"1000","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/biangeng.png"},{"id":"5","side_name":"更正","side_belong_id":"1","side_add_time":"2016-12-27 11:36:45","side_price":"1000","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/gengzheng.png"},{"id":"6","side_name":"续展","side_belong_id":"1","side_add_time":"2016-12-27 11:37:17","side_price":"2700","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/xuzhan.png"},{"id":"7","side_name":"宽展","side_belong_id":"1","side_add_time":"2016-12-27 11:37:43","side_price":"3000","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/kuanzhan.png"},{"id":"8","side_name":"转让","side_belong_id":"1","side_add_time":"2016-12-27 16:20:11","side_price":"1500","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/zhuanrang.png"},{"id":"9","side_name":"许可备案","side_belong_id":"1","side_add_time":"2016-12-27 16:20:15","side_price":"800","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/xukebeian.png"},{"id":"10","side_name":"商标证补证","side_belong_id":"1","side_add_time":"2016-12-27 16:20:58","side_price":"1500","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/shangbiaobuzheng.png"},{"id":"11","side_name":"转变续补证","side_belong_id":"1","side_add_time":"2016-12-27 16:21:26","side_price":"600","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/zhuanbianxubuzheng.png"},{"id":"12","side_name":"商标注销","side_belong_id":"1","side_add_time":"2016-12-27 16:21:45","side_price":"500","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/shangbiaozhuxiao.png"},{"id":"34","side_name":"智能商标注册","side_belong_id":"1","side_add_time":"2017-02-13 15:13:35","side_price":"597","percentage_price":"200","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/zhinengshangbiaozhuce.png"}]},{"sid":"2","name":"商标案件业务","time":"2016-12-27 11:29:12","sid_side":[{"id":"13","side_name":"驳回复审","side_belong_id":"2","side_add_time":"2016-12-27 16:22:20","side_price":"3500","percentage_price":"200","side_pic":"/Public/Home/img/side/70c5fb6f14ba1d217fcfdc03c4076ac0.jpg","side_jieshao_pic":"/Public/Home/img/sides/bohuifushen.png"},{"id":"14","side_name":"商标异议","side_belong_id":"2","side_add_time":"2016-12-27 16:22:45","side_price":"2700","percentage_price":"300","side_pic":"/Public/Home/img/side/70c5fb6f14ba1d217fcfdc03c4076ac0.jpg","side_jieshao_pic":"/Public/Home/img/sides/shangbiaoyiyi.png"},{"id":"15","side_name":"撤三","side_belong_id":"2","side_add_time":"2016-12-27 16:23:05","side_price":"2700","percentage_price":"200","side_pic":"/Public/Home/img/side/70c5fb6f14ba1d217fcfdc03c4076ac0.jpg","side_jieshao_pic":"/Public/Home/img/sides/chesan.png"},{"id":"16","side_name":"撤三复审","side_belong_id":"2","side_add_time":"2016-12-27 16:23:23","side_price":"3500","percentage_price":"200","side_pic":"/Public/Home/img/side/70c5fb6f14ba1d217fcfdc03c4076ac0.jpg","side_jieshao_pic":"/Public/Home/img/sides/chesanfushen.png"},{"id":"17","side_name":"无效宣告","side_belong_id":"2","side_add_time":"2016-12-27 16:23:41","side_price":"4000","percentage_price":"300","side_pic":"/Public/Home/img/side/70c5fb6f14ba1d217fcfdc03c4076ac0.jpg","side_jieshao_pic":"/Public/Home/img/sides/wuxiaoxuangao.png"}]},{"sid":"3","name":"版权业务","time":"2016-12-27 11:29:46","sid_side":[{"id":"18","side_name":"一般作品登记（美术、音乐、文字、模型等）","side_belong_id":"3","side_add_time":"2016-12-27 16:24:50","side_price":"498","percentage_price":"200","side_pic":"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg","side_jieshao_pic":"/Public/Home/img/sides/yibanzuopindengji.png"},{"id":"19","side_name":"一般作品加急登记（美术、音乐、文字、模型等）","side_belong_id":"3","side_add_time":"2016-12-27 16:25:13","side_price":"898","percentage_price":"300","side_pic":"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg","side_jieshao_pic":"/Public/Home/img/sides/yibanzuopinjiajidnegji.png"},{"id":"20","side_name":"一般作品普特急登记","side_belong_id":"3","side_add_time":"2016-12-27 16:25:39","side_price":"2000","percentage_price":"300","side_pic":"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg","side_jieshao_pic":"/Public/Home/img/sides/yibanzuopintejidengji.png"},{"id":"21","side_name":"一般作品变更","side_belong_id":"3","side_add_time":"2016-12-27 16:26:07","side_price":"700","percentage_price":"100","side_pic":"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg","side_jieshao_pic":"/Public/Home/img/sides/yibanzuopingenggai.png"},{"id":"22","side_name":"一般作品转让","side_belong_id":"3","side_add_time":"2016-12-27 16:26:37","side_price":"900","percentage_price":"100","side_pic":"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg","side_jieshao_pic":"/Public/Home/img/sides/yibanzuopinzhuanrang.png"},{"id":"23","side_name":"一般作品补证","side_belong_id":"3","side_add_time":"2016-12-27 16:27:01","side_price":"600","percentage_price":"100","side_pic":"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg","side_jieshao_pic":"/Public/Home/img/sides/yibanzuopinbuzheng.png"},{"id":"24","side_name":"软件著作权登记","side_belong_id":"3","side_add_time":"2016-12-27 16:27:37","side_price":"2400","percentage_price":"200","side_pic":"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg","side_jieshao_pic":"/Public/Home/img/sides/ruanjianzzhuzuoquan.png"},{"id":"25","side_name":"软件著作权加急登记","side_belong_id":"3","side_add_time":"2016-12-27 16:28:01","side_price":"4600","percentage_price":"300","side_pic":"/Public/Home/img/side/79feb2984f17a2ec7b71e42e023697f1.jpg","side_jieshao_pic":"/Public/Home/img/sides/ruanjianzhuzuoquanjiaji.png"}]},{"sid":"4","name":"专利申请","time":"2016-12-27 11:29:57","sid_side":[{"id":"26","side_name":"发明专利申请","side_belong_id":"4","side_add_time":"2016-12-27 16:28:44","side_price":"2999","percentage_price":"600","side_pic":"/Public/Home/img/side/a82e2958c13f4d90ab9aeae8c076f740.jpg","side_jieshao_pic":"/Public/Home/img/sides/famingzhuanlishenqing.png"},{"id":"27","side_name":"实用新型专利","side_belong_id":"4","side_add_time":"2016-12-27 16:29:12","side_price":"1500","percentage_price":"300","side_pic":"/Public/Home/img/side/a82e2958c13f4d90ab9aeae8c076f740.jpg","side_jieshao_pic":"/Public/Home/img/sides/shiyongxinxizhuanli.png"},{"id":"28","side_name":"外观专利申请","side_belong_id":"4","side_add_time":"2016-12-27 16:29:29","side_price":"500","percentage_price":"100","side_pic":"/Public/Home/img/side/a82e2958c13f4d90ab9aeae8c076f740.jpg","side_jieshao_pic":"/Public/Home/img/sides/waiguanzhuanlishenqing.png"}]},{"sid":"5","name":"法律服务","time":"2016-12-27 11:30:11","sid_side":[{"id":"29","side_name":"法律年度顾问","side_belong_id":"5","side_add_time":"2016-12-27 16:29:55","side_price":"5999","percentage_price":"500","side_pic":"/Public/Home/img/side/a82e2958c13f4d90ab9aeae8c076f740.jpg","side_jieshao_pic":"/Public/Home/img/sides/falvnianduguwen.png"}]},{"sid":"6","name":"科技项目","time":"2016-12-27 11:30:25","sid_side":[{"id":"30","side_name":"高新技术企业认证","side_belong_id":"6","side_add_time":"2016-12-27 16:30:24","side_price":"20000","percentage_price":"800","side_pic":"/Public/Home/img/side/a82e2958c13f4d90ab9aeae8c076f740.jpg","side_jieshao_pic":"/Public/Home/img/sides/gaoxijishuqiyerenzheng.png"}]},{"sid":"7","name":"国际商标注册","time":"2016-12-27 11:30:38","sid_side":[{"id":"31","side_name":"一标一类","side_belong_id":"7","side_add_time":"2016-12-27 16:30:46","side_price":"1000","percentage_price":"300","side_pic":"/Public/Home/img/side/b457446046d1b887c8c3fe05c2e575be.jpg","side_jieshao_pic":"/Public/Home/img/sides/yibiaoyilei.png"},{"id":"32","side_name":"一标两类","side_belong_id":"7","side_add_time":"2016-12-27 16:31:15","side_price":"2400","percentage_price":"300","side_pic":"/Public/Home/img/side/b457446046d1b887c8c3fe05c2e575be.jpg","side_jieshao_pic":"/Public/Home/img/sides/yibiaolianglei.png"},{"id":"33","side_name":"一标三类","side_belong_id":"7","side_add_time":"2016-12-27 16:31:32","side_price":"1000","percentage_price":"300","side_pic":"/Public/Home/img/side/b457446046d1b887c8c3fe05c2e575be.jpg","side_jieshao_pic":"/Public/Home/img/sides/yibiaosanlei.png"}]}]
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

    public static class MsgBean implements Serializable{
        /**
         * sid : 1
         * name : 商标基础业务
         * time : 2016-12-27 11:28:56
         * sid_side : [{"id":"1","side_name":"普通注册","side_belong_id":"1","side_add_time":"2016-12-27 11:34:09","side_price":"597","percentage_price":"200","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/putongzhuce.png"},{"id":"2","side_name":"担保注册","side_belong_id":"1","side_add_time":"2016-12-27 11:35:25","side_price":"1700","percentage_price":"300","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/danbaozhuce.png"},{"id":"3","side_name":"无忧注册","side_belong_id":"1","side_add_time":"2016-12-27 11:36:08","side_price":"1200","percentage_price":"300","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/wuyouzhuce.png"},{"id":"4","side_name":"变更","side_belong_id":"1","side_add_time":"2016-12-27 11:36:25","side_price":"1000","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/biangeng.png"},{"id":"5","side_name":"更正","side_belong_id":"1","side_add_time":"2016-12-27 11:36:45","side_price":"1000","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/gengzheng.png"},{"id":"6","side_name":"续展","side_belong_id":"1","side_add_time":"2016-12-27 11:37:17","side_price":"2700","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/xuzhan.png"},{"id":"7","side_name":"宽展","side_belong_id":"1","side_add_time":"2016-12-27 11:37:43","side_price":"3000","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/kuanzhan.png"},{"id":"8","side_name":"转让","side_belong_id":"1","side_add_time":"2016-12-27 16:20:11","side_price":"1500","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/zhuanrang.png"},{"id":"9","side_name":"许可备案","side_belong_id":"1","side_add_time":"2016-12-27 16:20:15","side_price":"800","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/xukebeian.png"},{"id":"10","side_name":"商标证补证","side_belong_id":"1","side_add_time":"2016-12-27 16:20:58","side_price":"1500","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/shangbiaobuzheng.png"},{"id":"11","side_name":"转变续补证","side_belong_id":"1","side_add_time":"2016-12-27 16:21:26","side_price":"600","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/zhuanbianxubuzheng.png"},{"id":"12","side_name":"商标注销","side_belong_id":"1","side_add_time":"2016-12-27 16:21:45","side_price":"500","percentage_price":"100","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/shangbiaozhuxiao.png"},{"id":"34","side_name":"智能商标注册","side_belong_id":"1","side_add_time":"2017-02-13 15:13:35","side_price":"597","percentage_price":"200","side_pic":"/Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg","side_jieshao_pic":"/Public/Home/img/sides/zhinengshangbiaozhuce.png"}]
         */

        private String sid;
        private String name;
        private String time;
        private List<SidSideBean> sid_side;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<SidSideBean> getSid_side() {
            return sid_side;
        }

        public void setSid_side(List<SidSideBean> sid_side) {
            this.sid_side = sid_side;
        }

        public static class SidSideBean implements Serializable{
            /**
             * id : 1
             * side_name : 普通注册
             * side_belong_id : 1
             * side_add_time : 2016-12-27 11:34:09
             * side_price : 597
             * percentage_price : 200
             * side_pic : /Public/Home/img/side/097add3c3aff835d3da370ceef856c19.jpg
             * side_jieshao_pic : /Public/Home/img/sides/putongzhuce.png
             */

            private int id;
            private String side_name;
            private String side_belong_id;
            private String side_add_time;
            private String side_price;
            private String percentage_price;
            private String side_pic;
            private String side_jieshao_pic;
            private int isChoise;

            public int getIsChoise() {
                return isChoise;
            }

            public void setIsChoise(int isChoise) {
                this.isChoise = isChoise;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSide_name() {
                return side_name;
            }

            public void setSide_name(String side_name) {
                this.side_name = side_name;
            }

            public String getSide_belong_id() {
                return side_belong_id;
            }

            public void setSide_belong_id(String side_belong_id) {
                this.side_belong_id = side_belong_id;
            }

            public String getSide_add_time() {
                return side_add_time;
            }

            public void setSide_add_time(String side_add_time) {
                this.side_add_time = side_add_time;
            }

            public String getSide_price() {
                return side_price;
            }

            public void setSide_price(String side_price) {
                this.side_price = side_price;
            }

            public String getPercentage_price() {
                return percentage_price;
            }

            public void setPercentage_price(String percentage_price) {
                this.percentage_price = percentage_price;
            }

            public String getSide_pic() {
                return side_pic;
            }

            public void setSide_pic(String side_pic) {
                this.side_pic = side_pic;
            }

            public String getSide_jieshao_pic() {
                return side_jieshao_pic;
            }

            public void setSide_jieshao_pic(String side_jieshao_pic) {
                this.side_jieshao_pic = side_jieshao_pic;
            }

            @Override
            public String toString() {
                return "SidSideBean{" +
                        "id='" + id + '\'' +
                        ", side_name='" + side_name + '\'' +
                        ", side_belong_id='" + side_belong_id + '\'' +
                        ", side_add_time='" + side_add_time + '\'' +
                        ", side_price='" + side_price + '\'' +
                        ", percentage_price='" + percentage_price + '\'' +
                        ", side_pic='" + side_pic + '\'' +
                        ", side_jieshao_pic='" + side_jieshao_pic + '\'' +
                        ", isChoise=" + isChoise +
                        '}';
            }
        }
    }
}
