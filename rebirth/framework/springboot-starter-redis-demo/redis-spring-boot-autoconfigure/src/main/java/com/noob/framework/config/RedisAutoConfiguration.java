package com.noob.framework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

// 配置类
@Configuration
@EnableConfigurationProperties(RedisProperties.class)// 将RedisProperties在启动时自动注册到SpringIOC容器中
@ConditionalOnClass(Jedis.class)// 限定条件：只有引入Jedis依赖（加载了Jedis字节码文件）才装配配置
public class RedisAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(name = {"jedis"})
    // 提供Jedis的Bean（将RedisProperties通过参数的方式注入进来，即可动态根据配置生成Jedis链接）
     public Jedis  jedis(RedisProperties redisProperties){
        System.out.println("RedisAutoConfiguration 装配，注册Jedis对象");
         // return new Jedis("localhost",6379);

        // 如果远程服务器设定了auth，则必须指定密码，否则链接错误
        Jedis jedis = new Jedis(redisProperties.getHost(),redisProperties.getPort());
        String password = redisProperties.getPassword();
        if(!StringUtils.isEmpty(password)){
            jedis.auth(redisProperties.getPassword());
        }
        return jedis;
     }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("43.138.185.20",6379);
        jedis.auth("123456");
        System.out.println(jedis);
        jedis.set("hello","hello world");
        System.out.println(jedis.get("hello"));
    }

}
