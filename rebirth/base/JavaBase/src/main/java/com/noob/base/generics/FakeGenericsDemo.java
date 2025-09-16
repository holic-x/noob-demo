package com.noob.base.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 的伪泛型概念
 */
public class FakeGenericsDemo {

    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(123);

        // 通过反射注入实现添加一个对象
        list.getClass().getMethod("add",Object.class).invoke(list,"aaa"); // 通过反射向list中注入一个字符串"aaa"

        // 打印数据
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }
}
