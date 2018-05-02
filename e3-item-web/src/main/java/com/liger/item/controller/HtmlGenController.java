package com.liger.item.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

/**
 * Created by roc_peng on 2017/9/2.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Controller
public class HtmlGenController {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/genhtml")
    @ResponseBody
    public String  getHtml() throws Exception{
        // 1、从spring容器中获得FreeMarkerConfigurer对象。
        // 2、从FreeMarkerConfigurer对象中获得Configuration对象
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        // 3、使用Configuration对象获得Template对象
        Template template = configuration.getTemplate("hello.ftl");
        // 4、创建数据集
        HashMap data = new HashMap();
        data.put("hello","1000");
        // 5、创建输出文件的Writer对象
        FileWriter fileWriter = new FileWriter(new File("/Users/roc_peng/Downloads/spring-freemarker.html"));
        // 6、调用模板对象的process方法，生成文件。
        template.process(data,fileWriter);
        //关闭
        fileWriter.close();
        return "ok";
    }
}
