package com.noob.algorithm.leetcode.hot100.q238;

/**
 * 238 除自身以外的乘积
 */
public class Solution2 {

    /**
     * 将问题拆分为求左侧的累乘结果 * 右侧的累乘结果
     */
    public int[] productExceptSelf(int[] nums) {

        // 定义相应结果
        int[] res = new int[nums.length];

        int len = nums.length;

        // 定义每个元素位置上左侧的累乘结果，存储到对应数组位置上（不包括本身）
        int[] left = new int[len];
        left[0] = 1;
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        // 定义每个元素位置上左侧的累乘结果，存储到对应数组位置上（不包括本身）
        int[] right = new int[len];
        right[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        // 遍历数组，封装累乘结果
        for (int i = 0; i < len; i++) {
            res[i] = left[i] * right[i];
        }

        // 返回结果
        return res;
    }
}
