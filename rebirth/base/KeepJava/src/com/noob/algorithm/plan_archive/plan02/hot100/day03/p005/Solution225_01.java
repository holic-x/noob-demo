package com.noob.algorithm.plan_archive.plan02.hot100.day03.p005;

import java.util.LinkedList;

/**
 * 🟢 225 用队列实现栈
 */
public class Solution225_01 {
    /**
     * 思路分析：累计头插法的概念，每次将元素插入到队首即可
     * 思路①：先将mainQueue中的元素转移到secQueue，然后在mainQueue插入元素，再将secQueue的内容搬回mainQueue（此过程涉及到两次搬运）
     * 思路②：将元素插入secQueue，随后将mainQueue按顺序转移到secQueue，最后将mainQueue进行交换（此过程只涉及一次搬运和一次交换）
     */
}

class MyStack {

    // 构建双队列实现自定义栈
    LinkedList<Integer> mainQueue; // 主队列
    LinkedList<Integer> secQueue; // 辅助队列

    // 构造器
    public MyStack() {
        mainQueue = new LinkedList<>();
        secQueue = new LinkedList<>();
    }

    // ① push(选用思路②处理)
    public void push(int x) {
        // a.在secQueue中插入元素
        secQueue.offer(x);
        // b.将mainQueue中的元素按顺序插入到secQueue
        while (!mainQueue.isEmpty()) {
            secQueue.offer(mainQueue.poll());
        }
        // c.交换两者
        LinkedList<Integer> temp = mainQueue;
        mainQueue = secQueue;
        secQueue = temp;
    }

    // ② pop
    public int pop() {
        // 此处mainQueue的队首元素即为最近插入的最新的元素
        return mainQueue.poll();
    }

    // ③ top
    public int top() {
        // 此处mainQueue的队首元素即为最近插入的最新的元素
        return mainQueue.peek();
    }

    // ④ empty
    public boolean empty() {
        // 此处只需要校验mainQueue（因为secQueue只是用于辅助插入的队列）
        return mainQueue.isEmpty();
    }
}