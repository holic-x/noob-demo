package com.noob.algorithm.daily.archive.plan02.hot100.day06.p014;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 040 组合总和II - https://leetcode.cn/problems/combination-sum-ii/description/
 */
public class Solution040_02 {

    private List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    private List<Integer> path = new ArrayList<>();// 定义当前路径
    private int curPathSum = 0; // 定义当前路径和

    /**
     * 思路分析：寻找目标即可中可以使得数字和为target的组合，每个元素只能使用1次
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序
        Arrays.sort(candidates);
        // 调用回溯算法
        backTrack(0, candidates, target);
        // 返回结果
        return res;
    }

    // 回溯算法
    private void backTrack(int idx, int[] nums, int target) {
        // 递归出口：idx到数组末尾（此处可由for控制，可省略）
        /*
        if (idx > nums.length) {
            return;
        }
         */

        // 校验路径和是否相等
        if (curPathSum == target) {
            // 去重处理
            if (!res.contains(path)) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        // 剪枝优化：curPathSum>target 后面构成的组合数会更大，直接剪枝
        if (curPathSum > target) {
            return;
        }

        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            // 递归处理前剪枝（校验连续重复出现的元素，即同一层剪枝）
            if (i > idx && nums[i - 1] == nums[i]) {
                continue; // 出现连续重复，跳过当前分支
            }
            path.add(nums[i]);
            curPathSum += nums[i];
            backTrack(i + 1, nums, target); // 选择元素不可重复
            path.remove(path.size() - 1);
            curPathSum -= nums[i];
        }
    }
}
