package com.noob.scene.otherService;

import org.springframework.stereotype.Component;

/**
 * 数据库服务
 */
@Component
public class DBService {

    public int update() {
        System.out.println("模拟DB操作");
        return 1;
    }

}