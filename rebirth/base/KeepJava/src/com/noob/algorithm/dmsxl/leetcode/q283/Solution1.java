package com.noob.algorithm.dmsxl.leetcode.q283;

/**
 * 🟢 283 移动零
 */
public class Solution1 {

    // 遍历法
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        // 定义辅助数组存储结果
        int[] res = new int[len];
        int idx = 0;  // 记录新数组填充位置
        int cnt = 0; // 统计0的个数
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                res[idx++] = nums[i];
            } else {
                cnt++;
            }
        }
        // 尾部补0
        while (cnt-- > 0) {
            res[idx++] = 0;
        }

        // 将数组填充回原数组
        for (int i = 0; i < len; i++) {
            nums[i] = res[i];
        }
    }
}
