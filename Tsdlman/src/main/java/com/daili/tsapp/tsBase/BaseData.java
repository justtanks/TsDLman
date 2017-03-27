package com.daili.tsapp.tsBase;

public class BaseData {

    //设置重新请求订单的间隔
    public static final int REFRESHTIME = 20000;

    /*注册信息上传
     http://localhost/brand/index.php/Home/Index/agent_register/agent_name/姓名(字符串)/agent_id_card/身份证(字符串)/agent_pic/
     头像(base64图片)/agent_sex/性别(1:男  2:女)/agent_tel/手机号/agent_work_time/工作年限(int)/agent_good_work/擅长业务(int)/agent_password/(密码)
     */
/*
用户注册
http://192.168.100.33/brand/index.php/Home/Index/agent_register/agent_name/shaowei/agent_id_card/222222222222222
/agent_pic/000000/agent_sex/1)/agent_tel/15621295399/agent_work_time/工作年限(int)/agent_good_work/擅长业务(int)/agent_password/(密码)
 */
    public static final String BASEURL = "http://121.199.32.4:8088";
    //新订单接口
    public static final String XINDINGDAN = BASEURL + "/index.php/Home/Index/wait_order_taking.html";
    public static final String BASEIMG = "http://121.199.32.4:8088";
    //brand/index.php/Home/Index/sendcode/agent_tel/18266142739  短信验证
    public static final String DUANXINYANZHENG = BASEURL + "/index.php/Home/Index/sendcode/";
    //用户注册
    public static final String REGISTER = BASEURL + "/index.php/Home/Index/agent_register/";

    //   http://121.199.32.4:8088/index.php/Home/index/good_work  获取擅长领域 接口，在某个界面中
    public static final String GOODWORK = BASEURL + "/index.php/Home/index/good_work";


    //    http://192.168.100.33/brand/index.php/Home/Index/ get_side/sid/1 擅长领域第二层
//    public static final String GOODWORK2=BASEURL+"/index.php/Home/Index/get_side/";
    //用户登录   http://121.199.32.4:8088/index.php/Home/Index/agent_login/agent_tel/18266142739/agent_password/123456
    public static final String LOGIN = BASEURL + "/index.php/Home/Index/agent_login";
    //    http://121.199.32.4:8088/index.php/Home/Index/jiedan/order_id/10/waiter_id/2
    //order_id/10/waiter_id/2    抢单
    public static final String QIANGDAN = BASEURL + "/index.php/Home/Index/jiedan/";
    //http://121.199.32.4:8088/index.php/Home/Index/yijiedan/waiter_id/69  已接订单接口
    public static final String YIJIEDINGDAN = BASEURL + "/index.php/Home/Index/yijiedan/";

    /*
    更改状态接口
    http://121.199.32.4:8088/index.php/Home/Index/put_order_type/order_id/58/order_acceptance_type/%E6%8F%90%E7%8E%B0
     */
    public static final String CHANGESTYLE = BASEURL + "/index.php/Home/Index/put_order_type";

   /*
   http://121.199.32.4:8088/index.php/Home/Index/get_type  获取进度列表  不使用了
    */
//    public  static  final  String JINDULIEBIAO="http://121.199.32.4:8088/index.php/Home/Index/get_type";


    //    http://121.199.32.4:8088/index.php/Home/index/get_all_evaluate/waiter_id/2  获取所有评论列表
    public static final String PINGLUN = BASEURL + "/index.php/Home/index/get_all_evaluate";

    //http://www.qichengcheng.cn/index.php/Home/Index/get_order_type/order_id/126    请求订单进度展示的接口
    public static final String JINDU = BASEURL + "/index.php/Home/Index/get_order_type";

    //http://www.qichengcheng.cn/index.php/Home/Index/waiter_shouyi/waiter_id/71 首页 报表中请求数据接口
    public static final String BAOBIAO = BASEURL + "/index.php/Home/Index/waiter_shouyi/";
   //http://www.qichengcheng.cn/index.php/Home/Waiter/good_work/waiter_id/71    获取当前代理人的擅长领域
    ///index.php/Home/Waiter/good_work/
    public static final  String SHANCHANG=BASEURL+"/index.php/Home/Waiter/good_work/";
 //更改代理人擅长领域接口http://www.qichengcheng.cn/index.php/Home/Index/put_waiter_good_work/waiter_id/71/good_work/1,2,3,4,5,6,7,8,9
    public static final  String GENGGAISHANCHANG=BASEURL+"/index.php/Home/Index/put_waiter_good_work/";

    //http://www.qichengcheng.cn/index.php/Home/Waiter/get_waiter_bank_card/waiter_id/71  获取银行卡信息
    public static final String GETCARDS=BASEURL+"/index.php/Home/Waiter/get_waiter_bank_card/";
    //http://www.qichengcheng.cn/index.php/Home/Waiter/waiter_order/waiter_id/71 代理人订单
    public static final String GETORDERS=BASEURL+"/index.php/Home/Waiter/waiter_order/";
    //http://www.qichengcheng.cn/index.php/Home/Waiter/waiter_add_bank_card/waiter_id/71/brank_name/1%E5%93%88%E5%93%88%E5%93%88/card_num/11111111111111111111
    //添加银行卡
    public static final String ADDCARD=BASEURL+"/index.php/Home/Waiter/waiter_add_bank_card/";
    //http://www.qichengcheng.cn/index.php/Home/Waiter/put_withdraw_password/waiter_id/71/password/123456 设置提现密码
    public static  final String SETPASS=BASEURL+"/index.php/Home/Waiter/put_withdraw_password/";
  //http://www.qichengcheng.cn/index.php/Home/Waiter/set_withdraw_password/waiter_id/71 判断是否有支付密码
    public static final String HAVAPASS=BASEURL+"/index.php/Home/Waiter/set_withdraw_password";
    //http://www.qichengcheng.cn/index.php/Home/Waiter/opinion/waiter_id/71/nav/111111111111/tel/11111 提交反馈信息
    public static final String FANKUI=BASEURL+"/index.php/Home/Waiter/opinion/";
    //语音合成key
    public static final String YUYINAPPID = "9215030";
    public static final String APIKEY = "j1NkOdD7IKagCQjju45ti4VG";
    public static final String SECRECTKEY = "eff41dec8576dffd104f2bb3ee4c76b5";
}
