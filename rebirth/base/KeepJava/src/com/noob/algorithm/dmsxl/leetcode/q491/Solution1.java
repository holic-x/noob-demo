package com.noob.algorithm.dmsxl.leetcode.q491;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 491 非递减子序列
 */
public class Solution1 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    List<Integer> curPath = new ArrayList<>(); // 定义当前路径

    public List<List<Integer>> findSubsequences(int[] nums) {
        // 调用回溯方法
        backTrack(nums, 0);
        // 返回结果
        return res;
    }

    // 回溯方法
    public void backTrack(int[] nums, int startIndex) {

        // 满足条件则加载结果集（递增子序列）
        if (curPath.size() >= 2) {
            res.add(new ArrayList<>(curPath)); // 加入满足条件的路径
        }

        HashSet<Integer> hs = new HashSet<>();

        // 回溯过程
        for (int i = startIndex; i < nums.length; i++) {
            // 剪枝：如果当前路径的最后一个元素大于当前遍历元素值（说明出现降序，可以跳过这个分支），或者当前元素已经遍历过了
            if (!curPath.isEmpty() && curPath.get(curPath.size() - 1) > nums[i] || hs.contains(nums[i])) {
                continue;
            }
            hs.add(nums[i]);

            curPath.add(nums[i]); // 处理节点
            backTrack(nums, i + 1); // 递归
            curPath.remove(curPath.size() - 1); // 复原现场
        }
    }

}
