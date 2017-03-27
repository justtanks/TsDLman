package com.ts.databending.data;

import android.view.View;

public class BaseData {

/*注册信息上传
 http://localhost/brand/index.php/Home/Index/agent_register/agent_name/姓名(字符串)/agent_id_card/身份证(字符串)/agent_pic/
 头像(base64图片)/agent_sex/性别(1:男  2:女)/agent_tel/手机号/agent_work_time/工作年限(int)/agent_good_work/擅长业务(int)/agent_password/(密码)
 */
/*
用户注册
http://192.168.100.33/brand/index.php/Home/Index/agent_register/agent_name/shaowei/agent_id_card/222222222222222
/agent_pic/000000/agent_sex/1)/agent_tel/15621295399/agent_work_time/工作年限(int)/agent_good_work/擅长业务(int)/agent_password/(密码)

 */
    public static final  String BASEURL="http://121.199.32.4:8088";
    //新订单接口
    public static final String XINDINGDAN=BASEURL+"/index.php/Home/Index/wait_order_taking.html";
	public static final String BASEIMG="http://121.199.32.4:8088";
    //brand/index.php/Home/Index/sendcode/agent_tel/18266142739  短信验证
    public static final String DUANXINYANZHENG=BASEURL+"/index.php/Home/Index/sendcode/";
    //用户注册
    public static final String REGISTER=BASEURL+"/index.php/Home/Index/agent_register/";

//    http://192.168.100.33//brand/index.php/Home/Index/good_work 擅长领域第一层
    public static final String GOODWORK1=BASEURL+"/index.php/Home/Index/good_work";
//    http://192.168.100.33/brand/index.php/Home/Index/ get_side/sid/1 擅长领域第二层
    public static final String GOODWORK2=BASEURL+"/index.php/Home/Index/get_side/";
    //用户登录   http://localhost/brand/index.php/Home/Index/agent_login/agent_tel/18266142739/agent_password/123456
    public  static final  String LOGIN=BASEURL+"/index.php/Home/Index/agent_login";
//    http://121.199.32.4:8088/index.php/Home/Index/jiedan/order_id/10/waiter_id/2
     //order_id/10/waiter_id/2    抢单
    public static final  String QIANGDAN=BASEURL+"/index.php/Home/Index/jiedan/";
    //http://121.199.32.4:8088/index.php/Home/Index/yijiedan/waiter_id/69  已接订单接口
     public static final String YIJIEDINGDAN=BASEURL+"/index.php/Home/Index/yijiedan/";

}
