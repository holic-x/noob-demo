package com.noob.algorithm.dmsxl.leetcode.q494;

/**
 * 494 目标和
 */
public class Solution5 {

    // 动态规划（一维数组）
    public int findTargetSumWays(int[] nums, int target) {
        // 前置：动态规划思路转化（求解和为left组合个数）
        int m = nums.length; // 物品数量
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += nums[i];
        }
        int left = (sum + target) / 2;

        // 特例情况判断：sum + target 的和为奇数，则找不到2个left的组合
        if ((sum + target) % 2 == 1) {
            return 0; // 这种情况方案数为0
        }
        // 特例情况判断：如果target的绝对值大于sum，则现有这些元素组合不可能构成target
        if (Math.abs(target) > sum) {
            return 0; // 这种情况方案数为0
        }

        // 动态规划处理
        // 1.dp定义：dp[j] 表示背包容量为j 装满的方案组合数
        int[] dp = new int[left + 1];

        /**
         * 2.递推公式
         * j<nums[i] 装不下物品i，则dp[i][j]继承上一状态：dp[i][j] = dp[i-1][j]
         * j>=nums[i] 可以装下物品i，选择装或者不装：
         *  - 不装物品i：dp[i][j] = dp[i-1][j]
         *  - 装物品i：dp[i][j] = dp[i-1]][j-nums[i]] (先空出可以装物品i的容量)
         *  - 这种情况下的组合总数为：dp[i][j] = dp[i-1][j] +dp[i-1]][j-nums[i]]
         */

        // 3.dp初始化
        dp[0] = 1; // 装满容量为0的方案数为1

        // 4.构建dp（先物品后背包）
        for (int i = 0; i < m; i++) {
            for (int j = left; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        // 返回结果
        return dp[left];
    }
}
