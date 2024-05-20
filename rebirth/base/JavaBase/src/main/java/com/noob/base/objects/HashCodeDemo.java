package com.noob.base.objects;

/**
 * HashCode demo
 */
public class HashCodeDemo {
    public static void main(String[] args) {
        // 两个相等的对象hashCode一定相等；
        String s1 = "hello";
        String s2 = "hello world";
        String s3 = "hello world";
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());

        // 两个不同的对象hashCode也可能相同
         String s4 = "Aa";
         String s5 = "BB";
        System.out.println(s4.hashCode());
        System.out.println(s5.hashCode());
    }
}
