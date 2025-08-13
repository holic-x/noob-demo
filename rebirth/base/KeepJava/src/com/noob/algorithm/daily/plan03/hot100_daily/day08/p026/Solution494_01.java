package com.noob.algorithm.daily.plan03.hot100_daily.day08.p026;


/**
 * 🟡 494 目标和 - https://leetcode.cn/problems/target-sum/description/
 */
public class Solution494_01 {
    /**
     * 思路分析：回溯处理思路
     */
    public int findTargetSumWays(int[] nums, int target) {
        backTrack(nums, target, 0, 0);
        return cnt;
    }

    private int cnt = 0;

    private void backTrack(int[] nums, int target, int curSum, int idx) {
        // 校验结果
        if (idx >= nums.length) {
            // 到达末尾，收集结果
            if (curSum == target) {
                cnt++;
            }
            return; // 递归出口
        }

        // 循环处理
        backTrack(nums, target, curSum + nums[idx], idx + 1);
        backTrack(nums, target, curSum - nums[idx], idx + 1);
    }
}