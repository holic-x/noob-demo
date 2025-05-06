package com.noob.algorithm.daily.plan03.hot100_random.day03.p005;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 225 用队列实现栈 - https://leetcode.cn/problems/implement-stack-using-queues/description/
 */
public class Solution225_01 {


}

class MyStack {

    /**
     * 思路分析：用队列实现栈（头插思路）
     */
    Queue<Integer> mainQueue; // 主队列：存储元素
    Queue<Integer> subQueue; // 辅助队列：辅助存储（头插辅助）

    // 构造器
    public MyStack() {
        mainQueue = new LinkedList<>();
        subQueue = new LinkedList<>();
    }

    // ① push
    public void push(int x) {
        // 向subQueue中插入元素，随后将mainQueue中的元素插入subQueue，将处理好的队列作为主队列
        subQueue.offer(x);
        while (!mainQueue.isEmpty()) {
            subQueue.offer(mainQueue.poll());
        }
        // 角色切换
        mainQueue = subQueue; // 更新主队列
        subQueue = new LinkedList<>(); // 重置辅助队列
    }

    // ② pop
    public int pop() {
        if (mainQueue.isEmpty()) {
            return -1;
        }
        return mainQueue.poll();
    }

    // ③ top
    public int top() {
        if (mainQueue.isEmpty()) {
            return -1;
        }
        return mainQueue.peek();
    }

    // ④ empty
    public boolean empty() {
        return mainQueue.isEmpty();
    }
}