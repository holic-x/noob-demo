package com.noob.algorithm.leetcode.common150.q198;

/**
 * 198 打家劫舍
 */
public class Solution1 {
    // 动态规划思路
    public int rob(int[] nums) {
        // 边界条件判断
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        // 1.dp[]定义：dp[i]表示经过i个房子后可劫取的最大值
        int[] dp = new int[len];
        // 2.初始化dp
        dp[0] = nums[0]; // 第1间房屋：只有一种选择（偷）
        dp[1] = Math.max(nums[0], nums[1]); // 第2间房屋：有两种方案（不能连着偷，只能选最大的来偷）
        /**
         * 3.构建dp（n>2的情况下）：对于第i间房屋的选择有两种情况：
         * - 偷：如果偷则不能连着偷，此时偷窃金额为dp[i-2] + nums[i]
         * - 不偷：如果不偷则跳过这个房屋，此时偷窃金额为dp[i-1](表示其带着上一轮偷来的前跳过这间房屋)
         * - 而对于方案的选择则需从这两个方案中计算得到 max ，选择偷窃金额最大的方案
         */
        for (int i = 2; i < len; i++) {
            // 分别计算两种方案
            int doIt = dp[i - 2] + nums[i]; // 偷，不能连着偷
            int undo = dp[i - 1]; // 不偷
            dp[i] = Math.max(doIt, undo); // 选择偷窃金额最高的方案
        }

        // 返回偷窃的最大金额
        return dp[len - 1]; // 偷窃金额是累加的，所以此处所有房屋都逛遍了，返回dp最后一个值即可
    }
}
