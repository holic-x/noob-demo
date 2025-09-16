package com.noob.algorithm.daily.design._1singleton;

/**
 * 饿汉式：在定义的时候就进行初始化
 */
public class Singleton02 {

    private static Singleton02 instance = new Singleton02();

    private Singleton02() {

    }

    public static Singleton02 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Singleton02.getInstance());
        }
    }
}
