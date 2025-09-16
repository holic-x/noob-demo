package com.noob.algorithm.daily.plan03.hot100_daily.day06.p017;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 046 全排列 - https://leetcode.cn/problems/permutations/description/
 */
public class Solution046_03 {

    public List<List<Integer>> permute(int[] nums) {
        backTrack(nums, 0);
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<>();

    private void backTrack(int[] nums, int idx) {

        // 结果收集
        if (idx >= nums.length) {
            // 记录全序列
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            ans.add(path);
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            swap(nums, i, idx);
            backTrack(nums, idx + 1);
            swap(nums, i, idx);
        }

    }

    // 元素交换
    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

}
