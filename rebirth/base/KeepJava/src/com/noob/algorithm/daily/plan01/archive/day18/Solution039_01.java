package com.noob.algorithm.daily.plan01.archive.day18;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 039 组合总和
 */
public class Solution039_01 {
    public List<List<Integer>> res = new ArrayList<>(); // 记录结果集
    public List<Integer> curPath = new ArrayList<>(); // 记录当前路径
    public int curPathSum = 0; // 记录当前路径和

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 调用回溯算法
        backTrack(candidates, target, 0);
        // 返回结果集
        return res;
    }

    // 回溯算法
    public void backTrack(int[] nums, int target, int idx) {
        // 递归出口
        if (curPathSum == target) {
            res.add(new ArrayList<>(curPath));
        }

        // 由于无法固定递归层数，此处需要进行剪枝 如果sum大于target则无需继续递归（剪枝）
        if (curPathSum > target) {
            return;
        }

        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            curPath.add(nums[i]);
            curPathSum += nums[i];
            backTrack(nums, target, i);
            curPath.remove(curPath.size() - 1);
            curPathSum -= nums[i];
        }
    }
}
