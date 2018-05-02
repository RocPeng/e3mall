package com.liger.order.service.impl;

import com.liger.common.jedis.JedisClient;
import com.liger.common.utils.E3Result;
import com.liger.mapper.TbOrderItemMapper;
import com.liger.mapper.TbOrderMapper;
import com.liger.mapper.TbOrderShippingMapper;
import com.liger.model.OrderItem;
import com.liger.model.OrderShipping;
import com.liger.order.global.OrderStatus;
import com.liger.order.model.OrderInfo;
import com.liger.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by roc_peng on 2017/9/4.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Service
public class OrderServiceImpl implements OrderService {
    //订单号生成key
    @Value("${ORDER_ID_GEN_KEY}")
    private String ORDER_ID_GEN_KEY;
    //订单号初始值
    @Value("${ORDER_ID_START}")
    private String ORDER_ID_START;
    //订单明细id生成key
    @Value("${ORDER_DETAIL_ID_GEN_KEY}")
    private String ORDER_DETAIL_ID_GEN_KEY;
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;


    @Override
    public E3Result createOrder(OrderInfo orderInfo) {
        //使用redis计算订单编号
        if(!jedisClient.exists(ORDER_ID_GEN_KEY)){
            jedisClient.set(ORDER_ID_GEN_KEY,ORDER_ID_START);
        }
        String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
        orderInfo.setOrderId(orderId);
        orderInfo.setStatus(OrderStatus.NOT_PAY);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderMapper.insert(orderInfo);
        //订单明细表
        List<OrderItem> list = orderInfo.getOrderItems();
        for(OrderItem orderItem : list){
            String id = jedisClient.incr(ORDER_DETAIL_ID_GEN_KEY).toString();
            orderItem.setId(id);
            orderItem.setOrderId(orderId);
            orderItemMapper.insert(orderItem);
        }
        //订单物流表
        OrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingMapper.insert(orderShipping);
        return E3Result.ok(orderId);
    }
}
