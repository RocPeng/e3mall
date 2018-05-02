package com.liger.order.controller;

import com.liger.cart.service.CartService;
import com.liger.common.utils.E3Result;
import com.liger.model.Item;
import com.liger.model.User;
import com.liger.order.model.OrderInfo;
import com.liger.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by roc_peng on 2017/9/4.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Controller
public class OrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    /**
     * 点击去结算 展示订单页面
     * @param request
     * @return
     */
    @RequestMapping("/order/order-cart")
    public String getOrderList(HttpServletRequest request){
//        AbstractJsonpResponseBodyAdvice
//        MappingJackson2HttpMessageConverter

        User user = (User) request.getAttribute("user");
        List<Item> cartList = cartService.getCartList(user.getId());
        request.setAttribute("cartList",cartList);
        return "order-cart";
    }

    /**
     * 订单提交
     * @param orderInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/order/create",method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo,HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        orderInfo.setUserId(user.getId());
        orderInfo.setShippingName(user.getUsername());
        E3Result result = orderService.createOrder(orderInfo);
        if(result.getStatus() == 200){
            //清空购物车
            cartService.clearCartItem(user.getId());
        }
        //把订单号传递给页面
        request.setAttribute("orderId", result.getData());
        request.setAttribute("payment", orderInfo.getPayment());
        return "success";
    }
}
