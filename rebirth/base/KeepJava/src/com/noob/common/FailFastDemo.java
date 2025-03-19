package com.noob.common;

import java.util.HashMap;
import java.util.Iterator;

/**
 * fail-fast 机制（多线程并发操作集合的一种失败处理机制）
 */
public class FailFastDemo {

    public static void main(String[] args) {
        // 定义集合
        HashMap<String, String> map = new HashMap<>();
        map.put("001", "hello");
        map.put("002", "world");
        // 定义迭代器遍历集合
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            // 在集合中修改数据（例如新增元素、删除元素，影响到原有数据集合记录数，即集合结构发生变化）会触发CME异常，如果单纯只是修改某个数据的值则是允许的
            // map.put("003","java"); 新增一个不存在的元素，触发CME异常
            // map.remove("002"); 删除一个集合中存在的元素，触发CME异常
            // map.remove("003"); // 删除一个集合中不存在的元素，不会触发CME
            // map.put("002","xixixi"); // 允许修改元素（但需理解修改时机），不会触发CME
        }
    }

}
