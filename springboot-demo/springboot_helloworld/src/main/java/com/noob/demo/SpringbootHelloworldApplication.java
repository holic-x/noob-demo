package com.noob.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class SpringbootHelloworldApplication {

    public static void main(String[] args) {

        // SpringApplication.run(SpringbootHelloworldApplication.class, args);
//        SpringApplication app = new SpringApplication(CustomSpringApplication.class);

        SpringApplication app = new SpringApplication(SpringbootHelloworldApplication.class);
        // 设置横幅关闭
        app.setBannerMode(Banner.Mode.OFF);
        // 启动项目
        app.run(args);
    }

}
