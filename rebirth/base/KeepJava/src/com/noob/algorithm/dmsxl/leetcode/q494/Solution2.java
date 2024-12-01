package com.noob.algorithm.dmsxl.leetcode.q494;

/**
 * 494 目标和
 */
public class Solution2 {

    public int cnt = 0; // 满足目标和条件的计数器（结果统计）

    // 回溯法
    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return cnt;
    }

    public void dfs(int[] nums, int target, int idx, int sum) {
        // 递归出口
        if (idx == nums.length) {
            if (sum == target) {
                // 记录结果
                cnt++;
            }
            return;
        }

        // 递归回溯
        dfs(nums, target, idx + 1, sum + nums[idx]);

        // 递归回溯
        dfs(nums, target, idx + 1, sum - nums[idx]);
    }
}
