package com.noob.base.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 泛型方法基础
class GenericMethod{
    public static <T> void printClass(T obj) {
        System.out.println(obj.getClass().toString());
    }

    public static void main(String[] args) {
        // 泛型方法基础
        printClass("abc");
        printClass(10);
    }
}

// 可变参数泛型方法
 class GenericVarargsMethod{
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<T>();
        Collections.addAll(result, args);
        return result;
    }

    public static void main(String[] args) {
        List<String> ls = makeList("A");
        System.out.println(ls);
        ls = makeList("A", "B", "C");
        System.out.println(ls);
    }
}



/**
 * 泛型方法
 */
public class GenericMethodDemo {
}
