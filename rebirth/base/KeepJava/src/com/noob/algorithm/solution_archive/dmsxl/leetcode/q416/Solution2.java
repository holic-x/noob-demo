package com.noob.algorithm.solution_archive.dmsxl.leetcode.q416;

/**
 * 416 分割等和子集
 */
public class Solution2 {

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

        // 1.dp定义（dp[j]）表示容量为j的背包，可装入的最大容量
        int[] dp = new int[bagSize + 1]; // i物品 j背包容量

        // 2.dp推导：dp = max{dp[j],dp[j-nums[i]]+nums[i]} (j>=nums[i])

        // 3.dp初始化（dp[0]=0,其他按照下标元素进行更新）

        // 4.构建dp
        for (int i = 1; i < n; i++) { // 外层物品
            for (int j = bagSize; j >= nums[i]; j--) { // 内层背包（逆序遍历：确保同一个物品只有一个放入，避免重复覆盖）
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                /*
                // 判断是否出现了dp[i][j]==sum/2,出现则说明满足
                if (dp[j] == bagSize) {
                    return true;
                }
                 */
            }
        }
        return dp[bagSize] == bagSize;
    }

}
