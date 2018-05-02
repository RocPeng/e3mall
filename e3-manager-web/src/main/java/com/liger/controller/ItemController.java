package com.liger.controller;

import com.liger.common.model.EasyUIDataGridResult;
import com.liger.common.model.EasyUITreeNode;
import com.liger.common.utils.E3Result;
import com.liger.model.Item;
import com.liger.model.ItemDesc;
import com.liger.service.ItemCatService;
import com.liger.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by roc_peng on 2017/8/28.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public E3Result getItemById(@PathVariable("itemId") Long itemId){
        Item item = itemService.getItemById(itemId);
        E3Result ok = E3Result.ok(item);
        return ok;
    }

    @RequestMapping("/desc/{itemDescId}")
    @ResponseBody
    public E3Result getItemDescById(@PathVariable("itemDescId") Long itemDescId){
        ItemDesc itemDesc = itemService.getItemDescById(itemDescId);
        E3Result ok = E3Result.ok(itemDesc);
        return ok;
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        EasyUIDataGridResult itemList = itemService.getItemList(page, rows);
        return itemList;
    }

    @RequestMapping("/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        List<EasyUITreeNode> catList = itemCatService.getCatList(parentId);
        return catList;
    }

    @RequestMapping("/save")
    @ResponseBody
    public E3Result saveItem(Item item, String desc){
        E3Result e3Result = itemService.addItem(item, desc);
        return e3Result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public E3Result updateItem(Item item, String desc){
        E3Result e3Result = itemService.updateItem(item, desc);
        return e3Result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public E3Result deleteItems(@RequestParam("ids") List<Long> ids){
        E3Result e3Result = itemService.deleteItems(ids);
        return e3Result;
    }

    /**
     * 上架
     * @param ids
     * @return
     */
    @RequestMapping("/reshelf")
    @ResponseBody
    public E3Result reshelfItems(@RequestParam("ids") List<Long> ids){
        E3Result e3Result = itemService.reshelfItems(ids);
        return e3Result;
    }

    /**
     * 下架
     * @param ids
     * @return
     */
    @RequestMapping("/instock")
    @ResponseBody
    public E3Result instockItems(@RequestParam("ids") List<Long> ids){
        E3Result e3Result = itemService.instockItems(ids);
        return e3Result;
    }

}
