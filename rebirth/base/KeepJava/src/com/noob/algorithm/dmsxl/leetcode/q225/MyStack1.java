package com.noob.algorithm.dmsxl.leetcode.q225;

import java.util.LinkedList;

/**
 * 225 用队列实现栈（双队列、头插法思路）
 */
class MyStack1 {

    LinkedList<Integer> queue1; // queue1 保存最新的"模拟栈"数据
    LinkedList<Integer> queue2; // queue2 作为头插的辅助队列

    // 初始化
    public MyStack1() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    // 插入元素：类似头插概念
    public void push(int x) {
        // 1.将元素先插入到queue2
        queue2.offer(x);
        // 2.将queue1队列中现有元素依次补到queue2中，即确保最新插入的数据始终在队头（满足后入先出）
        while(!queue1.isEmpty()){
            queue2.offer(queue1.poll());
        }
        // 3.交换queue1、queue2，让queue1始终保存最新的"模拟栈"数据（即保存经过头插后的内容）
        LinkedList<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    // 弹出元素：从queue1中获取
    public int pop() {
        return queue1.poll();
    }

    // 返回栈顶元素：此处对应queue1最新头插的队首元素
    public int top() {
        return queue1.peek();
    }

    // 栈是否为空：queue1始终指向最新的模拟栈内容，因此此处只需要校验queue
    public boolean empty() {
        return queue1.isEmpty();
    }

}
