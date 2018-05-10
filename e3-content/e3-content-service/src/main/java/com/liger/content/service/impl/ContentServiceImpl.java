package com.liger.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liger.common.model.EasyUIDataGridResult;
import com.liger.common.utils.E3Result;
import com.liger.content.service.ContentService;
import com.liger.mapper.TbContentMapper;
import com.liger.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by roc_peng on 2017/8/30.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;

    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

    @Override
    public E3Result addContent(Content content) {
        //补全属性
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insertSelective(content);
        return E3Result.ok();
    }

    @Override
    public E3Result updateContent(Content content) {
        contentMapper.updateByPrimaryKeySelective(content);
        return E3Result.ok();
    }

    @Override
    public E3Result deleteContent(List<Object> ids) {
        Example example = new Example(Content.class);
        example.createCriteria().andIn("id",ids);
        contentMapper.deleteByExample(example);
        return E3Result.ok();
    }

    /**
     * 分页查询
     * @param cid
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getContentListByCid(long cid, int page, int rows) {
        PageHelper.startPage(page,rows);
        Example example = new Example(Content.class);
        example.createCriteria().andEqualTo("categoryId",cid);
        List<Content> contents = contentMapper.selectByExample(example);
        //获取contents长度
        PageInfo pageContent = new PageInfo<>(contents);
        long total = pageContent.getTotal();
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(total);
        result.setRows(contents);
        return result;
    }

    @Override
    public List<Content> getContentList(long cid) {
        //redis无缓存 则先查询 再存储
        Example example = new Example(Content.class);
        example.createCriteria().andEqualTo("categoryId",cid);
        return contentMapper.selectByExample(example);
    }

}
