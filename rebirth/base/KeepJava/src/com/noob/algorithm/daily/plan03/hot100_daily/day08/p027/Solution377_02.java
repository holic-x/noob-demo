package com.noob.algorithm.daily.plan03.hot100_daily.day08.p027;

/**
 * 🟡 377 组合总和IV - https://leetcode.cn/problems/combination-sum-iv/description/
 */
public class Solution377_02 {


    /**
     * 思路分析：
     */
    public int combinationSum4(int[] nums, int target) {

        // 1.dp[i] 从nums中选择物品能恰好凑成i的组合个数
        int[] dp = new int[target + 1];

        dp[0] = 1; // 构成容量为0的有1种组合方案（表示不需要任何数这种情况）

        // 构建dp：
        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) {
                    // 可放入：选择放或者不放
                    dp[j] += dp[j - nums[i]];
                }
            }
        }

        // 返回结果
        return dp[target];
    }


}
