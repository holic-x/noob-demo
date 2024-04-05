package com.noob.demo.config;

import com.noob.demo.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConfigA.class)
public class ConfigB {
    @Bean
    public Person get() {
        return new Person();
    }
}