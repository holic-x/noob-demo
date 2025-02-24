package com.noob.algorithm.solution_archive.dmsxl.leetcode.q377;

import java.util.Arrays;

/**
 * 377 组合总和IV
 */
public class Solution1 {

    public int cnt; // 组合数统计
    public int curPathSum; // 当前路径和

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        backTrack(nums, target);
        return cnt;
    }

    // 回溯法
    public void backTrack(int[] nums, int target) {
        // 结果统计
        if (curPathSum == target) {
            cnt++;
            return;
        }

        // 处理
        for (int i = 0; i < nums.length; i++) {
            // 剪枝
            if (curPathSum + nums[i] > target) {
                break;
            }

            // 1.选择
            curPathSum += nums[i];
            // 2.递归处理下一位
            backTrack(nums, target);
            // 3.复原现场
            curPathSum -= nums[i];
        }
    }

}
