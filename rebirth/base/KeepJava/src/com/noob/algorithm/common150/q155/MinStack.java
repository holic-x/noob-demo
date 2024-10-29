package com.noob.algorithm.common150.q155;

import java.util.Stack;

/**
 * 155 最小栈
 */
public class MinStack {

    // 内部定义两个栈，分别用于存储栈元素和对应栈状态下元素的min
    Stack<Integer> stack ;
    Stack<Integer> minValStack ;

    public MinStack() {
        stack = new Stack<>();
        minValStack = new Stack<>();
        // 初始化时先压入一个min
        minValStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        // 同步入栈,并更新当前的栈的最小值（minValStack栈顶元素始终存储上一状态的栈的最小值，因此每次比较只需要和栈顶元素比较即可）
        int curMin = Math.min(minValStack.peek(),val);
        stack.push(val);
        minValStack.push(curMin);
    }

    public void pop() {
        // 同步出栈，直接弹出栈顶元素
        stack.pop();
        minValStack.pop();
    }

    public int top() {
        // 获取栈顶元素
        return stack.peek();
    }

    public int getMin() {
        // 获取当前栈的最小值(当前minValStack的栈顶元素即指向当前状态的栈的最小值)
        return minValStack.peek();
    }
}
