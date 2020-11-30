
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.Map;

public class TestJedis {

    private Jedis jedis;

    @Before
    public void initJedis(){
        jedis = new Jedis("127.0.0.1", 6379);
    }

    @Test
    public void testKey(){
        //keys 正式环境不能使用 数据量大了会造成线程阻塞
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.exists("k1"));
        //设置key 过期时间
        jedis.expire("k1",60);
        //还剩多久过期
        System.out.println(jedis.ttl("k1"));
        //类型
        System.out.println(jedis.type("k1"));

        //i++
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.decr("count"));
        System.out.println(jedis.decr("count"));
        System.out.println(jedis.decr("count"));

        System.out.println(jedis.incrBy("count",2));
        System.out.println(jedis.incrBy("count",2));
        System.out.println(jedis.decrBy("count",2));
        System.out.println(jedis.decrBy("count",2));
    }

    @Test
    public void testString(){
        //key 存在覆盖
        System.out.println(jedis.set("k1","v2"));
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.del("k1"));

        //追加，返回字符串长度
        System.out.println(jedis.append("k1","v1"));
        System.out.println(jedis.append("k1","v1"));
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.strlen("k1"));

        //批量set 和多个set区别在于 多个set多次请求 mset是一次
        System.out.println(jedis.mset("k1","v1","k2","v2","k3","v3"));
        System.out.println(jedis.mget("k1","k2","k3"));

        //如果存在不赋值
        System.out.println(jedis.setnx("k1","kkkkkkk"));
        System.out.println(jedis.get("k1"));

        System.out.println(jedis.setex("k9",60,"v9"));
        System.out.println(jedis.ttl("k9"));

        System.out.println("====================");
        SetParams setParams = new SetParams();
        setParams.ex(100);
        setParams.nx();

        System.out.println(jedis.set("k11","k11",setParams));
        System.out.println(jedis.get("k11"));
    }

    @Test
    public void testList(){

        //左&右进栈 有序
        System.out.println(jedis.lpush("lpush","v1","v2","v3","v4"));
        System.out.println(jedis.rpush("rpush","v1","v2","v3","v4"));
        System.out.println(jedis.lrange("lpush",0,-1));
        System.out.println(jedis.lrange("rpush",0,-1));

        //从尾取
        System.out.println(jedis.rpop("lpush"));
        System.out.println(jedis.rpop("rpush"));

        //从头取
        System.out.println(jedis.lpop("lpush"));
        System.out.println(jedis.lpop("rpush"));
    }


    @Test
    public void testSet(){
        //set 无序
        jedis.sadd("set1","v1","v2","v3","v4","v5","v6","v7");
        //查询 无序
        System.out.println(jedis.smembers("set1"));
        //是否存在 无序
        System.out.println(jedis.sismember("set1","v1"));

        jedis.sadd("set2","v1","v2","v3","v4","v11","v12");

        //set1 差集
        System.out.println(jedis.sdiff("set1","set2"));
        //并集
        System.out.println(jedis.sinter("set1","set2"));
        //交集
        System.out.println(jedis.sunion("set1","set2"));    }

        @Test
        public void testMap(){
            Map<String,String> map = new HashMap();
            map.put("k1","v1");
            map.put("k2","v2");
            map.put("k3","v3");
            System.out.println(jedis.hset("hash",map));
            System.out.println(jedis.hkeys("hash"));
            System.out.println(jedis.hvals("hash"));
            System.out.println(jedis.hgetAll("hash"));
        }
        //zset 有序
        //scan 查询 分页返回（keys 所有）

}
