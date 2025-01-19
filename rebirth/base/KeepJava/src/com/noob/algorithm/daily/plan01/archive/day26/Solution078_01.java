package com.noob.algorithm.daily.plan01.archive.day26;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 078 子集 - https://leetcode.cn/problems/subsets/description/
 */
public class Solution078_01 {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> curPath = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        // 调用回溯算法
        backTrack(nums, 0);
        // 返回结果
        return res;
    }

    // 回溯算法定义
    private void backTrack(int[] nums, int idx) {
//        if (idx > nums.length) {
//            return;
//        }

        res.add(new ArrayList<>(curPath));

        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            curPath.add(nums[i]);
            backTrack(nums, i + 1);
            curPath.remove(curPath.size() - 1);
        }
    }
}
