package com.noob.algorithm.daily.plan03.hot100_daily.day08.p027;

/**
 * 🟡 377 组合总和IV - https://leetcode.cn/problems/combination-sum-iv/description/
 */
public class Solution377_01 {


    /**
     * 思路分析：
     */
    public int combinationSum4(int[] nums, int target) {
        backTrack(nums, target);
        return cnt;
    }

    int curPathSum = 0;
    int cnt = 0;

    private void backTrack(int[] nums, int target) {

        // 校验过程中是否存在目标组合
        if (curPathSum == target) {
            cnt++; // 收集结果
            return;
        }

        // 校验是否超出target，提前剪枝
        if (curPathSum > target) {
            return;
        }

        // 回溯处理（选择元素）
        for (int i = 0; i < nums.length; i++) {
            curPathSum += nums[i];
            backTrack(nums, target);
            curPathSum -= nums[i];
        }

    }


}
