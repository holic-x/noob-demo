package com.noob.algorithm.daily.plan01.day18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 039 组合总和
 */
public class Solution039_02 {
    public List<List<Integer>> res = new ArrayList<>(); // 记录结果集
    public List<Integer> curPath = new ArrayList<>(); // 记录当前路径
    public int curPathSum = 0; // 记录当前路径和

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 排序处理
        Arrays.sort(candidates);
        // 调用回溯算法
        backTrack(candidates, target,0);
        // 返回结果集
        return res;
    }

    // 回溯算法: 排序 + 剪枝优化
    public void backTrack(int[] nums, int target, int idx) {
        // 递归出口
        if (curPathSum == target) {
            res.add(new ArrayList<>(curPath));
        }

        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            // nums为排序后的数组，因此此处可以进行剪枝判断
            if(curPathSum+nums[i]>target){
                break; // 如果当前这个路径和遍历元素的累加和大于target，则后续的节点累加和更加不可能构成target，直接跳出
            }

            curPath.add(nums[i]);
            curPathSum += nums[i];
            backTrack(nums, target, i);
            curPath.remove(curPath.size() - 1);
            curPathSum -= nums[i];
        }
    }
}
