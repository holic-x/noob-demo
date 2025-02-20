package com.noob.algorithm.daily.archive.plan02.hot100.day08.p027;

import java.util.Arrays;

/**
 * 🟡 377 组合总和IV - https://leetcode.cn/problems/combination-sum-iv/description/
 */
public class Solution377_01 {

    private int cnt = 0; // 满足条件的组合个数
    private int curPathSum = 0; // 当前路径和

    /**
     * 思路分析：不同整数组成nums，求可以构成target的元素组合个数（元素可以重复利用）
     * 回溯法
     */
    public int combinationSum4(int[] nums, int target) {
        // 对数组元素进行排序
        Arrays.sort(nums);
        // 调用回溯算法
        backTrack(0, nums, target);
        // 返回结果
        return cnt;
    }

    // 回溯算法
    private void backTrack(int idx, int[] nums, int target) {
        // 递归出口
        if (curPathSum > target) {
            return;
        }
        // 结果收集
        if (curPathSum == target) {
            cnt++;
            return;
        }
        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            // 选择元素
            curPathSum += nums[i];
            // 递归处理（元素可以重复利用）
            backTrack(idx, nums, target);
            // 恢复现场
            curPathSum -= nums[i];
        }
    }
}
