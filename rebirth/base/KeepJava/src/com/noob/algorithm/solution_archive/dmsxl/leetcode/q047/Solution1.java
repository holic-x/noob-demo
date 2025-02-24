package com.noob.algorithm.solution_archive.dmsxl.leetcode.q047;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 047 全排列II
 */
public class Solution1 {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> curPath = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);

        // 排序
        Arrays.sort(nums);

        // 调用回溯方法
        backTrack(nums, used);
        // 返回结果
        return res;
    }

    // 回溯算法
    public void backTrack(int[] nums, boolean[] used) {
        if (curPath.size() == nums.length) {
            res.add(new ArrayList<>(curPath));
            return;
        }
        // 回溯过程
        for (int i = 0; i < nums.length; i++) {
            /**
             * used[i - 1] == true 说明同⼀树⽀nums[i - 1]使⽤过;
             * used[i - 1] == false 说明同⼀树层nums[i - 1]使⽤过;
             * 如果同⼀树层nums[i - 1]使⽤过则直接跳过(i > 0 && nums[i - 1] == nums[i],同一数层中使用过的元素可以跳过（需对nums排序才能判断相邻）)
             */
            if ((i > 0 && nums[i - 1] == nums[i]) && used[i - 1] == false) { // 对于路径而言 used[i - 1]要一直为true或者一直为false 校验才有意义
                continue;
            }
            // 如果同⼀树⽀nums[i]没使⽤过开始处理
            if (used[i] == false) {
                // 回溯过程核心
                curPath.add(nums[i]);// 处理节点
                used[i] = true;
                backTrack(nums, used); // 递归处理
                curPath.remove(curPath.size() - 1); // 复原现场
                used[i] = false;
            }
        }
    }

}
