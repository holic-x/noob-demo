package com.noob.algorithm.daily.plan03.hot100_daily.day06.p014;

import java.util.ArrayList;
import java.util.List;

/**
 * 039-组合总和 https://leetcode.cn/problems/combination-sum/
 * 给定无重复元素的整数数组，选出可以使得数字和为target的不同组合(同一个数可以使用多次)
 */
public class Solution039_01 {

    /**
     * 思路分析：
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTrack(candidates, target, 0);
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>(); // 当前路径
    int curPathSum = 0; // 当前路径和


    private void backTrack(int[] candidates, int target, int idx) {
        if (idx == candidates.length) {
            return;
        }

        // 校验当前路径和
        if (curPathSum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }

        if (curPathSum > target) {
            return;
        }

        // 回溯处理(组合，可以重复选择元素)
        for (int i = idx; i < candidates.length; i++) {
            path.add(candidates[i]);
            curPathSum += candidates[i];
            backTrack(candidates, target, i);
            path.remove(path.size() - 1);
            curPathSum -= candidates[i];
        }
    }


}
