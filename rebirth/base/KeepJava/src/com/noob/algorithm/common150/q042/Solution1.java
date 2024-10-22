package com.noob.algorithm.common150.q042;

/**
 * 42 接雨水
 */
public class Solution1 {

    public int trap(int[] height) {
        int res = 0;

        /**
         * 接雨水：计算每个柱子可以接的雨水量，然后进行累加
         * 每个柱子可接雨水量=min(左侧最大前缀,右侧最大后缀)-柱子高度
         */
        // 先分别计算每个元素的右侧最大后缀、左侧最大前缀
        int n = height.length;
        int[] leftMax = new int[n];
        leftMax[0] = height[0]; // 初始化
        int[] rightMax = new int[n];
        rightMax[n-1] = height[n - 1]; // 初始化

        // 计算左侧最大前缀
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        // 计算右侧最大后缀
        for(int i=n-2;i>=0;i--){
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        // 累计每个柱子可接雨水量(水往低处流，选择较小的柱子然后减去自身高度)
        for(int i=0;i<n;i++){
            res += Math.min(leftMax[i],rightMax[i]) - height[i];
        }

        // 返回结果
        return res;
    }
}
