package com.liger.cart.service;

import com.liger.common.utils.E3Result;
import com.liger.model.Item;

import java.util.List;

/**
 * Created by roc_peng on 2017/9/3.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public interface CartService {
    E3Result addCart(long userId, long itemId, int num);
    E3Result mergeCart(long userId, List<Item> itemList);
    List<Item> getCartList(long userId);
    E3Result updateCartNum(long userId, long itemId, int num);
    E3Result deleteCartItem(long userId, long itemId);
    E3Result clearCartItem(long userId);
}
