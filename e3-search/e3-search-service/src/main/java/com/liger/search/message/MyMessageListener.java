package com.liger.search.message;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by roc_peng on 2017/9/1.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        //取消息内容
        TextMessage msg = (TextMessage) message;
        try {
            String text = msg.getText();
            System.out.println("接收到消息:"+text);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
