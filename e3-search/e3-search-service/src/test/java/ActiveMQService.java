import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * Created by roc_peng on 2017/9/1.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public class ActiveMQService {

    @Test
    public void Test() throws Exception{

    }

    /**
     *
     1.1.	Queue消息传递模型：点对点（point-to-point，简称PTP）
     */
    /**
     * 第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
     第二步：使用ConnectionFactory对象创建一个Connection对象。
     第三步：开启连接，调用Connection对象的start方法。
     第四步：使用Connection对象创建一个Session对象。
     第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。
     第六步：使用Session对象创建一个Producer对象。
     第七步：创建一个Message对象，创建一个TextMessage对象。
     第八步：使用Producer对象发送消息。
     第九步：关闭资源。
     * @throws Exception
     */
    @Test
    public void testQueueProducer() throws Exception{
        // 第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
        //brokerURL服务器的ip及端口号
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        // 第二步：使用ConnectionFactory对象创建一个Connection对象。
        Connection connection = connectionFactory.createConnection();
        // 第三步：开启连接，调用Connection对象的start方法。
        connection.start();
        // 第四步：使用Connection对象创建一个Session对象。
        //第一个参数：是否开启事务。true：开启事务，第二个参数忽略。
        //第二个参数：当第一个参数为false时，才有意义。消息的应答模式。1、自动应答2、手动应答。一般是自动应答。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Queue对象。
        //参数：队列的名称。
        Queue queue = session.createQueue("test-queue");
        // 第六步：使用Session对象创建一个Producer对象。
        MessageProducer producer = session.createProducer(queue);
        // 第七步：创建一个Message对象，创建一个TextMessage对象。
        TextMessage textMessage = session.createTextMessage("hello activeMQ,this is my first test!!!");
        // 第八步：使用Producer对象发送消息。
        producer.send(textMessage);
        // 第九步：关闭资源。
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * 消费者：接收消息。
     第一步：创建一个ConnectionFactory对象。
     第二步：从ConnectionFactory对象中获得一个Connection对象。
     第三步：开启连接。调用Connection对象的start方法。
     第四步：使用Connection对象创建一个Session对象。
     第五步：使用Session对象创建一个Destination对象。和发送端保持一致queue，并且队列的名称一致。
     第六步：使用Session对象创建一个Consumer对象。
     第七步：接收消息。
     第八步：打印消息。
     第九步：关闭资源
     */
    @Test
    public void testQueueConsumer() throws Exception{
        // 第一步：创建一个ConnectionFactory对象。
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        // 第二步：从ConnectionFactory对象中获得一个Connection对象
        Connection connection = connectionFactory.createConnection();
        // 第三步：开启连接。调用Connection对象的start方法
        connection.start();
        // 第四步：使用Connection对象创建一个Session对象。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 第五步：使用Session对象创建一个Destination对象。和发送端保持一致queue，并且队列的名称一致。
        Queue queue = session.createQueue("test-queue");
        // 第六步：使用Session对象创建一个Consumer对象。
        MessageConsumer consumer = session.createConsumer(queue);
        // 第七步：接收消息。
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    // 第八步：打印消息
                    System.out.println("接收到消息:"+text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });
        //等待键盘输入
        System.in.read();
        // 第九步：关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
    @Test
    public void testQueueConsumer2() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("接收到消息:"+text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

    /**
     * 1.1.	Topic 发布/订阅（publish/subscribe，简称pub/sub）
     */
    /**
     * 使用步骤：
     第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
     第二步：使用ConnectionFactory对象创建一个Connection对象。
     第三步：开启连接，调用Connection对象的start方法。
     第四步：使用Connection对象创建一个Session对象。
     第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Topic对象。
     第六步：使用Session对象创建一个Producer对象。
     第七步：创建一个Message对象，创建一个TextMessage对象。
     第八步：使用Producer对象发送消息。
     第九步：关闭资源。
     * @throws Exception
     */
    @Test
    public void testTopicProducer() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic-test");
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("Hello activeMQ,this is my first topic-test");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * 消费者：接收消息。
     第一步：创建一个ConnectionFactory对象。
     第二步：从ConnectionFactory对象中获得一个Connection对象。
     第三步：开启连接。调用Connection对象的start方法。
     第四步：使用Connection对象创建一个Session对象。
     第五步：使用Session对象创建一个Destination对象。和发送端保持一致topic，并且话题的名称一致。
     第六步：使用Session对象创建一个Consumer对象。
     第七步：接收消息。
     第八步：打印消息。
     第九步：关闭资源
     * @throws Exception
     */
    @Test
    public void testTopicConsumer() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic-test");
        MessageProducer producer = session.createProducer(topic);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage message1 = (TextMessage) message;
                    String text = message1.getText();
                    System.out.println(text);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
//        System.in.read();
        Thread.sleep(10000);
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testTopicConsumer2() throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic-test");
        MessageProducer producer = session.createProducer(topic);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage message1 = (TextMessage) message;
                    String text = message1.getText();
                    System.out.println(text);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
//        System.in.read();
        Thread.sleep(10000);
        producer.close();
        session.close();
        connection.close();
    }


}
