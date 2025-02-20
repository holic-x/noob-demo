package com.noob.algorithm.plan_archive.plan02.hot100.day08.p026;

/**
 * 🟡 494 目标和 - https://leetcode.cn/problems/target-sum/description/
 */
public class Solution494_02 {
    /**
     * 思路分析：给定要给数组，可以在元素前加入符号+/-然后串联起来得到表达式，返回通过上述方法构造的运算结果为target的不同表达式数目
     * 回溯法
     */
    public int findTargetSumWays(int[] nums, int target) {
        // 调用回溯算法
        backTrack(0, nums, target);
        return ans;
    }

    private int ans = 0;
    private int curPathSum = 0; // 路径对应元素处理和

    // 回溯算法
    private void backTrack(int idx, int[] nums, int target) {
        // 递归出口
        if (idx == nums.length) {
            // 结果集处理(遍历到末尾)
            if (curPathSum == target) {
                ans++;
            }
            return;
        }

        // 回溯处理(分+、-两种情况讨论)
        curPathSum += nums[idx];
        backTrack(idx + 1, nums, target);
        curPathSum -= nums[idx];

        curPathSum -= nums[idx];
        backTrack(idx + 1, nums, target);
        curPathSum += nums[idx];
    }

}