package com.noob.algorithm.daily.plan03.hot100_daily.day06.p017;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 046 全排列 - https://leetcode.cn/problems/permutations/description/
 */
public class Solution046_02 {

    public List<List<Integer>> permute(int[] nums) {
        backTrack(nums, 0);
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    private void backTrack(int[] nums, int idx) {

        // 结果收集
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            // 交换
            swap(nums, i, idx);
            path.add(nums[idx]);
            // 递归回溯
            backTrack(nums, idx + 1);
            // 复原
            swap(nums, i, idx);
            path.remove(path.size() - 1);
        }

    }

    // 元素交换
    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

}
