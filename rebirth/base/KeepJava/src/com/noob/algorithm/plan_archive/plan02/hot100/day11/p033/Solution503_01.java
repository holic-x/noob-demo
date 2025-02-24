package com.noob.algorithm.plan_archive.plan02.hot100.day11.p033;

import java.util.Arrays;
import java.util.Stack;

/**
 * 🟡 503 下一个更大元素II - https://leetcode.cn/problems/next-greater-element-ii/description/
 */
public class Solution503_01 {
    /**
     * 给定循环数组nums，寻找数字x的下一个更大的数，如果不存在则输出-1
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        Arrays.fill(ans, -1);

        // 平展数组（调整为2*n）
        int[] newNums = new int[2 * n];
        System.arraycopy(nums, 0, newNums, 0, n);
        System.arraycopy(nums, 0, newNums, n, n);

        // 遍历新数组，填充ans
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < newNums.length; i++) {
            while (!stack.isEmpty() && newNums[stack.peek()] < newNums[i]) {
                // 弹出top并填充结果
                int top = stack.pop();
                ans[top] = newNums[i];
            }
            stack.push(i);
        }
        return Arrays.copyOfRange(ans, 0, n);
    }
}
