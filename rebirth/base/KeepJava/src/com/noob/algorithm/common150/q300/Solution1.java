package com.noob.algorithm.common150.q300;

import java.util.Arrays;

/**
 * 300 最长递增子序列
 */
public class Solution1 {
    // 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        // 1.dp定义：dp[i] 表示以i位置结尾的最长连续升序序列的长度
        int[] dp = new int[nums.length];
        // 2.初始化（全初始化为1，每个数组元素本身可以作为一个独立的升序序列）
        Arrays.fill(dp, 1);
        /**
         * 3.状态方程
         * dp[i]如何获取：
         * 1.升序：从(0,i)的位置nums[i]小的元素，拼在其后面才能构成升序序列
         * 2.最长：步骤1中获取到的切割点j可能会有很多个，因此在这些可拼接的切割点中选择一个以其结尾构成最长连续序列的进行拼接，即dp[j]最大，拼在其后面才能构成最长
         */
        // 4.构建dp
        // 外层循环：确定i位置
        for (int i = 0; i < nums.length; i++) {
            // 内层循环：确定(0,i)之间可以可nums[i]构成最长连续子序列的点
            for (int j = 0; j < i; j++) {
                // nums[j]<nums[i] 才能构成升序
                if (nums[j] < nums[i]) {
                    // 更新max（满足升序条件前提下，不断更新max）
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 遍历dp数组，找到最长的连续序列
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max == Integer.MIN_VALUE ? -1 : max;
    }
}
