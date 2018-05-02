package com.liger.portal.controller;

import com.liger.content.service.ContentService;
import com.liger.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by roc_peng on 2017/9/1.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Controller
public class IndexController {
    @Value("${CONTENT_LUNBO_ID}")
    private Long CONTENT_LUNBO_ID;
    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        //查询内容列表
        List<Content> ad1List = contentService.getContentList(CONTENT_LUNBO_ID);
        // 把结果传递给页面
        model.addAttribute("ad1List", ad1List);
        return "index";
    }

}
