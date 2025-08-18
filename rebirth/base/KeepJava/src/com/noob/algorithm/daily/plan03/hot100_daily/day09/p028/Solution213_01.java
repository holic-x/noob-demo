package com.noob.algorithm.daily.plan03.hot100_daily.day09.p028;

/**
 * 🟡 213 打家劫舍II - https://leetcode.cn/problems/house-robber-ii/description/
 */
public class Solution213_01 {

    /**
     * 思路分析：数组平展，分类讨论
     */
    public int rob(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        // 0,n-2
        int max1 = robByRange(nums, 0, n - 2);
        // 1,n-1
        int max2 = robByRange(nums, 1, n - 1);

        // 返回最大偷取金额
        return Math.max(max1, max2);
    }

    /**
     * 偷取限定范围区域的房屋：dp[i] = max{偷，不偷}
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int robByRange(int[] nums, int start, int end) {
        // 确定偷窃目标范围
        int n = end - start + 1;
        // 特例讨论
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[start];
        }
        if (n == 2) {
            return Math.max(nums[start], nums[start + 1]);
        }

        int[] dp = new int[n];

        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);

        // 构建dp
        int idx = 2;
        for (int i = start + 2; i <= end; i++) {
            dp[idx] = Math.max(dp[idx - 2] + nums[i], dp[idx - 1]);
            idx++;
        }

        return dp[n - 1];
    }


}
