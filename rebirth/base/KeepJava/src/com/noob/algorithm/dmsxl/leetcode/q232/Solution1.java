package com.noob.algorithm.dmsxl.leetcode.q232;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 232 用栈实现队列
 */
class MyQueue1 {

    // 双端队列模拟
    Deque<Integer> deque ;

    // 初始化
    public MyQueue1() {
        deque = new ArrayDeque<>();
    }

    // 加入元素
    public void push(int x) {
        deque.offerFirst(x);
    }

    public int pop() {
        return deque.pollLast();
    }

    public int peek() {
        return deque.peekLast();
    }

    public boolean empty() {
        return deque.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
