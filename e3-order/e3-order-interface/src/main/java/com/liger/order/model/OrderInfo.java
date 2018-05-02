package com.liger.order.model;

import com.liger.model.Order;
import com.liger.model.OrderItem;
import com.liger.model.OrderShipping;

import java.io.Serializable;
import java.util.List;

/**
 * Created by roc_peng on 2017/9/4.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public class OrderInfo extends Order implements Serializable {

    private List<OrderItem> orderItems;
    private OrderShipping orderShipping;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(OrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
