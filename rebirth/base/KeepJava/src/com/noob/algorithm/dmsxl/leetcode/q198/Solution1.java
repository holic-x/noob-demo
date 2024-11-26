package com.noob.algorithm.dmsxl.leetcode.q198;

/**
 * 198 打家劫舍
 */
public class Solution1 {

    // 动态规划思路
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // 1.dp定义（dp[i]为偷到当前第i间房屋可以偷窃的最高金额）
        int[] dp = new int[nums.length];

        /**
         * 2.递推公式：对于每一间房屋都可以选择偷或者不偷
         * - 偷：dp[i] = dp[i-2] + nums[i]
         * - 不偷：dp[i] = dp[i-1]
         * - 偷窃方案：dp[i] = max {dp[i-1],dp[i-2] + nums[i]}
         */

        // 3.初始化
        dp[0] = nums[0]; // 只有一件房屋，必须偷
        dp[1] = Math.max(nums[0], nums[1]); // 有两间房屋，选择金额高的偷

        // 4.dp构建
        int max = -1; // 定义偷窃的最大金额
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            max = Math.max(max, dp[i]);
        }

        // 返回结果
        return max;
    }
}
