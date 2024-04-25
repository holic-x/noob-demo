package com.noob.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {

    /**
     * 使用 @Value设置依赖注入的属性
     * 1.除了可以写硬编码值
     * 2.还可以写${}  、#{}从配置文件中动态注入
     */
    // @Value("#{role.name}")
    private String name;

    public User() {
        System.out.println("User已加载");
    }

    private void initByConfig() {
        System.out.println("User初始化");
    }

    private void destroyByConfig() {
        System.out.println("User销毁");
    }
}
