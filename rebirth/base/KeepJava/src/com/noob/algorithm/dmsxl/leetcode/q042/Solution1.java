package com.noob.algorithm.dmsxl.leetcode.q042;

import com.noob.algorithm.dmsxl.util.PrintUtil;

/**
 * 042 接雨水
 */
public class Solution1 {

    // 动态规划：列维度
    public int trap(int[] height) {
        int len = height.length;
        // 1.求数组元素的最大前缀
        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        System.out.println("leftMax[]:");
        PrintUtil.print(leftMax);

        // 2.求数组元素的最大后缀
        int[] rightMax = new int[len];
        rightMax[len - 1] = height[len - 1];
        for (int j = len - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }
        System.out.println("rightMax[]:");
        PrintUtil.print(rightMax);

        // 3.求雨水总量
        int res = 0;
        for (int i = 0; i < len; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        // 返回结果
        return res;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Solution1 solution1 = new Solution1();
        System.out.println("res:" + solution1.trap(height));
    }
}
