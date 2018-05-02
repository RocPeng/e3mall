package com.liger.order.global;

/**
 * Created by roc_peng on 2017/9/4.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 订单状态
 */

public class OrderStatus {
    //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
    public static final Integer NOT_PAY = 1;
    public static final Integer HAS_PAYED = 2;
    public static final Integer NOT_DELIVER = 3;
    public static final Integer HAS_DELIVERED = 4;
    public static final Integer DEAL_SUCCESS = 5;
    public static final Integer DEAL_CLOED = 6;

}
