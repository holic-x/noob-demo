package com.noob.algorithm.daily.plan01.day41;

import java.util.Stack;

/**
 * 🔴 084 柱状图中最大的矩形 - https://leetcode.cn/problems/largest-rectangle-in-histogram/description/
 */
public class Solution084_01 {

    /**
     * 单调栈思路：
     * 栈存储：索引
     * 栈顺序：栈顶->栈顶 递减（从大到小）
     * 栈操作：top与cur比较
     * - ① top <= cur 符合顺序要求，入栈
     * - ② top > cur 出栈并记录
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        int maxArea = 0;

        // 构建栈辅助遍历
        Stack<Integer> st = new Stack<>();
        st.push(0);

        // 基于原数组首尾补0用于处理连续单调递增或连续单调递减的情况
        int[] newHeight = new int[n + 2];
        System.arraycopy(heights, 0, newHeight, 1, n);

        for (int i = 1; i < newHeight.length; i++) {
            if (newHeight[st.peek()] <= newHeight[i]) {
                st.push(i);
            } else {
                while (!st.isEmpty() && newHeight[st.peek()] > newHeight[i]) {
                    // 记录结果
                    int h = newHeight[st.pop()]; // 弹出第1个栈顶元素索引指向元素为高度
                    int lIdx = st.peek(); // 第2个栈顶元素为左边界
                    int rIdx = i; // 当前遍历位置i为右边界
                    maxArea = Math.max(maxArea, h * (rIdx - lIdx - 1)); // 更新最大面积
                }
                st.push(i);
            }
        }

        // 返回结果
        return maxArea;

    }

}
