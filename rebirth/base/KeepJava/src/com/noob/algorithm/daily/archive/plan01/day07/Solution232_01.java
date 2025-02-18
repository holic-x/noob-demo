package com.noob.algorithm.daily.archive.plan01.day07;

import java.util.Stack;

/**
 * 🟢 232 用栈实现队列
 * 双栈思路：输入栈、输出栈
 */
public class Solution232_01 {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
    }

}


/**
 * 用栈模拟队列（双栈思路：输入栈、输出栈）
 * MyQueue、push、pop、peek、empty
 */
class MyQueue {

    Stack<Integer> inputStack;
    Stack<Integer> outputStack;

    // 构造方法
    MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    // 将元素x推到队列的末尾
    public void push(int x) {
        // 正常推入元素到输入栈
        inputStack.push(x);
    }

    // 从队列的开头移除并返回元素
    public int pop() {
        // 校验输出栈是否为空，如果为空则将输入栈中现存元素依次弹入输出栈
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        // 从输出栈中获取并弹出元素
        return outputStack.pop();
    }


    // 返回队列开头的元素
    int peek() {
        // 校验输出栈是否为空，如果为空则将输入栈中现存元素依次弹入输出栈
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        // 从输出栈中获取元素
        return outputStack.peek();
    }


    // 如果队列为空，返回true，否则返回false
    public boolean empty() {
        // 校验输入栈、输出栈是否均为空
        return outputStack.isEmpty() && inputStack.isEmpty();
    }

}
