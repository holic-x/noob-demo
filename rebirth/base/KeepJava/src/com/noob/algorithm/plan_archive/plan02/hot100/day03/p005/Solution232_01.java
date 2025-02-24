package com.noob.algorithm.plan_archive.plan02.hot100.day03.p005;

import java.util.Stack;

/**
 * 🟢 232 用栈实现队列
 */
public class Solution232_01 {
    /**
     * 实现思路：基于双栈实现队列（输入栈、输出栈）
     */
}

// 用栈实现队列
class MyQueue {

    // 定义两个栈实现队列
    Stack<Integer> inputStack; // 输入栈：接收参数
    Stack<Integer> outputStack; // 输出栈：访问参数

    // 构造器
    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    // ① push
    public void push(int x) {
        // 基于输入栈推入数据
        inputStack.push(x);
    }

    // ② pop
    public int pop() {
        // 基于输出栈读取数据（如果输出栈为空则从输入栈中导入数据）
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
            return outputStack.pop();
        } else {
            // 直接弹出数据
            return outputStack.pop();
        }
    }

    // ③ peek
    public int peek() {
        // 基于输出栈读取数据（如果输出栈为空则从输入栈中导入数据）
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
            return outputStack.peek();
        } else {
            // 直接读取数据
            return outputStack.peek();
        }
    }

    // ④ empty
    public boolean empty() {
        // 如果两个栈内容均为空则构建的队列为空
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

}