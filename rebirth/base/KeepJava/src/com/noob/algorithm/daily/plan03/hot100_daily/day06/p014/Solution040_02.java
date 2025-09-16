package com.noob.algorithm.daily.plan03.hot100_daily.day06.p014;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 040 组合总和II - https://leetcode.cn/problems/combination-sum-ii/description/
 */
public class Solution040_02 {

    /**
     * 思路分析：
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 数据排序用作后续结果验证去重
        backTrack(candidates, target, 0);
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>(); // 当前路径
    private int curPathSum = 0; // 当前路径和

    private void backTrack(int[] nums, int target, int idx) {
        // 校验当前路径和
        if (curPathSum == target) {
            // 记录值（需去重）
            ans.add(new ArrayList<>(path));
            /*
            if (!ans.contains(new ArrayList<>(path))) {
                ans.add(new ArrayList<>(path));
            }
             */
            return;
        }

        // 剪枝
        if (curPathSum > target) {
            return;
        }

        // 回溯
        for (int i = idx; i < nums.length; i++) { // 选取元素不能重复，此处基于下一个位置开始进行递归回溯
            if (i > idx && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            curPathSum += nums[i];
            backTrack(nums, target, i + 1);
            path.remove(path.size() - 1);
            curPathSum -= nums[i];
        }

    }

}
