package com.noob.algorithm.plan_archive.plan02.hot100.day13;

/**
 * 🟡 416 分割等和子集 - https://leetcode.cn/problems/partition-equal-subset-sum/description/
 */
public class Solution416_01 {


    public boolean canPartition(int[] nums) {
        // 计算nums元素之和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 判断sum的奇偶性
        if (sum % 2 == 1) {
            return false; // 如果sum为奇数，则说明根本无法拆分为两个等和子集
        }

        int m = nums.length;
        int bagSize = sum / 2, n = bagSize + 1;
        // 1.定义dp dp[i][j]表示用[0,i]的物品所获得的最大价值
        int[][] dp = new int[m][n];

        /**
         * 2.dp 推导
         * 对于每个物品只能选择放入或者不放入
         * dp[i][j] = max{dp[i-1][j],dp[i][j-nums[i]] + nums[i]}
         */

        // 3.dp 初始化
        // dp[0][j]
        for (int j = 0; j < n; j++) {
            dp[0][j] = (j >= nums[0] ? nums[0] : 0);
        }

        // dp[i][0]
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                }
            }
        }

        // 返回结果
        return dp[m - 1][bagSize] == bagSize;

    }
}
