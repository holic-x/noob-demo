package com.noob.algorithm.daily.codeTop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution040_01 {

    List<List<Integer>> res = new ArrayList<>(); // 结果集定义
    List<Integer> path = new ArrayList<>(); // 定义当前组合
    int curPathSum = 0; // 定义当前组合元素和


    // 给定集合和目标数，每个数字在每个组合中只能使用1次，找出元素之和为target的组合，且不能包括重复的组合
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序
        Arrays.sort(candidates);
        // 调用回溯算法
        backTrack(candidates, target, 0);
        // 返回结果
        return res;
    }

    // 回溯处理
    private void backTrack(int[] nums, int target, int idx) {
        // 递归出口
        if (idx > nums.length) {
            return;
        }
        // 结果记录
        if (curPathSum == target) {
            if (!res.contains(path)) {
                res.add(new ArrayList<>(path));
            }
            return ;
        }
        // 递归处理
        for (int i = idx; i < nums.length; i++) {
            // 将当前元素加入
            path.add(nums[i]);
            curPathSum += nums[i];
            // 递归处理下一位
            backTrack(nums, target, i + 1);
            // 恢复现场
            path.remove(path.size() - 1);
            curPathSum -= nums[i];
        }
    }

}
