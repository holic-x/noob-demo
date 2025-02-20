package com.noob.algorithm.solution_archive.dmsxl.leetcode.q046;

import java.util.ArrayList;
import java.util.List;

/**
 * 046 全排列
 */
public class Solution1 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    List<Integer> curPath = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 调用回溯方法
        backTrack(nums, 0);
        // 返回结果
        return res;
    }

    // 定义回溯方法
    public void backTrack(int[] nums, int idx) {
        // 回溯出口
        if (idx >= nums.length) {
            // 表示遍历到叶子节点才将结果载入
            res.add(new ArrayList<>(curPath));
            return;
        }

        // 回溯过程
        for (int i = idx; i < nums.length; i++) {
            // 处理节点：交换位置，载入节点路径
            swap(nums, i, idx);
            curPath.add(nums[idx]);
            // 递归调用
            backTrack(nums, idx + 1); // 处理下个位置
            // 复原现场
            swap(nums, idx, i);
            curPath.remove(curPath.size() - 1);
        }
    }

    // 交换指定索引位置的数组元素
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
