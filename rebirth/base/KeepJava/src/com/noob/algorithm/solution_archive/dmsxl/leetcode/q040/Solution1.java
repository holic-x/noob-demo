package com.noob.algorithm.solution_archive.dmsxl.leetcode.q040;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 040 组合总和II
 */
public class Solution1 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    List<Integer> curPath = new ArrayList<>(); // 定义当前路径
    int curPathSum = 0; // 同步记录当前路径和

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        // 调用回溯方法
        backTrack(candidates, target, 0);
        // 返回结果
        return res;
    }


    // 回溯算法 ❌❌❌❌❌ 超时 ❌❌❌❌❌
    public void backTrack(int[] nums, int target, int startIndex) {
        // 递归出口：遍历到集合末尾或者找到目标target
        if (startIndex == nums.length) { // 元素不允许重复，则说明遍历到数组末尾即结束
            return;
        }

        if (curPathSum == target) {
            // 处理结果(需判断重复组合)
            if(!res.contains(new ArrayList<>(curPath))){
                res.add(new ArrayList<>(curPath)); // 将当前路径加入结果集（需new一个对象，避免引用影响）
            }
            return;
        }

        // 回溯过程
        for (int i = startIndex; i < nums.length; i++) {
            // 1.处理节点
            curPath.add(nums[i]);
            curPathSum += nums[i];
            // 2.递归
            backTrack(nums, target, i + 1); // 不允许数组元素重复使用，此处从当前遍历节点位置的下一位开始
            // 3.复原现场
            curPath.remove(curPath.size() - 1);
            curPathSum -= nums[i];
        }
    }
}
