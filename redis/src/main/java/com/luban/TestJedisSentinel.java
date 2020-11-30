package com.luban;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//redis 哨兵模式集群
public class TestJedisSentinel {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("192.168.0.104:28000");
        set.add("192.168.0.104:28001");
        set.add("192.168.0.104:28002");
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster",set,"123456");
        while (true){
            Jedis jedis = null;
            try {
                jedis = jedisSentinelPool.getResource();
                String s = UUID.randomUUID().toString();
                jedis.set("k"+s,"v"+s);
                System.out.println(jedis.get("k"+s));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                if (jedis != null){
                    jedis.close();
                }
            }
        }
    }
}
