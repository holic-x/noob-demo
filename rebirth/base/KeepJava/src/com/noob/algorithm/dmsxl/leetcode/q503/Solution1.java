package com.noob.algorithm.dmsxl.leetcode.q503;

import java.util.Arrays;
import java.util.Stack;

/**
 * 503 下一个更大元素II
 */
public class Solution1 {
    // 平展循环数组 + 单调栈思路
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        // 定义结果集
        int[] res = new int[2 * len];
        Arrays.fill(res, -1);

        // 将循环数组平展成一个新数组
        int[] newNums = new int[2 * len];
        System.arraycopy(nums, 0, newNums, 0, len); // 数组复制
        System.arraycopy(nums, 0, newNums, len, len); // 数组复制


        // 基于单调栈，对新数组newNums进行遍历、封装
        Stack<Integer> st = new Stack<>();
        st.push(0); // 初始化
        for (int i = 1; i < newNums.length; i++) {
            // 简化版本单调栈处理
            while (!st.isEmpty() && newNums[i] > newNums[st.peek()]) {
                res[st.peek()] = newNums[i]; // 记录栈顶元素的下一个更大元素
                st.pop(); // 栈顶元素出栈
            }
            st.push(i); // 入栈处理
        }

        // 返回结果集
        return Arrays.copyOfRange(res, 0, len);
    }
}
