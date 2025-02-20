package com.noob.algorithm.leetcode.common150.q039;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 039 组合总和
 */
public class Solution1 {

    List<List<Integer>> ans = new ArrayList<>(); // 结果集合
    LinkedList<Integer> path = new LinkedList<>(); // 路径列表
    int curSum = 0; // 记录当前path的元素之和

    /**
     * 回溯方法：
     * 递归出口：找到 target 或者 数组遍历到尾部
     */
    public void backTrack(int[] nums, int target, int idx) {
        // 找到满足的target
        if (curSum == target) {
            ans.add(new ArrayList<>(path)); // 此处需要new一个List
            return;
        }
        // 如果sum大于target则无需继续递归（剪枝）
        if (curSum > target) {
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            // 尝试
            path.add(nums[i]);
            curSum += nums[i];

            backTrack(nums, target, i); // 递归

            // 复原现场
            path.removeLast();
            curSum -= nums[i];
        }
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }
        // 递归检索组合
        backTrack(candidates, target, 0);
        return ans;
    }


}
