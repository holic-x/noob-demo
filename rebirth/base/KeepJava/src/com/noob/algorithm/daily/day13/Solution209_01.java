package com.noob.algorithm.daily.day13;

/**
 * 🟡 209 长度最小的子数组 - https://leetcode.cn/problems/minimum-size-subarray-sum/
 */
public class Solution209_01 {

    /**
     * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组长度
     */
    public int minSubArrayLen(int target, int[] nums) {

        int minLen = Integer.MAX_VALUE;

        int n = nums.length;
        for (int i = 0; i < n; i++) { // 子数组起点
            int curSum = 0;
            for (int j = i; j < n; j++) { // 子数组终点
                curSum += nums[j]; // 计算子数组和
                // 校验子数组和是否大于等于target，如果满足则记录（寻找最小子数组长度）
                if (curSum >= target) {
                    minLen = Math.min(minLen, j - i + 1); // 更新minLen
                }
            }
        }
        // 返回结果
        return minLen == Integer.MAX_VALUE ? 0 : minLen;

    }

}
