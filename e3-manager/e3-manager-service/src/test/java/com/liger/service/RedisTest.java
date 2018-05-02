package com.liger.service;

import com.liger.common.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by roc_peng on 2017/8/30.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public class RedisTest {

    @Test
    public void Test() throws Exception{

    }

    @Test
    public void jedisTest() throws Exception{
        Jedis jedis = new Jedis("172.16.193.129", 6379);
        String hello = jedis.get("hello");
        System.out.println(hello);
        jedis.set("hello","Roc_Peng");
        String hello2 = jedis.get("hello");
        System.out.println(hello2);
        jedis.close();
    }
    /**
     * 单机版连接池
     */
    @Test
    public void jedisPoolTest() throws Exception{
        JedisPool jedisPool = new JedisPool("172.16.193.129", 6379);
        Jedis jedis = jedisPool.getResource();
        String hello = jedis.get("hello");
        System.out.println(hello);
        jedis.close();
        jedisPool.close();
    }

    /**
     * 集群
     */
    @Test
    public void jedisClusterTest() throws Exception{
        // 第一步：使用JedisCluster对象。需要一个Set<HostAndPort>参数。Redis节点的列表。
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("172.16.193.129",7002));
        nodes.add(new HostAndPort("172.16.193.129",7001));
        nodes.add(new HostAndPort("172.16.193.129",7003));
        nodes.add(new HostAndPort("172.16.193.129",7004));
        nodes.add(new HostAndPort("172.16.193.129",7005));
        nodes.add(new HostAndPort("172.16.193.129",7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        // 第二步：直接使用JedisCluster对象操作redis。在系统中单例存在。
        jedisCluster.set("world","Alice");
        String world = jedisCluster.get("world");
        System.out.println(world);
        jedisCluster.close();
    }

    @Test
    public void testJedisClient() throws Exception {
        //初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("first", "100");
        String result = jedisClient.get("first");
        System.out.println(result);
    }

    @Test
    public void testJedisTest() throws Exception{
        //初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("cookie-aaa","111");
        jedisClient.set("cookie-bbb","2222");
        jedisClient.set("cookie-ccc","33");
    }

    @Test
    public void testJedisWriteTest() throws Exception{
        //初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        System.out.println(jedisClient.get("cookie-aaa"));
        System.out.println(jedisClient.get("cookie-bbb"));
        System.out.println(jedisClient.get("cookie-ccc"));
    }

    @Test
    public void testJedisWriteTest2() throws Exception{
        //初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        JedisPool jedisPool = applicationContext.getBean(JedisPool.class);
//        jedisClient.set("cookie*","");
//        jedisClient.del("cookie*");
        Jedis jedis = jedisPool.getResource();
        Set<String> keys = jedis.keys("cookie*");
        System.out.println(keys);
    }

}
