package com.liger.cart.service.impl;

import com.liger.cart.service.CartService;
import com.liger.common.jedis.JedisClient;
import com.liger.common.utils.E3Result;
import com.liger.common.utils.JsonUtils;
import com.liger.mapper.TbItemMapper;
import com.liger.model.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roc_peng on 2017/9/3.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个类都是对redis中的购物车数据进行操作
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    @Value("${REDIS_CART_PRE}")
    private String REDIS_CART_PRE;
    @Autowired
    private TbItemMapper itemMapper;

    /**
     *
     * 向redis中添加购物车。
     * 数据类型是hash key：用户id field：商品id value：商品信息
     * @return
     */
    @Override
    public E3Result addCart(long userId, long itemId, int num) {
        //先判断redis中是否有此商品 有的话数量增加
        String str = jedisClient.hget(REDIS_CART_PRE + ":" + userId, itemId + "");
        if(StringUtils.isNotBlank(str)){
            Item item = JsonUtils.jsonToPojo(str, Item.class);
            item.setNum(item.getNum()+num);
            jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "",JsonUtils.objectToJson(item));
            return E3Result.ok();
        }
        //redis没有商品 则查询商品详情  添加到redis中
        Item item = itemMapper.selectByPrimaryKey(itemId);
        item.setNum(num);
        //取第一张图片
        String image = item.getImage();
        if(StringUtils.isNotBlank(image)){
            item.setImage(image.split(",")[0]);
        }
        jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "",JsonUtils.objectToJson(item));
        return E3Result.ok();
    }

    /**
     * 将cookie中的购物车列表合并到redis中
     * @param userId
     * @param itemList
     * @return
     */
    @Override
    public E3Result mergeCart(long userId, List<Item> itemList) {
        //遍历商品列表
        //把列表添加到购物车。
        //判断购物车中是否有此商品
        //如果有，数量相加
        //如果没有添加新的商品
        for(Item item : itemList){
            addCart(userId,item.getId(),item.getNum());
        }
        return E3Result.ok();
    }

    /**
     * 取出redis中的购物车列表
     * @param userId
     * @return
     */
    @Override
    public List<Item> getCartList(long userId) {
        List<String> hvals = jedisClient.hvals(REDIS_CART_PRE + ":" + userId);
        List<Item> list = new ArrayList<>();
        for(String values : hvals){
            Item item = JsonUtils.jsonToPojo(values, Item.class);
            list.add(item);
        }
        return list;
    }

    @Override
    public E3Result updateCartNum(long userId, long itemId, int num) {
        String str = jedisClient.hget(REDIS_CART_PRE + ":" + userId, itemId + "");
        if(StringUtils.isNotBlank(str)) {
            Item item = JsonUtils.jsonToPojo(str, Item.class);
            item.setNum(num);
            jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "",JsonUtils.objectToJson(item));
        }
        return E3Result.ok();
    }

    @Override
    public E3Result deleteCartItem(long userId, long itemId) {
        jedisClient.hdel(REDIS_CART_PRE + ":" + userId,itemId + "");
        return E3Result.ok();
    }

    @Override
    public E3Result clearCartItem(long userId) {
        jedisClient.del(REDIS_CART_PRE + ":" + userId);
        return E3Result.ok();
    }
}
