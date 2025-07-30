package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;


import java.util.Stack;

/**
 * 🟢 232 用栈实现队列 - https://leetcode.cn/problems/implement-queue-using-stacks/description/
 */
public class Solution232_01 {
    /**
     * 队列：先进先出
     * 实现思路：双栈实现队列
     * - 输入栈：写入数
     * - 输出栈：读取数（如果为空则从输入栈中加载数据）
     */
}

// 用栈实现队列
class MyQueue {
    Stack<Integer> inputStack;
    Stack<Integer> outputStack;

    // 构造器
    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    // ① push
    public void push(int x) {
        inputStack.push(x);
    }

    // ② pop
    public int pop() {
        // 如果输出栈不为空直接从栈顶弹出，如果为空则先从输入栈中加载
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }

        // 弹出栈顶元素
        return outputStack.pop();
    }

    // ③ peek
    public int peek() {

        // 如果输出栈不为空直接从栈顶弹出，如果为空则先从输入栈中加载
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }

        // 返回栈顶元素
        return outputStack.peek();
    }

    // ④ empty
    public boolean empty() {
        // 双栈不为空则表示整个队列不为空
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

}