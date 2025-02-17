package com.noob.algorithm.daily.plan02.day06.p014;

import java.util.ArrayList;
import java.util.List;

public class Solution039_01 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    List<Integer> path = new ArrayList<>(); // 定义路径
    int curPathSum = 0; // 定义当前path对照的元素和

    /**
     * 思路分析：给定无重复元素的整数数组，选出可以使得数字和为target的不同组合(同一个数可以使用多次)
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 调用回溯算法
        backTrack(0, candidates, target);
        // 返回结果集
        return res;
    }

    // 定义回溯算法
    private void backTrack(int idx, int[] nums, int target) {
        // 封装结果集
        if (curPathSum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 剪枝(由于无法固定递归层数(元素可重复利用)，此处需要进行剪枝 如果sum大于target则无需继续递归（剪枝）)
        if (curPathSum > target) {
            return;
        }

        // 调用回溯算法
        for (int i = idx; i < nums.length; i++) {
            path.add(nums[i]);
            curPathSum += nums[i];
            backTrack(i, nums, target);
            path.remove(path.size() - 1);
            curPathSum -= nums[i];
        }
    }

}
