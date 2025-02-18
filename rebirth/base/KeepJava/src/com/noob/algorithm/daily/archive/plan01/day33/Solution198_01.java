package com.noob.algorithm.daily.archive.plan01.day33;

/**
 * 🟡 198 打家劫舍 - https://leetcode.cn/problems/house-robber/description/
 */
public class Solution198_01 {

    /**
     * 思路：动态规划，不能连着偷
     */
    public int rob(int[] nums) {
        int n = nums.length;

        if (n < 2) {
            return n == 0 ? 0 : nums[0];
        }


        // 1.dp 定义：dp[i] 表示在第i间房屋可以偷得的最大金额
        int[] dp = new int[n];

        /**
         * 2.dp 推导：根据上一间房的偷盗情况讨论是否要盗取当前房屋
         * ① 如果偷了第i-1间房，则不能偷i
         * ② 如果没偷第i-1间房，则可以偷i
         * 因此得到dp[i] = max{dp[i-1],dp[i-2] + nums[i]}
         */

        // 3.dp 初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // 4.dp 构建
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }

        // 返回结果
        return dp[n-1]; // 所有房屋过滤完成得到最大的偷盗金额
    }

}
