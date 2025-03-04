package com.noob.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 方式② ：基于SpringApplication的shutdown hook实现优雅停机
 */
 @SpringBootApplication
public class ShutdownApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ShutdownApplication.class);
        application.setRegisterShutdownHook(true); // 注册 Shutdown Hook
        application.run(args);
    }

}
