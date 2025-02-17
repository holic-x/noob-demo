package com.noob.algorithm.daily.archive.plan02.day06.p017;

import java.util.*;

/**
 * 🟡 046 全排列 - https://leetcode.cn/problems/permutations/description/
 */
public class Solution046_01 {

    private List<List<Integer>> res = new ArrayList<>(); // 定义结果集合
    private List<Integer> path = new ArrayList<>(); // 定义路径

    public List<List<Integer>> permute(int[] nums) {
        // 调用回溯算法
        backTrack(0, nums);
        // 返回结果
        return res;
    }

    // 定义回溯算法
    private void backTrack(int idx, int[] nums) {
        // 递归出口(结果集处理)
        if (path.size() == nums.length) {
            // 全排列，载入结果集合
            res.add(new ArrayList<>(path));
        }

        // 选择列表处理(idx指向位置为当前选择位置，将其依次与i位置进行交换获得新的排列组合)
        for (int i = idx; i < nums.length; i++) {
            path.add(nums[i]); // 载入结果集合(在交换前则加入nums[i])
            swap(nums, i, idx); // 交换元素
            backTrack(idx + 1, nums); // 递归选择下一个交换位置
            path.remove(path.size() - 1);
            swap(nums, i, idx); // 复原现场

            /*
            swap(nums, i, idx); // 交换元素
            path.add(nums[idx]); // 载入结果集合(在交换后，应加入nums[idx]，因为此处idx与i位置已经交换)
            backTrack(idx + 1, nums); // 递归选择下一个交换位置
            swap(nums, i, idx); // 复原现场
            path.remove(path.size() - 1);
             */
        }
    }

    // 交换元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
