package com.liger.item.listener;

import com.liger.model.Item;
import com.liger.model.ItemDesc;
import com.liger.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roc_peng on 2017/9/2.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public class HtmlGenListener implements MessageListener {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private ItemService itemService;
    @Value("${HTML_GEN_PATH}")
    private String HTML_GEN_PATH;

    @Override
    public void onMessage(Message message) {
        try {
            //先从消息队列中获取id
            TextMessage msg = (TextMessage) message;
            String text = msg.getText();
            System.out.println("HtmlGenListener接收到的消息:"+text);
            Long itemId = new Long(text);
            //等待事务提交
            Thread.sleep(1000);
            //查找item
            Item item = itemService.getItemById(itemId);
            //生成freemarker静态页面
            com.liger.item.model.Item item1 = new com.liger.item.model.Item(item);
            ItemDesc itemDesc = itemService.getItemDescById(itemId);
            //创建一个数据集，把商品数据封装
            Map data = new HashMap<>();
            data.put("item", item1);
            data.put("itemDesc", itemDesc);
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            FileWriter fileWriter = new FileWriter(new File(HTML_GEN_PATH+itemId+".html"));
            template.process(data,fileWriter);
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
