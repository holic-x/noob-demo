package com.noob.algorithm.plan_archive.plan01.day31;

import java.util.Arrays;

/**
 * 🟡 377 组合总和IV - https://leetcode.cn/problems/combination-sum-iv/
 */
public class Solution377_02 {

    public int cnt = 0; // 统计满足条件的组合总数
    public int curPathSum = 0; // 计算当前路径和

    /**
     * 回溯思路
     */
    public int combinationSum4(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        // 调用回溯算法
        backTrack(nums, target);
        // 返回结果
        return cnt;
    }

    // 回溯算法
    private void backTrack(int[] nums, int target) {
        // 如果路径和满足则加入结果集
        if (curPathSum == target) {
            cnt++;
            return;
        }

        // 回溯处理
        for (int i = 0; i < nums.length; i++) {
            if (curPathSum + nums[i] > target) {
                break;
            }
            curPathSum += nums[i]; // 选择节点
            backTrack(nums, target); // 递归处理
            curPathSum -= nums[i]; // 恢复现场
        }
    }
}
