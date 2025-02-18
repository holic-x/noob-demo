package com.noob.algorithm.daily.plan02.day08.p026;

/**
 * 🟡 416 分割等和子集 - https://leetcode.cn/problems/partition-equal-subset-sum/description/
 */
public class Solution416_01 {

    /**
     * 思路分析：动态规划（01背包问题）
     */
    public boolean canPartition(int[] nums) {
        // 遍历数组获取数组和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 判断sum的奇偶性
        if (sum % 2 == 1) {
            return false; // 和为奇数，无法划分为两个等和子集
        }

        // 1.dp 定义 （用任意物品i放入背包容量为j的所能获得的最大价值总和）
        int bagSize = sum / 2;
        int m = nums.length, n = bagSize + 1;
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推
         * dp[i][j] 可以选择是否装入该物品
         * - 无法装入：本身背包容量不足，无法放入物品，沿用上一状态 => dp[i][j] = dp[i-1][j]
         * - 可以装入：选择装入或者不装入
         *    - 选择不装：dp[i][j] = dp[i-1][j]
         *    - 选择装：dp[i][j] = dp[i-1][j-nums[i]] + nums[i] (先空出上一状态的容量)
         */

        // 3.dp 初始化（首行首列初始化）
        // 首行初始化（i==0）表示背包容量为j的背包放下物品0所能获得的最大物品价值
        for (int j = 0; j < n; j++) {
            dp[0][j] = (j >= nums[0] ? nums[0] : 0);
        }
        // 首列初始化（j==0）表示背包容量为0的背包放下物品i所能获得的最大物品价值（可以理解为在无负数的情况下，背包放不下任何物品）
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }

        // 4.dp 构建（迭代顺序：先物品后背包、先背包后物品均可）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 返回结果
        return dp[m - 1][bagSize] == bagSize;
    }
}
