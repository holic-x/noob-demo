package com.noob.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * fail-safe 机制（多线程并发操作集合的一种失败处理机制）
 */
public class FailSafeDemo {

    public static void main(String[] args) {
        // 定义集合
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("hello");
        list.add("world");
        // 定义迭代器遍历集合
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            // 基于fail-safe机制，允许在迭代器中安全修改数据结构而不会触发CME
            list.add("hahahhaha");
            list.remove("hello");
            list.remove("xixixiixi");
            System.out.println(iterator.next());
        }
    }

}
