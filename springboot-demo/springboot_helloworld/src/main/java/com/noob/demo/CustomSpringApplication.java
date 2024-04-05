package com.noob.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 自定义项目启动类
 */
@SpringBootApplication
public class CustomSpringApplication extends SpringApplication {

    public CustomSpringApplication() {
        super();
        // 在这里可以添加自定义的EnvironmentPostProcessor
//        this.addInitializers(new MyCustomInitializer());
//        this.addListeners(new MyCustomListener());
        System.out.println("hello custom springApplication");
    }

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CustomSpringApplication.class);
        builder.bannerMode(Banner.Mode.OFF).run(args);
    }
}
