package com.noob.algorithm.dmsxl.leetcode.q494;

/**
 * 494 目标和
 */
public class Solution1 {

    public int cnt = 0; // 满足目标和条件的计数器（结果统计）
    public int pathSum = 0; // 当前路径和


    // 回溯法
    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0);
        return cnt;

    }


    public void dfs(int[] nums, int target, int startIdx) {
        // 递归出口
        if(startIdx==nums.length){
            if (pathSum == target) {
                // 记录结果
                cnt++;
            }
        }

        // 处理
        for (int i = startIdx; i < nums.length; i++) {
            // 处理
            pathSum += nums[i];
            // 递归
            dfs(nums, target, i + 1);
            // 回溯
            pathSum -= nums[i];

            // 处理
            pathSum -= nums[i];
            // 递归
            dfs(nums, target, i + 1);
            // 回溯
            pathSum += nums[i];
        }

    }

}
