package com.noob.algorithm.daily.archive.plan02.hot100.day11.p033;

/**
 * 🔴 042 接雨水 - https://leetcode.cn/problems/trapping-rain-water/description/
 */
public class Solution042_01 {

    /**
     * 思路分析：计算每个格子的接水量
     */
    public int trap(int[] height) {
        int n = height.length;
        // 计算每个柱子的左侧的最高点
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 计算每个柱子右侧的最高点
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 计算每个柱子的接水量并累加
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return cnt;
    }

}
