package com.noob.algorithm.daily.archive.plan02.hot100.day08.p027;

import java.util.Arrays;

/**
 * 🟡 377 组合总和IV - https://leetcode.cn/problems/combination-sum-iv/description/
 */
public class Solution377_02 {


    /**
     * 思路分析：不同整数组成nums，求可以构成target的元素组合个数（元素可以重复利用）
     * 动态规划
     */
    public int combinationSum4(int[] nums, int target) {
        int m = nums.length, n = target + 1;
        // 1.dp 定义：dp[j] 表示[0,i]的物品可以凑满 j 的元素组合个数
        int[] dp = new int[n];

        /**
         * 2.dp 递推
         * - j >= nums[i]: dp[j] = dp[j] + dp[j-nums[i]]
         * - j < nums[i]: dp[j] = dp[j] 继承状态
         */

        // 3.dp 初始化
        dp[0] = 1; // 凑满0的方案只有1种就是不凑

        // 4.dp 构建：遍历顺序（排列概念：先背包后物品，背包正序）
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                dp[j] = (j >= nums[i] ? dp[j] + dp[j - nums[i]] : dp[j]);
            }
        }

        // 返回结果
        return dp[target];
    }


}
