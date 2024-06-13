package com.noob.framework.config;

import com.noob.framework.module.DemoModule;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DemoProperties.class)
public class DemoAutoConfiguration {

    @Bean
    public DemoModule demoModule(DemoProperties properties){
        DemoModule demoModule = new DemoModule();
        demoModule.setName(properties.getName());
        demoModule.setVersion(properties.getVersion());
        return demoModule;
    }

    @Bean(name = "demoModuleByParam")
    public DemoModule demoModuleByParam(DemoProperties properties){
        System.out.println("demoModuleByParam执行 初始化DemoModule");
        DemoModule demoModule = new DemoModule(properties.getName(), properties.getVersion());
        return demoModule;
    }

}