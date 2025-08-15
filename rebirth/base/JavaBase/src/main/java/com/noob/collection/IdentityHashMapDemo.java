package com.noob.collection;

import java.util.HashMap;
import java.util.IdentityHashMap;

class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Employee) obj).getName()); // 如果name相同则认为对象是同一个对象
    }
}

/**
 * IdentityHashMap 的引用相等性校验
 */
public class IdentityHashMapDemo {

    public static void main(String[] args) {

        Employee e1 = new Employee("Huh");
        Employee e2 = new Employee("Huh");
        System.out.println(e1.equals(e2)); // true
        System.out.println(e1 == e2); // false

        // 载入identityHashMap集合
        IdentityHashMap identityHashMap = new IdentityHashMap();
        identityHashMap.put(e1, e1);
        identityHashMap.put(e2, e2);
        System.out.println(identityHashMap.size()); // 有2个对象

        // 载入identityHashMap集合
        HashMap hashMap = new HashMap();
        hashMap.put(e1, e1);
        hashMap.put(e2, e2);
        System.out.println(hashMap.size()); // 有1个对象

    }
}
