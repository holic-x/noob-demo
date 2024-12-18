package com.noob.algorithm.plan01.day07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢225 用队列实现栈
 */
public class Solution225_01 {


}

/**
 * 用队列实现栈：双队列实现（基于头插概念）
 */
class MyStack {

    public Queue<Integer> mainQueue; // 主队列：始终存储
    public Queue<Integer> tempQueue; // 辅助队列：用于头插构建,临时存储元素

    // 构造器
    public MyStack() {
        mainQueue = new LinkedList<>();
        tempQueue = new LinkedList<>();
    }

    // 将元素x压入栈顶
    public void push(int x) {
        // 插入过程：将元素插入tempQueue，然后将mainQueue中的元素追加到tempQueue后面，随后交换两个队列，始终让mainQueue指向完整的元素列表（即栈）
        tempQueue.offer(x);
        while (!mainQueue.isEmpty()) {
            tempQueue.offer(mainQueue.poll());
        }
        // 交换两个队列指针（也可以直接让mainQueue指向tempQueue，然后tempQueue重置）
        mainQueue = tempQueue;
        tempQueue = new LinkedList<>();
    }

    // 移除并返回栈顶元素
    public int pop() {
        // 返回并弹出mainQueue的队头元素（即对应头插后构成的栈的栈顶元素）
        return mainQueue.poll();
    }


    // 返回栈顶元素
    public int top() {
        // 返回mainQueue的队头元素（即对应头插后构成的栈的栈顶元素）
        return mainQueue.peek();
    }

    // 如果栈为空则返回true，否则返回false
    public boolean empty() {
        // 限定mainQueue即为对应的栈，因此此处只需要校验mainQueue是否为空
        return mainQueue.isEmpty();
    }

}
