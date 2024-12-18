package com.noob.algorithm.dmsxl.leetcode.q189;

/**
 * 🟡189 轮转数组
 */
public class Solution2 {

    // 遍历
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len; // 获取真正需要的轮转次数

        // 根据题意分析向右轮转k次，实际上就是将元素末尾k个元素提到数组前面
        int[] res = new int[len];
        int idx = 0;// idx指向当前数组填充位置
        // 先遍历后k个元素
        for (int i = len - k; i < len; i++) {
            res[idx++] = nums[i];
        }
        // 再遍历前n-k个元素
        for (int i = 0; i < len - k; i++) {
            res[idx++] = nums[i];
        }

        // 数组回填
        for (int i = 0; i < len; i++) {
            nums[i] = res[i];
        }

    }
}
