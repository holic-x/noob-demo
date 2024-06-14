package com.noob.framework.spi.java;

public class TencentVideo implements Video{
    @Override
    public void play() {
        System.out.println("tencent video play");
    }
}
