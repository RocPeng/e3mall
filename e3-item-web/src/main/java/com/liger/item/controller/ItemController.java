package com.liger.item.controller;

import com.liger.model.Item;
import com.liger.model.ItemDesc;
import com.liger.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by roc_peng on 2017/9/1.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 商品详情展示页
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable("itemId") Long itemId, Model model){
        Item itemById = itemService.getItemById(itemId);
        com.liger.item.model.Item item = new com.liger.item.model.Item(itemById);
        //取商品描述信息
        ItemDesc itemDesc = itemService.getItemDescById(itemId);
        //把信息传递给页面
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", itemDesc);
        return "item";
    }
}
