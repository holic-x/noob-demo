package com.noob.chain.onlineAudit.rc;

import java.text.SimpleDateFormat;

public class AuthClient {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        // 创建责任链节点
        Level3AuthHandler level3AuthHandler = new Level3AuthHandler();
        Level2AuthHandler level2AuthHandler = new Level2AuthHandler();
        Level1AuthHandler level1AuthHandler = new Level1AuthHandler();

        // 构建责任链关系
        level3AuthHandler.setNextHandler(level2AuthHandler);
        level2AuthHandler.setNextHandler(level1AuthHandler);

        // 模拟测试
        level3AuthHandler.handle(new AuthInfo("1001","1",sdf.parse("2020-05-21 00:00:00")));
        System.out.println("----------");
        level3AuthHandler.handle(new AuthInfo("1002","2",sdf.parse("2020-06-03 00:00:00")));
        System.out.println("----------");
        level3AuthHandler.handle(new AuthInfo("1003","3",sdf.parse("2020-06-18 00:00:00")));
    }
}
