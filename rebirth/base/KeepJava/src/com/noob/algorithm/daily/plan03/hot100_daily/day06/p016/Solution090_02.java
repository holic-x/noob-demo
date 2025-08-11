package com.noob.algorithm.daily.plan03.hot100_daily.day06.p016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 090 子集问题II - https://leetcode.cn/problems/subsets-ii/
 */
public class Solution090_02 {

    /**
     * 思路分析：返回数组所有可能的子集(元素nums可能包括重复元素)，不能包括重复的子集
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 对数组进行排序
        Arrays.sort(nums);
        // 递归回溯
        backTrack(0, nums);
        // 返回结果
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    private void backTrack(int idx, int[] nums) {

        // 递归出口
        if (idx > nums.length) {
            return;
        }

        // 结果收集
        List<Integer> toAddPath = new ArrayList<>(path);
        ans.add(toAddPath); // 去重优化体现在递归过程

        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i - 1] == nums[i]) {
                continue;
            }
            path.add(nums[i]);
            backTrack(i + 1, nums);
            path.remove(path.size() - 1);
        }

    }

}
