package com.liger.cart.controller;

import com.liger.cart.service.CartService;
import com.liger.common.utils.CookieUtils;
import com.liger.common.utils.E3Result;
import com.liger.common.utils.JsonUtils;
import com.liger.model.Item;
import com.liger.model.User;
import com.liger.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by roc_peng on 2017/9/3.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Controller
public class CartController {
    @Value("${COOKIE_CART_EXPIRE}")
    private Integer COOKIE_CART_EXPIRE;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartService cartService;


    /**
     * 添加购物车逻辑
     * 先判断用户是否登录?"使用redis保存item":使用cookie保存item
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable("itemId") Long itemId, @RequestParam(defaultValue = "1") Integer num,
                          HttpServletRequest request, HttpServletResponse response){
        //判断用户是否登录 用户登录则使用redis缓存
        User user = (User) request.getAttribute("user") ;
        if(user!=null){
            cartService.addCart(user.getId(),itemId,num);
            return "cartSuccess";
        }

        //未登录使用cookie
        List<Item> items = getItemFromCookie(request);
        //两种情况:1)cookie中存在改商品 则增加数量 2)不存在该商品 则新增
        boolean exist = false;
        for(Item item : items){
            if(Objects.equals(item.getId(), itemId)){
                item.setNum(item.getNum()+num);
                exist = true;
                break;
            }
        }
        if(!exist){
            Item item = itemService.getItemById(itemId);
            item.setNum(num);
            //取第一张图片
            String image = item.getImage();
            if(StringUtils.isNotBlank(image)){
                item.setImage(image.split(",")[0]);
            }
            items.add(item);
        }
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(items),
                COOKIE_CART_EXPIRE,true);
        return "cartSuccess";
    }

    /**
     * cookie中获取购物车数据
     * @return
     */
    public List<Item> getItemFromCookie(HttpServletRequest request){
        String cart = CookieUtils.getCookieValue(request, "cart", true);
        if(StringUtils.isBlank(cart)){
            return new ArrayList<>();
        }
        List<Item> items = JsonUtils.jsonToList(cart, Item.class);
        return items;
    }

    /**
     * 购物车列表
     * 如果用户登录状态?"cookie数据merge到redis,删除cookie,查询redis数据" : "直接查询cookie"
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cart/cart")
    public String cartList(HttpServletRequest request,HttpServletResponse response,Model model){
        List<Item> list = getItemFromCookie(request);
        User user = (User) request.getAttribute("user");
        if(user!=null){
            cartService.mergeCart(user.getId(),list);
            CookieUtils.deleteCookie(request,response,"cart");
            list = cartService.getCartList(user.getId());
        }
        model.addAttribute("cartList",list);
        return "cart";
    }

    /**
     * 更新购物车
     * 用户登录状态?"更新redis" : "更新cookie"
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public E3Result updateList(@PathVariable("itemId") Long itemId, @PathVariable("num") Integer num,
                               HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getAttribute("user");
        if(user!=null){
            cartService.updateCartNum(user.getId(),itemId,num);
        }
        List<Item> list = getItemFromCookie(request);
        for(Item item : list){
            if(Objects.equals(itemId, item.getId())){
                item.setNum(num);
            }
        }
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(list),
                COOKIE_CART_EXPIRE,true);
        return E3Result.ok();
    }

    /**
     * 删除购物车中的商品
     * 用户登录状态?"删除redis的item" : "删除cookie的item"
     * @param itemId
     * @return
     */
    @RequestMapping("/cart/delete/{itemId}")
    public String deleteList(@PathVariable("itemId") Long itemId,
                               HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getAttribute("user");
        if(user!=null){
            cartService.deleteCartItem(user.getId(),itemId);
        }
        List<Item> list = getItemFromCookie(request);
        for(Item item : list){
            if(Objects.equals(itemId, item.getId())){
                list.remove(item);
                break;
            }
        }
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(list),
                COOKIE_CART_EXPIRE,true);
        return "redirect:/cart/cart.html";
    }
}
