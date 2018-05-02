package com.liger.controller;

import com.liger.common.model.EasyUITreeNode;
import com.liger.common.utils.E3Result;
import com.liger.content.service.ContentCategoryService;
import com.liger.model.ContentCategory;
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
 * Description 内容管理controller
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        List<EasyUITreeNode> contentCatList = contentCategoryService.getContentCatList(parentId);
        return contentCatList;
    }

    @RequestMapping("/create")
    @ResponseBody
    public E3Result createContentCategory(long parentId, String name){
        E3Result result = contentCategoryService.addContentCategory(parentId, name);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public E3Result updateContentCategory(long id,String name){
        E3Result result = contentCategoryService.updateContentCategory(id, name);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public E3Result deleteContentCategory(long id){
        E3Result result = contentCategoryService.deleteContentCategory(id);
        return result;
    }


}
