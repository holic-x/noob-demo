package com.noob.algorithm.daily.archive.plan02.hot100.day06.p017;

import java.util.*;

/**
 * 🟡 047 全排列II - https://leetcode.cn/problems/permutations-ii/description/
 */
public class Solution047_01 {

    private List<List<Integer>> res = new ArrayList<>(); // 定义结果集合
    private List<Integer> path = new ArrayList<>();

    /**
     * 思路分析：给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // 调用回溯算法
        backTrack(0, nums);
        // 返回结果
        return res;
    }

    // 定义回溯算法
    private void backTrack(int idx, int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }

        HashSet<Integer> set = new HashSet<>();

        for (int i = idx; i < nums.length; i++) {
            // 去重处理
            if (!set.isEmpty() && set.contains(nums[i])) {
                continue; // 重复，剪枝
            }
            set.add(nums[i]);


            path.add(nums[i]);
            swap(nums, i, idx);
            backTrack(idx + 1, nums);
            path.remove(path.size() - 1);
            swap(nums, i, idx);
        }
    }

    // 交换数组元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
