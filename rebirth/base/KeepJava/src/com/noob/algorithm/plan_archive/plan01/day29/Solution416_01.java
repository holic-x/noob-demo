package com.noob.algorithm.plan_archive.plan01.day29;

/**
 * 🟡 416 分割等和子集 - https://leetcode.cn/problems/partition-equal-subset-sum/
 */
public class Solution416_01 {

    /**
     * 动态规划：
     * ① 校验当前元素和的奇偶性
     * - 如果和为奇数则必定无法拆分为两个元素和相等的子集
     * - 如果和为偶数则可能拆分，需进一步进行步骤②校验操作
     * ② 转化为0-1背包问题，dp[i][j] 表示从[0,i]中选择物品可装满容量j的最大价值
     * - 那么此处这个背包容量bagSize即为sum/2,要判断dp[i][bagSize]的最大价值是否恰好也为sum/2
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }

        // ① 判断元素和的奇偶性
        if (sum % 2 == 1) {
            return false; // 元素和为奇数，无法分拆为两个元素和相等的子集
        }

        // ② 当元素和为偶数，转化为0-1背包问题求解
        int bagSize = sum / 2;

        // 1.dp 定义：dp[i][j] 表示从[0,i]中选择物品放入背包可构成容量为j的最大物品价值
        int[][] dp = new int[len][bagSize + 1];
        int m = dp.length, n = dp[0].length;

        /**
         * 2.dp 递推
         * 0-1 背包问题：外层背包、内层物品
         */

        // 3.dp初始化
        for (int j = 0; j < n; j++) {
            dp[0][j] = (j >= nums[0]) ? nums[0] : 0; // 此处nums[0]表示value[0]
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) { // 外层物品
            for (int j = 1; j < n; j++) { // 内层背包
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                }
            }
        }

        return dp[m - 1][bagSize] == bagSize;
    }

}
