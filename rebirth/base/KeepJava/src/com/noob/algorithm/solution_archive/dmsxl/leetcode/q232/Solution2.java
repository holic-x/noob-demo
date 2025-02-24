package com.noob.algorithm.solution_archive.dmsxl.leetcode.q232;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 232 用栈实现队列
 */
class MyQueue2 {

    // 双栈模拟
    Stack<Integer> inputStack; // 输入栈
    Stack<Integer> outputStack; // 输出栈

    // 初始化
    public MyQueue2() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    // 加入元素（往输入栈中插入）
    public void push(int x) {
        inputStack.push(x);
    }

    // 获取元素（从输出栈中获取，如果输出栈为空校验输入栈是否还有没遍历的数据，将输入栈现存数据压入输出栈）
    public int pop() {
        boolean isOutputStackEmpty = outputStack.isEmpty();
        if (isOutputStackEmpty) {
            // 输出栈为空，触发输入栈的压栈操作
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        // 弹出栈顶元素
        return outputStack.pop();
    }

    // 获取元素（不弹出元素）
    public int peek() {
        boolean isOutputStackEmpty = outputStack.isEmpty();
        if (isOutputStackEmpty) {
            // 输出栈为空，触发输入栈的压栈操作
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        // 获取栈顶元素
        return outputStack.peek();
    }

    // 队列为空判断
    public boolean empty() {
        // 当输入栈和输出栈都为空说明队列为空
        return inputStack.isEmpty() && outputStack.isEmpty();
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
