package com.noob.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
//         SpringApplication.run(DemoApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        Jedis jedis = context.getBean(Jedis.class);
        System.out.println(jedis);
    }

    @Bean
     public Jedis jedis() {
        System.out.println("自定义的Jedis对象");
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        return jedis;
     }
}
