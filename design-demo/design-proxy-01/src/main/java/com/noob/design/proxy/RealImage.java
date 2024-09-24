package com.noob.design.proxy;

/**
 * 真实主题：实现抽象主题接口
 */
public class RealImage implements Image {
    @Override
    public void display() {
        System.out.println("show real image");
    }
}
