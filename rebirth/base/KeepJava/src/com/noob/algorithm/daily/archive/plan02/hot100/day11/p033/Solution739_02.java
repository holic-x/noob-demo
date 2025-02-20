package com.noob.algorithm.daily.archive.plan02.hot100.day11.p033;

import java.util.Stack;

/**
 * 🟡 739 每日温度 - https://leetcode.cn/problems/daily-temperatures/description/
 */
public class Solution739_02 {

    /**
     * 每日温度：下一个更高的温度出现在几日后
     * 单调栈思路：
     * - stack 定义 （存储索引）
     * - 入栈顺序 （寻找下一个更大的值，栈头->栈底（从小->大））
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];

        // 定义栈辅助遍历（单调栈思路）
        Stack<Integer> stack = new Stack<>();

        // 遍历集合元素
        for (int i = 0; i < n; i++) {
            // 栈不为空则校验栈顶元素和当前遍历元素关系,依次弹出比当前遍历元素值小的栈顶元素
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                // 弹出当前栈顶元素
                int top = stack.pop();
                // 找到比当前栈顶元素大的第1个元素,处理结果
                ans[top] = i - top; // 记录索引差
            }
            // 上述处理完成，最后将当前元素入栈等待下一步校验
            stack.push(i);
        }

        // 返回结果
        return ans;
    }

}
