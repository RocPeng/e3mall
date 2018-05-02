package com.liger.service;

import com.liger.common.model.EasyUITreeNode;

import java.util.List;

/**
 * Created by roc_peng on 2017/8/29.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public interface ItemCatService {
    List<EasyUITreeNode> getCatList(long parentId);
}
