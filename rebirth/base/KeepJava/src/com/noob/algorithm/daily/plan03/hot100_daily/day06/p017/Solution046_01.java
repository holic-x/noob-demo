package com.noob.algorithm.daily.plan03.hot100_daily.day06.p017;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 046 全排列 - https://leetcode.cn/problems/permutations/description/
 */
public class Solution046_01 {

    public List<List<Integer>> permute(int[] nums) {
        backTrack(nums);
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    private void backTrack(int[] nums) {
        // 递归出口在遍历过程中体现，此处收集全排列结果
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果当前路径已经出现过该元素则跳过
            if (path.contains(nums[i])) {
                continue;
            }

            // 回溯处理
            path.add(nums[i]);
            backTrack(nums);
            path.remove(path.size() - 1);
        }
    }
}
