package com.noob.algorithm.daily.plan03.hot100_daily.day09.p028;

/**
 * 🟡 198 打家劫舍 - https://leetcode.cn/problems/house-robber/description/
 */
public class Solution198_01 {

    /**
     * 思路分析：
     */
    public int rob(int[] nums) {
        int n = nums.length;

        // 特例判断
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]); // 要么偷0 要么偷1，两者选最大
        }


        // 1.dp 定义：dp[i] 表示进入到每间房所做的最终决策可以获得的最高金额
        int[] dp = new int[n];

        /**
         * 2.dp 递推：对于每间房可选择偷或者不偷：
         * ① 偷：不能连着偷 dp[i] = dp[i-2] + nums[i]
         * ② 不偷：状态继承 dp[i] = dp[i-1]
         * dp[i] = max{①，②}
         */

        // 3.dp 初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]); // 要么偷0 要么偷1，两者选最大

        // 4.dp 递推
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        // 返回结果
        return dp[n - 1];
    }
}
