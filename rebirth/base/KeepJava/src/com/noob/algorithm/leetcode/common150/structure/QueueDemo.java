package com.noob.algorithm.leetcode.common150.structure;


import java.util.ArrayDeque;
import java.util.Queue;

public class QueueDemo {
    public static void main(String[] args) {
        // Queue:队列操作
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        queue.offer(2);
        queue.add(3);
        System.out.println(queue.remove()); // 移出并返回目标元素
        System.out.println(queue.poll()); // 返回队列的第一个元素（按照入队顺序返回，但不删除元素）
        System.out.println(queue.peek()); // 返回队列的第一个元素（按照入队顺序返回，并删除元素）
    }
}
