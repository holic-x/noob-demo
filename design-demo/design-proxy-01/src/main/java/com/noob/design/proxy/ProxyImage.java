package com.noob.design.proxy;

/**
 * 代理主题：实现抽象主题接口
 * 目的：控制对真实图片的访问,例如水印渲染、加密处理等
 */
public class ProxyImage implements Image {

    // 定义被代理对象
    private RealImage realImage = new RealImage();

    @Override
    public void display() {
        realImage.display();
        System.out.println("show proxy image: 输出水印渲染后的图片信息");
    }
}
