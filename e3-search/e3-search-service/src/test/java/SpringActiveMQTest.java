import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by roc_peng on 2017/9/1.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class SpringActiveMQTest {
    @Autowired
    private JmsTemplate jmsTemplate;
    //这个是队列目的地，点对点的
    @Autowired
    ActiveMQQueue activeMQQueue;
    //主题目的地，一对多的
    @Autowired
    ActiveMQTopic activeMQTopic;


    @Test
    public void Test() throws Exception{

    }

    @Test
    public void testSpringActiveMq() throws Exception{
        jmsTemplate.send(activeMQQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("Hello,spring整合的Activemq!!!");
                return textMessage;
            }
        });
    }

    @Test
    public void testSpringActiveMq2() throws Exception{
        jmsTemplate.send(activeMQTopic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("Hello,spring整合的Activemq!!!");
                return textMessage;
            }
        });
    }

    @Test
    public void aeertTest(){
//        Assert.assertEquals(22l,212l);
    }

}
