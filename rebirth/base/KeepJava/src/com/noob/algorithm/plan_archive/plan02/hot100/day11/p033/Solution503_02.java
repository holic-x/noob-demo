package com.noob.algorithm.plan_archive.plan02.hot100.day11.p033;

import java.util.Arrays;
import java.util.Stack;

/**
 * 🟡 503 下一个更大元素II - https://leetcode.cn/problems/next-greater-element-ii/description/
 */
public class Solution503_02 {
    /**
     * 给定循环数组nums，寻找数字x的下一个更大的数，如果不存在则输出-1
     * 循环处理（求余）
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        // 遍历数组，填充ans
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                // 弹出top并填充结果
                int top = stack.pop();
                ans[top] = nums[i % n];
            }
            stack.push(i % n);
        }
        return Arrays.copyOfRange(ans, 0, n);
    }
}
