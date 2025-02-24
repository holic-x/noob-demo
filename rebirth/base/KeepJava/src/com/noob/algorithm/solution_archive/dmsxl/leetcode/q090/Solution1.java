package com.noob.algorithm.solution_archive.dmsxl.leetcode.q090;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 090 子集II
 */
public class Solution1 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    List<Integer> curPath = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 此处排序，用于后续去重判断
        Arrays.sort(nums);
        // 调用回溯算法
        backTrack(nums, 0);
        // 返回结果
        return res;
    }

    // 回溯算法
    public void backTrack(int[] nums, int startIndex) {
        // 将路径载入结果集(判断重复性)
        if (!res.contains(curPath)) {
            res.add(new ArrayList<>(curPath));
        }

        // 递归出口
//        if (startIndex >= nums.length) {
//            return;
//        }

        // 回溯过程
        for (int i = startIndex; i < nums.length; i++) {
            // 处理节点
            curPath.add(nums[i]);
            // 递归
            backTrack(nums, i + 1);
            // 回退,复原现场
            curPath.remove(curPath.size() - 1);
        }
    }

}
