package com.noob.base.generics;


/**
 * 泛型类的类型嵌套
 */
public class NestGenericClass {
    public static void main(String[] args) {
        Info<Integer> info = new Info<>(10);
        MyMap<String,Info<Integer>> myMap = new MyMap<String,Info<Integer>>("hello",info);
        System.out.println(myMap);
    }
}
