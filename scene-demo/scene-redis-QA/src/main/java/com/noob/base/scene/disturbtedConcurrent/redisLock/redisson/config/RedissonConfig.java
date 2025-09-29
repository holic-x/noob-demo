package com.noob.base.scene.disturbtedConcurrent.redisLock.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        // 创建配置对象
        Config config = new Config();

        // 单节点 Redis 配置（最常见的方式）
        // 这里替换为你的 Redis 服务器地址和端口
        config.useSingleServer()
                 .setAddress("redis://127.0.0.1:6379")
                // 连接超时时间
                .setConnectTimeout(5000)
                // Redis 密码（如果没有密码可以省略）
                .setPassword(".......")
                // 数据库编号（默认是0）
                .setDatabase(0);

        // 如果是 Redis 集群，使用下面的配置
        // config.useClusterServers()
        //       .addNodeAddress("redis://127.0.0.1:7001", "redis://127.0.0.1:7002")
        //       .setPassword("yourRedisPassword");

        // 创建并返回 Redisson 客户端实例
        return Redisson.create(config);
    }
}