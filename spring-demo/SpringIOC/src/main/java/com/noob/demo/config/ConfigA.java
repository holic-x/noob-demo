package com.noob.demo.config;

import com.noob.demo.model.User;
import org.springframework.context.annotation.Bean;

public class ConfigA {
    @Bean
    public User get() {
        return new User();
    }
}
