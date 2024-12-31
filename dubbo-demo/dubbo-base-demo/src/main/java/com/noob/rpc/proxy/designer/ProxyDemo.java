package com.noob.rpc.proxy.designer;

/**
 * 设计模式之代理模式测试
 */
public class ProxyDemo {

    public static void main(String[] args) {
        // Proxy proxy = new Proxy();
        Subject proxy = new Proxy();
        proxy.operation();
    }

}
