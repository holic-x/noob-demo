package com.noob.algorithm.daily.archive.plan02.day06.p016;

import java.util.ArrayList;

import java.util.*;

/**
 * 🟡 090 子集问题II - https://leetcode.cn/problems/subsets-ii/
 */
public class Solution090_01 {

    private List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    private List<Integer> path = new ArrayList<>(); // 定义路径

    /**
     * 思路分析：返回数组所有可能的子集(元素nums可能包括重复元素)，不能包括重复的子集
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 排序
        Arrays.sort(nums);
        // 调用回溯算法
        backTrack(0, nums);
        // 返回结果集合
        return res;
    }

    // 回溯算法
    private void backTrack(int idx, int[] nums) {

        if (!res.contains(path)) {
            res.add(new ArrayList<>(path));
        }

        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            path.add(nums[i]);
            backTrack(i + 1, nums);
            path.remove(path.size() - 1);
        }

    }
}
