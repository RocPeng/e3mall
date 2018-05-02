package com.liger.search.service;

import com.liger.common.utils.E3Result;

/**
 * Created by roc_peng on 2017/8/31.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public interface SearchItemService {
    E3Result importAllItems();
    E3Result addDocument(long itemId) throws Exception;
}
