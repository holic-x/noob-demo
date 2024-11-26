package com.noob.algorithm.dmsxl.leetcode.q213;

import java.util.Arrays;

/**
 * 213-打家劫舍II
 */
public class Solution2 {

    public int rob(int[] nums) {
        // 特例判断
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

        // 划分区域选择偷窃方案
        int rob1 = robByRange(nums, 0, n - 1); // 偷窃范围[0,n-1)(即[0,n-2])
        int rob2 = robByRange(nums, 1, n); // 偷窃范围[1,n)(即[1,n-1])
        return Math.max(rob1, rob2);
    }

    // 动态规划思路
    public int robByRange(int[] nums, int start, int end) {
        if (end - start == 1) {
            return nums[start];
        }
        if (end - start == 2) {
            return Math.max(nums[start], nums[start + 1]);
        }

        int p = nums[start]; // 最开始只有一件房屋，必须偷 (对应dp[i-2])
        int q = Math.max(nums[start], nums[start + 1]); // 有两间房屋，选择金额高的偷(对应dp[i-1])
        int r = 0;
        int max = -1; // 定义偷窃的最大金额

        for (int i = start + 2; i < end; i++) {
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
