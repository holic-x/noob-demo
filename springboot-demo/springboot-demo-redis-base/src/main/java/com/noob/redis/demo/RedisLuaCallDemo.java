package com.noob.redis.demo;

import redis.clients.jedis.Jedis;

/**
 * Redis Lua 原子性Demo （ call()命令调用 ）
 */
public class RedisLuaCallDemo {

    /**
     * Lua对Redis原子性的支持：
     * 在Redis中支持Lua脚本，整个脚本在执行期间会被当做一个整体，不会被其他的客户端命令打断
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        jedis.select(1);
        String luaScript =
                "redis.call('SET', 'ckey1', 'value1')" +
                        "redis.call('INCRBY', 'ckey2', 1/0)" + // 设置错误指令
                        "redis.call('SET', 'ckey3', 'value3')" +
                        "return 'OK'";
        Object result = jedis.eval(luaScript,0);
        System.out.println("Result: " + result);
        // 关闭客户端连接
        jedis.close();
    }
}
