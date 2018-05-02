package com.liger.service;

import com.liger.common.model.EasyUIDataGridResult;
import com.liger.common.utils.E3Result;
import com.liger.model.Item;
import com.liger.model.ItemDesc;

import java.util.List;

/**
 * Created by roc_peng on 2017/8/28.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public interface ItemService {
    Item getItemById(long id);
    ItemDesc getItemDescById(long id);
    EasyUIDataGridResult getItemList(int page, int rows);
    E3Result addItem(Item item, String desc);
    E3Result updateItem(Item item, String desc);
    E3Result deleteItems(List<Long> ids);
    E3Result reshelfItems(List<Long> ids);
    E3Result instockItems(List<Long> ids);
}
