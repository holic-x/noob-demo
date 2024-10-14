package com.noob.algorithm.hot100.q002;

/**
 * 2 盛水最多的容器
 * 给定一个长度为 `n` 的整数数组 `height` 。有 `n` 条垂线，第 `i` 条线的两个端点是 `(i, 0)` 和 `(i, height[i])`
 * 找出其中的两条线，使得它们与 `x` 轴共同构成的容器可以容纳最多的水
 * 返回容器可以储存的最大水量
 */
public class Solution {
    /**
     * 思路：双指针（x缩小的时候要尽可能找长的柱子才能使得存储水量变大）
     * 因此可以将题意简化为双指针判断短边并移动短边，因为只有移动短边才有可能使得在x缩小的时候，会不会找到一条更高的柱子
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;// 定义双指针记录选择的柱子
        int max = 0; // 定义面积最大值用作记录
        // 当指针相遇循环结束
        while (left < right) {
            // 记录当下的最大值，并移动短边
            if (height[left] < height[right]) {
                max = Math.max(max, height[left] * (right - left)); // 装水遵循木桶效应
                left++; // 移动短边，寻找下一个可能使得max变大的边
            } else if (height[left] >= height[right]) {
                max = Math.max(max, height[right] * (right - left));// 装水遵循木桶效应
                right--; // 移动短边，寻找下一个可能使得max变大的边
            }
        }
        // 返回结果
        return max;
    }
}
