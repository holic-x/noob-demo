package com.noob.algorithm.plan_archive.plan02.hot100.day01.p001;

/**
 * 🟡 209 长度最小的子数组 - https://leetcode.cn/problems/minimum-size-subarray-sum/
 * 求满足数组中其总和大于等于target的长度最小的子数组
 */
public class Solution209_02 {

    /**
     * 思路分析：模拟法（统计每个满足条件的子数组长度，计算minSubLen）
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minSubLen = Integer.MAX_VALUE; // 定义初始值
        int n = nums.length;
        // 双层遍历进行子数组统计(计算[i,j]区间范围内的子数组元素总和，更新minSubLen)
        for (int i = 0; i < n; i++) { // i 起点
            int curSum = 0;
            for (int j = i; j < n; j++) { // j 终点
                curSum += nums[j];
                // 校验curSum与target
                if (curSum >= target) {
                    minSubLen = Math.min(minSubLen, j - i + 1);
                }
            }
        }
        // 返回结果
        return minSubLen == Integer.MAX_VALUE ? 0 : minSubLen; // 如果为初始值则说明不存在满足条件的子数组
    }
}
