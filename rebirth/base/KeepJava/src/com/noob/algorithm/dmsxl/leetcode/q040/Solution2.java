package com.noob.algorithm.dmsxl.leetcode.q040;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 040 组合总和II
 */
public class Solution2 {

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


    // 回溯算法 优化版本
    public void backTrack(int[] nums, int target, int startIndex) {
        // 递归出口：遍历到集合末尾或者找到目标target
        if (curPathSum > target) { // 【优化1】剪枝
            return;
        }

        if (curPathSum == target) {
            // 处理结果(需判断重复组合) 【优化2】去重优化（在回溯过程中进行剪枝，此处则不需要重复校验，直接添加）
            res.add(new ArrayList<>(curPath)); // 将当前路径加入结果集（需new一个对象，避免引用影响）
            return;
        }

        // 回溯过程
        for (int i = startIndex; i < nums.length; i++) {
            /*
            if (curPathSum + nums[i] > target) { // 剪枝：和递归出口那里的剪枝作用等效，校验实际不同而已
                break;
            }
             */

            // 【优化2】去重，要对同一树层使用过的元素进行跳过（可以画图理解）
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }

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
