package com.noob.base.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型擦除 demo
 */
public class GenericsErasureTypeDemo {
    public static void main(String[] args) {
        List<Object> list1 = new ArrayList<Object>();
        List<String> list2 = new ArrayList<String>();
        System.out.println(list1.getClass());
        System.out.println(list2.getClass());
    }
}
