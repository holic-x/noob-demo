package com.noob.algorithm.plan_archive.plan02.hot100.day06.p016;

import java.util.*;

/**
 * 🟡 078 子集问题 - https://leetcode.cn/problems/subsets/description/
 */
public class Solution078_01 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集合
    List<Integer> path = new ArrayList<>(); // 定义当前路径

    /**
     * 思路分析：返回数组所有可能的子集，不能包括重复的子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        // 调用回溯算法
        backTrack(0, nums);
        // 返回结果集合
        return res;
    }

    // 回溯算法
    private void backTrack(int idx, int[] nums) {

        res.add(new ArrayList<>(path));

        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            path.add(nums[i]);
            backTrack(i + 1, nums); // 递归处理
            path.remove(path.size() - 1); // 恢复现场
        }

    }
}
