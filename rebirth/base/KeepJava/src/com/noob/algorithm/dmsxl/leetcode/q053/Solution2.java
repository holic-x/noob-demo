

package com.noob.algorithm.dmsxl.leetcode.q053;

/**
 * 最大子序和：最大连续子数组和
 */
class Solution2 {
    /**
     * 动态规划思路: 空间优化版本
     * 1.dp[i]和dp[i-1]、nums[i] 有关
     * 2.可以在遍历的过程中就同步更新max的值
     * 基于此可以定义滚动变量来滚动存储，不需要记录整个dp数组
     */
    public int maxSubArray(int[] nums) {
        int maxVal = Integer.MIN_VALUE;
        int curMaxSum = 0;

        for (int i = 0; i < nums.length; i++) {
            curMaxSum = Math.max(curMaxSum + nums[i], nums[i]);
            maxVal = Math.max(maxVal, curMaxSum); // 在构建dp的时候同步更新maxVal
        }

        // 返回结果
        return maxVal;
    }
}