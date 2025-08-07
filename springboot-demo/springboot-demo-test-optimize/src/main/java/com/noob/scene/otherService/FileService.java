package com.noob.scene.otherService;

import org.springframework.stereotype.Component;

/**
 * 文件服务
 */
@Component
public class FileService {

    public String getFile() {
        System.out.println("模拟文件获取操作");
        return "文件信息";
    }

}