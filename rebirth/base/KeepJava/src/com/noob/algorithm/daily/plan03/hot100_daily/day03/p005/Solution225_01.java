package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 225 用队列实现栈 - https://leetcode.cn/problems/implement-stack-using-queues/description/
 */
public class Solution225_01 {
    /**
     * 思路分析：
     * 栈:先进后出（类似头插法概念）
     * - 主栈（队列）
     * - 辅助栈（辅助处理）：插入元素的时候先将元素插入辅助栈，随后将主栈内容插入到辅助栈（保证了原来的顺序，等同于头插概念），最后切换main、help概念
     */

}

class MyStack {

    Queue<Integer> mainQueue;
    Queue<Integer> helpQueue;

    // 构造器
    public MyStack() {
        mainQueue = new LinkedList<>();
        helpQueue = new LinkedList<>();
    }

    // ① push
    public void push(int x) {

        // 将数据插入辅助队列
        helpQueue.offer(x);
        // 遍历主队列元素，将数据迁移到辅助队列
        while (!mainQueue.isEmpty()) {
            helpQueue.offer(mainQueue.poll());
        }
        // 切换主次概念
        Queue<Integer> tmp = mainQueue;
        mainQueue = helpQueue;
        helpQueue = tmp;
    }

    // ② pop
    public int pop() {
        return mainQueue.poll();
    }

    // ③ top
    public int top() {
        return mainQueue.peek();
    }

    // ④ empty
    public boolean empty() {
        return mainQueue.isEmpty();
    }
}