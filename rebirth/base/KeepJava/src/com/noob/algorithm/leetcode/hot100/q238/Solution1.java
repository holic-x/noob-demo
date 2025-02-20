package com.noob.algorithm.leetcode.hot100.q238;

/**
 * 238 除自身以外的乘积
 */
public class Solution1 {
    /**
     * 暴力累乘法
     */
    public int[] productExceptSelf(int[] nums) {
        // 定义返回结果
        int[] res = new int[nums.length];

        // 双层循环进行累乘，排除自身
        for (int i = 0; i < nums.length; i++) {
            res[i] = 1; // 每轮循环初始化res[i]为1（1乘以任何数都为本身）
            for (int j = 0; j < nums.length; j++) {
                if (i != j) { // 排除自身
                    res[i] = nums[j];
                }
            }
        }

        // 返回结果
        return res;
    }
}
