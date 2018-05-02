package com.liger.order.service;

import com.liger.common.utils.E3Result;
import com.liger.order.model.OrderInfo;

/**
 * Created by roc_peng on 2017/9/4.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public interface OrderService {
    E3Result createOrder(OrderInfo orderInfo);
}
