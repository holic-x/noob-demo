package com.noob.algorithm.daily.plan03.hot100_daily.day01.p001;

/**
 * 🟡 209 长度最小的子数组 - https://leetcode.cn/problems/minimum-size-subarray-sum/
 * 求满足数组中其总和大于等于target的长度最小的子数组
 */
public class Solution209_01 {

    /**
     * 思路分析：滑动窗口思路
     */
    public int minSubArrayLen(int target, int[] nums) {

        // 定义滑动窗口的边界指针
        int left = 0, right = 0;

        int winCnt = 0;

        int minLen = Integer.MAX_VALUE;
        while (right < nums.length) {
            // 将元素载入窗口
            winCnt += nums[right];
            // 判断当前窗口与target的大小，如果超出目标值则不断移出左侧元素
            while (winCnt >= target) {
                // 记录minLen
                minLen = Math.min(minLen, right - left + 1);
                // 移除左边界值元素
                winCnt -= nums[left++];
            }
            // 右侧指针处理完成，移动
            right++;
        }

        // 返回结果
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
