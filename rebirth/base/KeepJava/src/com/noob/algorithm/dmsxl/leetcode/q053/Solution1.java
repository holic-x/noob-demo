package com.noob.algorithm.dmsxl.leetcode.q053;

/**
 * 053 最大子数组和
 */
public class Solution1 {

    // 动态规划
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // 特例判断
        if (n == 1) {
            return nums[0];
        }

        // 1.dp定义（表示以i位置元素结尾的最大连续子数组和）
        int[] dp = new int[n];

        /**
         * 2.dp推导
         * 【1】自成一派：dp[i]=nums[i]
         * 【2】拼接尾部：dp[i]=dp[i-1]+nums[i]
         * max{【1】，【2】}
         */

        // 3.初始化dp
        dp[0] = nums[0]; // i=0只有一种情况(自成一派)，因此初始化为nums[0]

        // 4.dp构建
        int maxVal = dp[0]; // 从第一个元素开始同步更新
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            maxVal = Math.max(maxVal, dp[i]); // 更新最大值
        }

        // 返回结果
        return maxVal; // 需要从每个可能结尾的最大连续子数组中择选最大
    }

}
