package com.daili.tsapp.jsView;


public class BaseData1 {
    /*
      存放链接

     */

    public static final String BASEURL = "http://121.199.32.4:8088";
    /*
    http://121.199.32.4:8088/index.php/Home/Index/partner_login/partner_tel/18266142739/partner_password/123456
     */
    public static final String LOGIN = BASEURL + "/index.php/Home/Index/partner_login";

    /*
    添加银行卡
    http://121.199.32.4:8088/index.php/Home/index/add_bank_card/partner_id/3/bank_name/银行名称/bank_card_num/123/withdrawals_password/123456
     */
    public static final String ADDBANKCARD = BASEURL + "/index.php/Home/index/add_bank_card/";
    /*
    体现
    http://121.199.32.4:8088/index.php/Home/index/partner_withdrawals_record/partner_id/3/bank_name/银行名称/bank_card_num/123/withdrawals_money/100000/
    withdrawals_password/123456
     */
    public static final String DRAW_MONEY = BASEURL + "/index.php/Home/index/partner_withdrawals_record/";
    /*
      设置提现密码
      http://121.199.32.4:8088/index.php/Home/index/set_withdrawals_password/partner_id/3/withdrawals_password/111111111
     */
    public static final String SETPASS = BASEURL + "/index.php/Home/index/set_withdrawals_password/";
    /*
    判断是否设置过密码
    http://121.199.32.4:8088/index.php/Home/index/is_set_password/partner_id/3
    data：1  已设置提现面膜   data : 2未设置提现密码
     */
    public static final String ISHADPASS = BASEURL + "/index.php/Home/index/is_set_password/";
    //短信验证
    public static final String DUANXINYANZHENG = BASEURL + "/index.php/Home/Index/sendcode/";
    //展示协议的网页
    public static final String XIEYI = "http://121.199.32.4:8088/index.php/Home/Index/partner_xieyi.html";
    //提供分享信息的接口  http://121.199.32.4:8088/index.php/Home/Index/partner_spread
    public static final String FENXIANG = BASEURL + "/index.php/Home/Index/partner_spread";
}
