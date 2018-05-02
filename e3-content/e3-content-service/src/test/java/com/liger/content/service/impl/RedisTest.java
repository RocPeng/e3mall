package com.liger.content.service.impl;

import org.junit.Test;
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
        nodes.add(new HostAndPort("172.16.193.129",7001));
        nodes.add(new HostAndPort("172.16.193.129",7002));
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

}
