package com.noob.algorithm.solution_archive.dmsxl.leetcode.q300;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

import java.util.Arrays;

/**
 * 300 最长递增子序列
 */
public class Solution2 {
    // 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // 1.dp定义(dp[i]表示以`i`位置元素结尾的最大严格递增子序列长度（不连续）)
        int[] dp = new int[n];

        /**
         * 2.dp递推
         * 以nums[i]结尾只有两种可能
         * 1.要么为元素自身构成一个序列：dp[i] = 1
         * 2.要么是可以拼接在[0,i]内元素后面，并且构成最大严格递增子序列（不连续）：dp[i] = max{dp[i],dp[j] + 1}  (j∈{0，i])
         */

        // 3.dp初始化
        Arrays.fill(dp, 1); // 初始化：元素自身为一个递增子序列

        // 4.dp构建
        int maxLen = 0;
        for (int i = 1; i < n; i++) { // 外层确定i
            for (int j = 0; j < i; j++) { // 内层从[0,i]中择选可以构成最大连续递增子序列的元素，选择最长的那个
                // 只有nums[i]>nums[j]才能构建连续递增
                if (nums[i] > nums[j]) {
                    // 选择最大的长度
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 遍历过程中同步更新最大长度
            maxLen = Math.max(maxLen, dp[i]);
            // 打印数组状态变化
            System.out.print("i=" + i + ":");
            PrintUtil.print(dp);
        }

        // 结果处理
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 2};
        Solution2 solution2 = new Solution2();
        solution2.lengthOfLIS(nums);
    }
}
