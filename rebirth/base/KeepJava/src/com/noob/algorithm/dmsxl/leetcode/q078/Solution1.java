package com.noob.algorithm.dmsxl.leetcode.q078;

import java.util.ArrayList;
import java.util.List;

/**
 * 078 子集
 */
public class Solution1 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    List<Integer> curPath = new ArrayList<>(); // 定义当前组合

    public List<List<Integer>> subsets(int[] nums) {
        // 调用回溯算法
        backTrack(nums, 0);
        // 返回结果
        return res;
    }

    // 定义回溯算法
    public void backTrack(int[] nums, int startIdx) {

        // 处理结果（求不重复子集收集的是所有的结果，此处不用限定条件）
        res.add(new ArrayList<>(curPath)); // 需确认重复的集合

        // 回溯出口(startIdx==nums.length 表示遍历到数组末尾，退出)
        /*
        if (startIdx == nums.length) {
            return;
        }
         */

        // 回溯过程
        for (int i = startIdx; i < nums.length; i++) {
            curPath.add(nums[i]); // 处理节点
            backTrack(nums, i + 1); // 元素不能重复使用，递归处理
            curPath.remove(curPath.size() - 1); // 复原现场
        }
    }
}
