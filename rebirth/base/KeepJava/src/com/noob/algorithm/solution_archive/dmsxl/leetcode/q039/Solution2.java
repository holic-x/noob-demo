package com.noob.algorithm.solution_archive.dmsxl.leetcode.q039;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 039 组合总和
 */
public class Solution2 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    List<Integer> curPath = new ArrayList<>(); // 定义当前路径
    int curPathSum = 0; // 定义当前路径和(与当前路径同步匹配)

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 调用回溯算法
        backTrack(candidates, target, 0);
        // 返回结果
        return res;
    }


    // 回溯算法
    public void backTrack(int[] nums, int target, int idx) {
        // 回溯出口：如果找到路径和为目标target则记录结果集,或者递归到当前累加和大于target直接剪枝（递归退出）
        if (curPathSum == target) {
            res.add(new ArrayList<>(curPath));
            return;
        }

        // 由于无法固定递归层数，此处需要进行剪枝 如果sum大于target则无需继续递归（剪枝）
        if (curPathSum > target) {
            return;
        }

        // 回溯过程
        for (int i = idx; i < nums.length; i++) {
            // 1.处理节点
            curPath.add(nums[i]);
            curPathSum += nums[i];
            // 2.递归
            backTrack(nums, target, i); // 元素允许重复，此处可以从i开始继续递归
            // 3.恢复现场
            curPath.remove(curPath.size() - 1);
            curPathSum -= nums[i];
        }
    }
}
