package com.liger.search.message;

import com.liger.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by roc_peng on 2017/9/1.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 监听商品添加消息，接收消息后，将对应的商品信息同步到索引库
 */

public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private SearchItemService searchItemService;

    @Override
    public void onMessage(Message message) {
        try {
            //消息中取出id
            TextMessage msg = (TextMessage) message;
            String text = msg.getText();
            System.out.println("ItemListener接收到的消息:"+text);
            Long id = new Long(text);

            searchItemService.addDocument(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
