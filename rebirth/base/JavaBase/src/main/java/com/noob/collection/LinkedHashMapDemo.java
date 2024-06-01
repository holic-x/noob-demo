package com.noob.collection;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * LinkedHashMap
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> defaultMap = new LinkedHashMap<>();
        defaultMap.put(1, "JAVA");
        defaultMap.put(2, "PYTHON");
        defaultMap.put(5, "C");
        defaultMap.put(3, "GO");
        defaultMap.put(4, "C++");
        defaultMap.put(3, "GO LANG");
        // 迭代器遍历数据
        Iterator it1 = defaultMap.entrySet().iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }

        System.out.println("-------------------");

        LinkedHashMap<Integer, String> lruMap = new LinkedHashMap<>(16, 0.75f, true);
        lruMap.put(1, "JAVA");
        lruMap.put(2, "PYTHON");
        lruMap.put(3, "GO");
        lruMap.put(4, "C++");
        lruMap.put(5, "C");
        // 迭代器遍历数据
        Iterator it2 = lruMap.entrySet().iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
        System.out.println("------校验LRU机制------");
        lruMap.put(3, "GO LANG");
        Iterator it3 = lruMap.entrySet().iterator();
        while (it3.hasNext()) {
            System.out.println(it3.next());
        }

    }

}
