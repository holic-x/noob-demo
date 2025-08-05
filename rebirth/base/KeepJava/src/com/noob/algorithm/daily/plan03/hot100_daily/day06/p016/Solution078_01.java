package com.noob.algorithm.daily.plan03.hot100_daily.day06.p016;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 078 子集问题 - https://leetcode.cn/problems/subsets/description/
 */
public class Solution078_01 {


    /**
     * 思路分析：
     */
    public List<List<Integer>> subsets(int[] nums) {
        backTrack(0, nums);
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    // 回溯算法
    private void backTrack(int idx, int[] nums) {
        if (idx > nums.length) {
            return;
        }

        // 结果收集
        ans.add(new ArrayList<>(path));

        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            path.add(nums[i]);
            backTrack(i + 1, nums);
            path.remove(path.size() - 1);
        }
    }
}
