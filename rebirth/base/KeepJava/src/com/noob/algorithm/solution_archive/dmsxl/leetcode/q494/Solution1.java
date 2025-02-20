package com.noob.algorithm.solution_archive.dmsxl.leetcode.q494;

/**
 * 494 目标和
 */
public class Solution1 {

    public int cnt = 0; // 满足目标和条件的计数器（结果统计）
    public int curPathSum = 0; // 当前路径和

    // 回溯法
    public int findTargetSumWays(int[] nums, int target) {
        backTrack(nums, target, 0);
        return cnt;

    }

    public void backTrack(int[] nums, int target, int idx) {
        // 递归出口
        if (idx == nums.length) {
            if (curPathSum == target) {
                // 记录结果
                cnt++;
            }
            return;
        }

        // 处理：选择+
        curPathSum += nums[idx];
        // 递归
        backTrack(nums, target, idx + 1);
        // 回溯
        curPathSum -= nums[idx];

        // 处理：选择-
        curPathSum -= nums[idx];
        // 递归
        backTrack(nums, target, idx + 1);
        // 回溯
        curPathSum += nums[idx];
    }

}
