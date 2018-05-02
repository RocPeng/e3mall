package com.liger.controller;

import com.liger.common.model.EasyUIDataGridResult;
import com.liger.common.utils.E3Result;
import com.liger.content.service.ContentService;
import com.liger.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by roc_peng on 2017/8/30.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(@RequestParam("categoryId") long categoryId,
                                               @RequestParam(value = "page",defaultValue = "1") int page,
                                               @RequestParam(value = "rows",defaultValue = "10") int rows){
        return contentService.getContentListByCid(categoryId,page,rows);
    }

    @RequestMapping("/save")
    @ResponseBody
    public E3Result saveContent(Content content){
        E3Result result = contentService.addContent(content);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public E3Result updateContent(Content content){
        E3Result result = contentService.updateContent(content);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public E3Result updateContent(@RequestParam("ids") List<Object> ids){
        E3Result result = contentService.deleteContent(ids);
        return result;
    }

}
