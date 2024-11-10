package com.noob.algorithm.common150.q198;

/**
 * 198 打家劫舍
 */
public class Solution2 {
    // 动态规划思路（空间优化版本）
    public int rob(int[] nums) {
        // 临界条件判断
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0]; // 只有一间，偷
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]); // 只有两间，选择最大价值的偷
        }

        // n>2的情况，dp[i] = max{dp[i-2]+nums[i],dp[i-1]} 不能连着偷
        int p = nums[0]; // 表示dp[i-2] 初始化为nums[0]（第1间偷得最大值）
        int q = Math.max(nums[0], nums[1]); // 表示dp[i-1] 初始化为 Math.max(nums[0],nums[1]);（第2间偷得最大值）
        int r = 0; // 表示dp[i] 第i间房屋偷得的最大值
        int max = Integer.MIN_VALUE;
        for (int i = 2; i < len; i++) {
            // 确定偷窃方案
            int doIt = p + nums[i];
            int undo = q;
            r = Math.max(doIt, undo);
            max = Math.max(max, r); // 每次确定方案偷完之后更新一下max

            // 滚动记录
            p = q;
            q = r;
        }
        // 返回结果
        return max;
    }
}
