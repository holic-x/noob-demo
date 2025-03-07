package com.noob.algorithm.daily.codeTop;

import java.util.Stack;

/**
 * 🟡 155 最小栈 - https://leetcode.cn/problems/min-stack/description/
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 */
public class Solution155_01 {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

}

// 自定义最小栈
class MinStack {
    // 借助两个栈辅助处理
    Stack<Integer> mainStack; // 辅助存储栈元素
    Stack<Integer> minStack; // 同步每个栈状态下的min值

    // 初始化
    public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE); // 初始化设定一个最大值作为空栈的min对照
    }

    // 插入元素 push
    public void push(int val) {
        // 插入元素、更新min
        mainStack.push(val);
        int curMin = minStack.peek();
        minStack.push(Math.min(curMin, val));
    }

    // 弹出栈顶元素 pop
    public void pop() {
        // 弹出元素、并弹出对应状态下的min
        mainStack.pop();
        minStack.pop();
        // 返回结果
        // return val;
    }

    // 获取栈顶元素 top
    public int top() {
        return mainStack.peek();
    }

    // 获取当前栈的最小值 getMin
    public int getMin() {
        return minStack.peek();
    }
}



