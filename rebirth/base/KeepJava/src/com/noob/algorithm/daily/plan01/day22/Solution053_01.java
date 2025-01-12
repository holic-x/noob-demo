package com.noob.algorithm.daily.plan01.day22;

import com.noob.algorithm.dmsxl.util.PrintUtil;

/**
 * 🟡 053 最大子数组和 - https://leetcode.cn/problems/maximum-subarray/description/
 */
public class Solution053_01 {

    /**
     * 最大和的连续子数组
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;

        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }

        // 1.构建dp：dp[i] 表示以i位置元素结尾的子数组的最大和
        int[] dp = new int[len];

        /**
         * 2.dp递推公式
         * - 对每个位置i的dp[i],只能是以该元素结尾的子数组，所以有两种情况
         * - ① dp[i] = nums[i] 自立门派
         * - ② dp[i] = dp[i-1] + nums[i] 继续拼接
         * =》 dp[i] = max{nums[i],dp[i-1] + nums[i]}
         */

        // 3.dp初始化：
        dp[0] = Math.max(Integer.MIN_VALUE, nums[0]);

        // 4.dp构建
        int maxSum = Math.max(Integer.MIN_VALUE, dp[0]);
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }

        PrintUtil.print(dp);

        // 返回最大和
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        Solution053_01 solution = new Solution053_01();
        solution.maxSubArray(nums);
    }
}
