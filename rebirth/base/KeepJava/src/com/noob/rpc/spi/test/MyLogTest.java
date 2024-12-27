package com.noob.rpc.spi.test;

import com.noob.rpc.spi.MyLog;
import com.noob.rpc.spi.impl.ConsoleLog;
import com.noob.rpc.spi.impl.WebLog;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MyLogTest {

    // ① 传统方式引用服务
    public static void testCase1() {
        ConsoleLog consoleLog = new ConsoleLog();
        consoleLog.info("hhh");
        WebLog webLog = new WebLog();
        webLog.info("xxx");
    }

    // ② SPI方式引用服务
    public static void testCase2() {
        // 使用ServiceLoader动态加载指定接口的实现类
        ServiceLoader<MyLog> serviceLoader = ServiceLoader.load(MyLog.class);
        // 借助迭代器获取实现类信息
        Iterator<MyLog> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            MyLog myLog = iterator.next();
            myLog.info("keep...");
        }
    }

    public static void main(String[] args) {
        System.out.println("① 传统方式引用服务");
        testCase1();
        System.out.println("② SPI方式引用服务");
        testCase2();
    }

}
