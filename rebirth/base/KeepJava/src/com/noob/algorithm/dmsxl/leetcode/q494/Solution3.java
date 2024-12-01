package com.noob.algorithm.dmsxl.leetcode.q494;

/**
 * 494 目标和
 */
public class Solution3 {

    public int cnt = 0; // 满足目标和条件的计数器（结果统计）

    // 回溯法
    public int findTargetSumWays(int[] nums, int target) {
        backTrack(nums, 0, target);
        return cnt;

    }


    public void backTrack(int[] nums, int startIdx, int rest) {
        // 递归出口
        if (startIdx == nums.length) {
            if (rest == 0) {
                // 记录结果
                cnt++;
            }
            return;
        }

        // 处理
        rest += nums[startIdx];
        // 递归
        backTrack(nums,startIdx+1,rest);
        // 回溯
        rest -= nums[startIdx];

        // 处理
        rest -= nums[startIdx];
        // 递归
        backTrack(nums,startIdx+1,rest);
        // 回溯
        rest += nums[startIdx];
    }

}
