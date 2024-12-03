package com.noob.algorithm.dmsxl.leetcode.q084;

import java.util.Stack;

/**
 * 084 柱状图中的最大的矩形
 */
public class Solution2 {
    // 单调栈：测试（首尾不补0的情况讨论❌❌❌❌❌❌）
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;

        // 构建辅助栈
        Stack<Integer> st = new Stack<>();
        st.push(0); // 初始化

        // 定义最大面积
        int maxArea = 0;

        // 遍历元素(对新数组进行处理)
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] >= heights[st.peek()]) {
                st.push(i);
            } else {
                while (!st.isEmpty() && heights[i] < heights[st.peek()]) {
                    int mid = st.pop(); // 弹出第一个栈顶元素作为高度
                    int left = st.peek(); // 其左边界为当前栈顶元素所在索引
                    int right = i; // 其右边界为当前遍历位置i
                    int w = right - left - 1; // 计算宽度
                    int h = heights[mid]; // 计算高度
                    maxArea = Math.max(maxArea, w * h); // 更新最大面积
                }
                // 将当前元素入栈
                st.push(i);
            }
        }

        // 返回结果
        return maxArea;
    }

    public static void main(String[] args) {
        Solution2 solution1 = new Solution2();
        int[] heights1 = new int[]{2, 1, 5, 6, 2, 3};
        int[] heights2 = new int[]{2, 4}; // 连续单调递增
        int[] heights3 = new int[]{4, 3}; // 连续单调递减

        solution1.largestRectangleArea(heights1);
        solution1.largestRectangleArea(heights2);
        solution1.largestRectangleArea(heights3);
    }
}
