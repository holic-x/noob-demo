package com.noob.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SpringbootRetryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRetryDemoApplication.class, args);
    }

}
