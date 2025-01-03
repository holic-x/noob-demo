package com.noob.rpc.simple.demo.test;

public class DemoServiceImpl implements DemoService {

    public String sayHello(String param) {
        return "hello:" + param;
    }

}