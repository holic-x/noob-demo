package com.noob.multiThread.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;

// AtomicIntegerArray：原子更新数组
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray array = new AtomicIntegerArray(new int[]{0,1,2,3,4});
        System.out.println(array);
        System.out.println(array.get(1));
        System.out.println(array.compareAndSet(1,1,5));
        System.out.println(array);
        // 预期不一致
        System.out.println(array.compareAndSet(1,3,10));
        System.out.println(array);
    }
}
