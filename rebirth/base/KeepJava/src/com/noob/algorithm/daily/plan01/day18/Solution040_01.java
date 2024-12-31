package com.noob.algorithm.daily.plan01.day18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 040 组合总和II
 */
public class Solution040_01 {

    public List<List<Integer>> res = new ArrayList<>(); // 结果集定义
    public List<Integer> curPath = new ArrayList<>(); // 当前遍历路径
    public int curPathSum = 0; // 当前遍历路径和


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序
        Arrays.sort(candidates);
        // 调用回溯算法
        backTrack(candidates, target, 0);
        // 返回结果集
        return res;
    }

    // 回溯算法
    public void backTrack(int[] nums, int target, int idx) {
        // 递归出口
        if (curPathSum == target) {
            if(!res.contains(curPath)){
                res.add(new ArrayList<>(curPath));
            }
            return;
        }

        // 限定每个元素只能使用1次，此处如果选定路径大于nums的个数则退出
        if (curPath.size() > nums.length) {
            return;
        }

        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            curPath.add(nums[i]);
            curPathSum += nums[i];
            backTrack(nums, target, i+1);
            curPath.remove(curPath.size() - 1);
            curPathSum -= nums[i];
        }
    }


}
