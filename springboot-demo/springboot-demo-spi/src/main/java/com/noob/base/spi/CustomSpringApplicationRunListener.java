package com.noob.base.spi;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

/**
 * 自定义CustomSpringApplicationRunListener实现SpringApplicationRunListener
 */
public class CustomSpringApplicationRunListener implements SpringApplicationRunListener {

    // 构造函数
//    public CustomSpringApplicationRunListener(SpringApplication application, String[] args) {
//    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("【自定义监听器】应用启动成功！");

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("【自定义监听器】ApplicationContext 准备完毕...");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.contextPrepared(context);
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        System.out.println("【自定义监听器】环境信息准备完毕...");
    }

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("【自定义监听器】应用启动开始...");
    }

}
