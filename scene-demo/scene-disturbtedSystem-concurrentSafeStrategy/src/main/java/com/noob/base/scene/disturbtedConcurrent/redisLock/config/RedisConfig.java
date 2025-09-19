package com.noob.base.scene.disturbtedConcurrent.redisLock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
// 确保该类被Spring扫描到（包路径需在启动类的扫描范围内）
public class RedisConfig {

    /**
     * 定义RedisTemplate的Bean，用于注入到RedisDistributedLock中
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        // 创建RedisTemplate实例
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        
        // 设置连接工厂（由Spring自动注入）
        redisTemplate.setConnectionFactory(connectionFactory);
        
        // 配置Key的序列化器（字符串序列化，避免key乱码）
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        
        // 配置Value的序列化器（JSON序列化，支持对象存储）
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        
        // 初始化配置
        redisTemplate.afterPropertiesSet();
        
        return redisTemplate;
    }
}
