package com.noob.algorithm.dmsxl.leetcode.q053;

/**
 * 053 最大子数组和
 */
public class Solution4 {

    // 动态规划
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // 特例判断
        if (n == 1) {
            return nums[0];
        }

        // 1.定义preMax始终指向以上一个元素结尾构成的连续子数组的最大值(滚动变量)
        int preMax = nums[0]; // i=0只有一种情况(自成一派)，因此初始化为nums[0]
        // 2.遍历处理
        int maxVal = nums[0]; // 从第一个元素开始同步更新
        for (int i = 1; i < n; i++) {
            int curMax = Math.max(nums[i], preMax + nums[i]);
            maxVal = Math.max(maxVal, curMax); // 更新最大值
            // 操作完成，更新preMax
            preMax = curMax;
        }

        // 返回结果
        return maxVal; // 需要从每个可能结尾的最大连续子数组中择选最大
    }

}
