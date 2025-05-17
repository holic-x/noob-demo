package com.noob.algorithm.daily.plan03.hot100_random.day03.p005;


import java.util.Stack;

/**
 * 🟢 232 用栈实现队列 - https://leetcode.cn/problems/implement-queue-using-stacks/description/
 */
public class Solution232_01 {
    /**
     * 实现思路：双栈思路（输入栈、输出栈）
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
        if (outputStack.isEmpty()) {
            // 从输入栈中取元素
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.pop();
    }

    // ③ peek
    public int peek() {
        if (outputStack.isEmpty()) {
            // 从输入栈中取元素
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.peek();
    }

    // ④ empty
    public boolean empty() {
        // 两个栈均为空则视为空
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

}