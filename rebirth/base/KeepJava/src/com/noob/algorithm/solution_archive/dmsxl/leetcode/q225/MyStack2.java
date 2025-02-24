package com.noob.algorithm.solution_archive.dmsxl.leetcode.q225;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 225 用队列实现栈
 */
public class MyStack2 {

    Deque<Integer> deque;

    // 初始化
    public MyStack2() {
        deque = new LinkedList<>();
    }

    // 插入元素：
    public void push(int x) {
        deque.offerLast(x); // 队尾追加最新的元素
    }

    // 弹出元素：
    public int pop() {
        return deque.pollLast(); // 队尾获取最新的元素（满足LIFO）
    }

    // 返回栈顶元素：
    public int top() {
        return deque.peekLast();
    }

    // 栈是否为空：校验deque集合
    public boolean empty() {
        return deque.isEmpty();
    }

}
