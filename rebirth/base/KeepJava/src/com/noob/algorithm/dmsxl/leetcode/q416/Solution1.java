package com.noob.algorithm.dmsxl.leetcode.q416;

/**
 * 416 分割等和子集
 */
public class Solution1 {

    /**
     * 动态规划思路：切换为01背包问题
     *
     * @param nums 既是weight又是value
     *             背包容量设定为sum/2（表示分割成两个元素和相等的子集）
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;

        // 前置：遍历一遍数组元素，先计算出nums元素总和，得到目标的背包容量
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false; // 总和为奇数，不可能凑到
        }
        int bagSize = sum / 2;

        // 1.dp定义（dp[i][j]）表示容量为j的背包，从[0-i]中选择任意物品装入可获得的最大容量
        int[][] dp = new int[n][bagSize + 1]; // i物品 j背包容量

        // 2.dp推导：dp = max{dp[i-1][j],dp[i-1][j-nums[i]]+nums[i]} (j>=nums[i])


        // 3.dp初始化
        for (int j = 0; j <= bagSize; j++) {
            // 首行初始化
            dp[0][j] = (j >= nums[0]) ? nums[0] : 0; // 此处nums[0]表示value[0]
        }

        // 4.构建dp
        for (int i = 1; i < n; i++) { // 外层物品
            for (int j = 1; j <= bagSize; j++) { // 内层背包
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                }
                // 判断是否出现了dp[i][j]==sum/2,出现则说明满足
                if (dp[i][j] == bagSize) {
                    return true;
                }
            }
        }
        return false;
    }

}
