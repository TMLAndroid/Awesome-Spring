package com.luban;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TestRedisClusterScan {
    public static void main(String[] args) {
        Set<HostAndPort> nodesList = new HashSet<>();
        nodesList.add(new HostAndPort("192.168.0.106",7000));
        nodesList.add(new HostAndPort("192.168.0.106",7001));
        nodesList.add(new HostAndPort("192.168.0.106",7002));
        nodesList.add(new HostAndPort("192.168.0.106",7003));
        nodesList.add(new HostAndPort("192.168.0.106",7004));
        nodesList.add(new HostAndPort("192.168.0.106",7005));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲连接数 默认8个
        jedisPoolConfig.setMaxIdle(200);
        //最大连接数 默认8
        jedisPoolConfig.setMaxTotal(1000);
        //最小空闲连接 默认0
        jedisPoolConfig.setMinIdle(100);

        jedisPoolConfig.setTestOnBorrow(false);
        JedisCluster jedisCluster = new JedisCluster(nodesList, 100,100,100,"123456",jedisPoolConfig);

        while (true){

            try {

                String s = UUID.randomUUID().toString();
                jedisCluster.set("k"+s,"v"+s);
                System.out.println(jedisCluster.get("k"+s));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                if (jedisCluster != null){
                    jedisCluster.close();
                }
            }
        }
    }


}
