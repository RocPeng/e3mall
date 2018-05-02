package com.liger.content.service;

import com.liger.common.model.EasyUITreeNode;
import com.liger.common.utils.E3Result;

import java.util.List;

/**
 * Created by roc_peng on 2017/8/30.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(long parentId);
    E3Result addContentCategory(long parentId, String name);
    E3Result updateContentCategory(long id, String name);
    E3Result deleteContentCategory(long id);
}
