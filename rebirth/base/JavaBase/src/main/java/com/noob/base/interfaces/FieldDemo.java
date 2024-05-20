package com.noob.base.interfaces;


import java.util.Random;

// 接口定义
interface RandVals{
    Random RAND = new Random(47);
    int RANDOM_INT = RAND.nextInt(10);
    long RANDOM_LONG = RAND.nextLong() * 10;
    float RANDOM_FLOAT = RAND.nextLong() * 10;
    double RANDOM_DOUBLE = RAND.nextDouble() * 10;
}

/**
 * 接口字段 demo
 */
public class FieldDemo {
    public static void main(String[] args) {
        // static字段，在类第一次被加载的时候初始化
        System.out.println(RandVals.RANDOM_INT);
        System.out.println(RandVals.RANDOM_LONG);
        System.out.println(RandVals.RANDOM_FLOAT);
        System.out.println(RandVals.RANDOM_DOUBLE);
    }
}
