package com.noob.framework;

import com.noob.framework.application.MyApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Condition;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
         SpringApplication.run(DemoApplication.class, args);
//        SpringApplication app = new SpringApplication(DemoApplication.class);
//        app.addInitializers(new MyApplicationContextInitializer());
//        app.run(args);


    }

}
