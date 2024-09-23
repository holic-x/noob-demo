package com.noob.design.proxy;

/**
 * 客户端访问
 */
public class Client {

    public static void main(String[] args) {
        Image proxy = new ProxyImage();
        proxy.display();
    }

}
