package com.noob.algorithm.daily.archive.plan02.day03.p005;

/**
 * 🔴 239 滑动窗口最大值 - https://leetcode.cn/problems/sliding-window-maximum/submissions/598655671/
 */
public class Solution239_01 {

    /**
     * 思路分析：模拟法（滑动窗口，计算每个窗口的最大值，封装结果）
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 定义结果集
        int[] res = new int[n - k + 1];
        // int cur = 0; // 滑动窗口填充指针
        // 遍历数组
        for (int i = 0; i < n - k + 1; i++) { // i 的有效取值为[0,n-k]
            // 滑动窗口有效取值[i,i+k-1]
            res[i] = getMaxByRange(nums, i, i + k - 1);
        }
        // 返回结果集
        return res;
    }

    // 计算指定数组范围中的max（即窗口内的max,取值限定[start,end]）
    private int getMaxByRange(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }
}
