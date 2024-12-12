package com.noob.algorithm.dmsxl.leetcode.q503;

import java.util.Arrays;
import java.util.Stack;

/**
 * 503 下一个更大元素II
 */
public class Solution2 {

    // 2次循环遍历 + 单调栈
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        // 定义结果集
        int[] res = new int[len];
        Arrays.fill(res, -1);

        // 基于单调栈，对新数组newNums进行遍历、封装
        Stack<Integer> st = new Stack<>();
        st.push(0); // 初始化
        for (int i = 1; i < 2 * len; i++) {
            // 简化版本单调栈处理
            while (!st.isEmpty() && nums[i % len] > nums[st.peek()]) {
                res[st.peek()] = nums[i % len]; // 记录栈顶元素的下一个更大元素
                st.pop(); // 栈顶元素出栈
            }
            st.push(i % len); // 入栈处理
        }

        // 返回结果集
        return res;
    }
}
