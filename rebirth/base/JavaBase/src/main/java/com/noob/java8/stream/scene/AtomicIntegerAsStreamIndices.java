package com.noob.java8.stream.scene;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 场景3：在Stream 迭代中使用元素索引
 */
// Java8 里没办法直接访问 stream 里的元素，不过可以用下面这种方式实现
public class AtomicIntegerAsStreamIndices {
    public static void main(String[] args)
    {
        String[] array = { "A", "B", "C", "D"  };

        AtomicInteger index = new AtomicInteger();
        Arrays.stream(array)
                .map(str -> index.getAndIncrement() + " -> " + str)
                .forEach(System.out::println);
    }
}