package com.noob.redis.demo;

import redis.clients.jedis.Jedis;

/**
 * Redis Lua 原子性Demo
 */
public class RedisLuaDemo {

    /**
     * Lua对Redis原子性的支持：
     * 在Redis中支持Lua脚本，整个脚本在执行期间会被当做一个整体，不会被其他的客户端命令打断
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        jedis.select(1);
        String luaScript =
                "redis.pcall('SET',KEYS[1],ARGV[1])" +
                        "redis.pcall('SET',KEYS[2],ARGV[2])" +
                        "redis.pcall('SET',KEYS[3],ARGV[3])" +
                        "return 'OK'";
        Object result = jedis.eval(luaScript,3,"key1","key2","key3","value1","value2","value3");
        System.out.println("Result: " + result);
        System.out.println("key1: " + jedis.keys("key1"));
        System.out.println("key2: " + jedis.keys("key2"));
        System.out.println("key3: " + jedis.keys("key3"));
        // 关闭客户端连接
        jedis.close();
    }
}
