package com.noob.base.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ? extends T
 */
public class ExtendGenericsDemo {

    // 上界：接收 Number 及其子类的 List
    public static void printSum(List<? extends Number> list) {
        double sum = 0;
        for (Number num : list) {
            sum += num.doubleValue(); // 安全读取：子类对象可转为父类 Number
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {

        // 调用时兼容各种子类集合
        printSum(Arrays.asList(1, 2, 3)); // List<Integer>
        printSum(Arrays.asList(1.5, 2.5)); // List<Double>

        List<? extends Number> list = new ArrayList<Integer>();
        // list.add(1); // 编译报错！无法确定 list 实际接收的是 Integer 还是 Double

    }
}
