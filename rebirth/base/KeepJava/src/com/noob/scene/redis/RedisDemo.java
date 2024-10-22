package com.noob.scene.redis;


/**
 * 缓存击穿解决方案：通过双检锁避免缓存击穿问题
 */
public class RedisDemo {

    public static String getByRedis(String key){
        return null; // 模拟从Redis数据库中获取数据
    }

    public static String getVal(String key){
        /**
         * 双检锁：解决懒加载的线程安全问题，此处用于避免缓存击穿问题
         * 互斥锁、重建缓存：即只有在Redis缓存不存在的时候才进行缓存重建，且这个过程中只能有1个线程才能重建缓存
         */

        // 模拟从缓存中获取数据
        String cacheVal = getByRedis(key);

        // 判断Redis缓存是否存在，存在则直接返回，如果不存在则重建缓存
        if(cacheVal==null){
            // Redis缓存不存在，则需重建缓存（引入同步块确保只有一个线程进行重建）
            synchronized(RedisDemo.class){
                cacheVal = getByRedis(key); // 第2次检查：避免在通过第1道检查和进入同步代码块之间已经完成缓存重建了,需要再次确认最新的缓存数据是否存在
                if(cacheVal==null){
                    // ..... 模拟缓存重建:查找MySQL数据库，并回写Redis
                    String val = "xxx"; // mod getByMySQL 并回写Redis
                    return val; // 返回数据
                }else{
                    // 如果第二次检查发现缓存已经重建好则直接返回
                    return cacheVal;
                }
            }
        }else{
            // 缓存数据存在则直接返回
            return cacheVal;
        }
    }
}
