package com.noob.algorithm.dmsxl.leetcode.q209;

/**
 * 209 长度最小的子数组
 */
public class Solution2 {

    // 暴力法：i 敲定起点，j敲定终点 （相当于把所有子数组都计算一遍）
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;

        int min = Integer.MAX_VALUE; // 最小值

        // 嵌套循环：i 敲定起点，j敲定终点
        for (int i = 0; i < len; i++) { // 注意循环条件设定和数组越界问题
            int curSum = 0;
            for (int j = i; j < len; j++) { // 元素本身也算是一个子数组，此处设定起点相同
                // 计算[i,j]的元素之和
                curSum += nums[j];
                // 判断curSum是否大于等于target
                if (curSum >= target) {
                    min = Math.min(min, j - i + 1);
                }
            }
        }

        // 返回满足条件的最小长度
        return min == Integer.MAX_VALUE ? 0 : min;
    }

}
