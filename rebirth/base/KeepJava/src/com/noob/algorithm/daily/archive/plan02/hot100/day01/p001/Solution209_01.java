package com.noob.algorithm.daily.archive.plan02.hot100.day01.p001;

/**
 * 🟡 209 长度最小的子数组 - https://leetcode.cn/problems/minimum-size-subarray-sum/
 * 求满足数组中其总和大于等于target的长度最小的子数组
 */
public class Solution209_01 {

    /**
     * 思路分析：滑动窗口思路
     * 定义一个窗口，窗口中限定刷入满足总和大于等于target的元素，left用于剔除，right用于纳新，计算满足条件的窗口（子数组）长度min
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minSubLen = Integer.MAX_VALUE; // 定义初始值
        int n = nums.length;
        // 定义滑动窗口的左右边界指针
        int left = 0, right = 0;
        // 定义窗口内元素和（随着窗口的滑动变更）
        int curSum = 0;
        while (right < n) {
            // ① 尝试加入新元素
            curSum += nums[right];

            // ② 如果curSum>=target，说明此时窗口内满足要求，记录minSubLen，并通过不断剔除左侧元素进行校验（左边界缩圈，为接收下一个可能的新元素做准备，直到curSum<target）
            while (curSum >= target) {
                minSubLen = Math.min(minSubLen, right - left + 1); // 更新满足条件的子数组的最小长度
                // 剔除左侧元素（不断移出左侧元素，直到curSum<target，为加入下一个新元素做准备）
                curSum -= nums[left++];
            }

            // ③ right 移动（指向下一个要纳入的元素）
            right++; // right 的变更放在后面（前面步骤②需要用到原来的right计算minSubLen）
        }
        // 返回结果
        return minSubLen == Integer.MAX_VALUE ? 0 : minSubLen; // 如果为初始的MAX说明不存在满足条件的子数组
    }
}
