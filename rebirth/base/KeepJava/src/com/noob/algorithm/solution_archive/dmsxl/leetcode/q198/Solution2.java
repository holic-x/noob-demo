package com.noob.algorithm.solution_archive.dmsxl.leetcode.q198;

/**
 * 198 打家劫舍
 */
public class Solution2 {

    // 动态规划思路:空间优化版本
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int p = nums[0]; // 只有一件房屋，必须偷 (对应dp[i-2])
        int q = Math.max(nums[0], nums[1]); // 有两间房屋，选择金额高的偷(对应dp[i-1])
        int r = 0;
        int max = -1; // 定义偷窃的最大金额

        for (int i = 2; i < nums.length; i++) {
            r = Math.max(q, p + nums[i]);
            max = Math.max(max, r);
            // 更滚动更新变量
            p = q;
            q = r;
        }

        // 返回结果
        return max;
    }
}
