package com.noob.algorithm.daily.plan01.day40;

import java.util.Stack;

/**
 * 🟡 739 每日温度 - https://leetcode.cn/problems/daily-temperatures/description/
 */
public class Solution739_02 {

    /**
     * 思路分析：单调栈思路（理解单调栈核心），以 【寻找下一个更大元素】为例
     * ① 栈内存储什么？: 存储元素下标
     * ② 栈内元素顺序（栈头->栈底）：如果是寻找下一个更大元素，则栈头->栈底是单调递增的
     * ③ 栈元素操作：比较栈顶元素top和当前遍历元素cur
     * - top > cur: 入栈，满足栈头->栈底递增的设定
     * - top = cur：入栈（相等，正常入栈）
     * - top < cur：弹出元素，直到top>cur
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        // 定义结果集
        int[] t = new int[n];
        // 栈内存储元素对应下标
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // 初始化栈
        // 遍历元素，栈处理
        for (int i = 1; i < n; i++) {
            // 将栈顶元素与遍历元素进行比较
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                // 弹出栈顶元素并封装结果
                t[stack.peek()] = i - stack.peek(); // 栈顶元素指向索引位置为待处理位置，获取并封装结果
                stack.pop(); // 弹出栈顶元素
            }
            // 入栈
            stack.push(i);
        }
        // 返回封装结果
        return t;
    }

}
