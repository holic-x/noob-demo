package com.noob.base.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ? super T
 */
public class SuperGenericsDemo {

    // 下界：接收 Integer 及其父类的 List
    public static void addInt(List<? super Integer> list) {
        list.add(1); // 安全写入：Integer 可转为父类 Number/Object
        list.add(2);
    }

    public static void main(String[] args) {

        // 调用时兼容各种父类集合
        addInt(new ArrayList<Integer>());  // List<Integer>
        addInt(new ArrayList<Number>());   // List<Number>
        addInt(new ArrayList<Object>());   // List<Object>

        List<? super Integer> list = new ArrayList<Number>();
        Object obj = list.get(0); // 只能读为 Object，无法直接读为 Number 或 Integer
    }
}
