package com.liger.content.service;

import com.liger.common.model.EasyUIDataGridResult;
import com.liger.common.utils.E3Result;
import com.liger.model.Content;

import java.util.List;

/**
 * Created by roc_peng on 2017/8/30.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public interface ContentService {
    E3Result addContent(Content content);
    E3Result updateContent(Content content);
    E3Result deleteContent(List<Object> ids);
    EasyUIDataGridResult getContentListByCid(long cid, int page, int rows);
    List<Content> getContentList(long cid);
}
