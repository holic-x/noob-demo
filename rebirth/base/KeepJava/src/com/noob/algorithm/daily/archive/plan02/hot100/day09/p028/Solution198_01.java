package com.noob.algorithm.daily.archive.plan02.hot100.day09.p028;

/**
 * 🟡 198 打家劫舍 - https://leetcode.cn/problems/house-robber/description/
 */
public class Solution198_01 {

    /**
     * 思路分析：不能连着偷两间房，求偷窃的最大金额
     */
    public int rob(int[] nums) {
        // 特例分析
        if (nums == null || nums.length == 0) {
            return 0; // 没有房子可偷
        }

        int n = nums.length;
        if (n == 1) {
            return nums[0]; // 只有1间房子，必须偷
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]); // 有2间房子，选着偷
        }

        // 1.dp 定义：dp[i] 表示偷到第i间房可获取的最大金额
        int[] dp = new int[n];

        /**
         * 2.dp 递推：
         * dp[i] 可基于前面的偷窃情况进行推导，对于第i间房屋可选择偷、不偷
         * - 偷：dp[i] = dp[i-2] + nums[i] (不能连着偷，偷了i则不能偷i-1)
         * - 不偷：dp[i] = dp[i-1] (不偷该房间，则状态继承第i-1间房偷窃的最大金额)
         */

        // 3.dp 初始化
        dp[0] = nums[0];// 只有1间房子可以选择，直接偷
        dp[1] = Math.max(nums[0], nums[1]); // 有2间房子可供选择，选最大金额偷
        // 遍历其他房子
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        // 遍历完所有房子，得到最大偷窃金额
        return dp[n - 1];
    }
}
