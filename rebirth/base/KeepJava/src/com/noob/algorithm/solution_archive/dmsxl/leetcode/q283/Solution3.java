package com.noob.algorithm.solution_archive.dmsxl.leetcode.q283;

/**
 * 🟢 283 移动零
 */
public class Solution3 {
    // 双指针法
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        // 定义辅助数组存储结果
        int slowIdx = 0; // 定义同向指针同时出发，往前覆盖
        for (int fastIdx = 0; fastIdx < len; fastIdx++) {
            if (nums[fastIdx] != 0) {
                nums[slowIdx++] = nums[fastIdx]; // 覆盖
            }
        }
        // 上述遍历完成，继续从slowIdx指向位置出发填充剩余的0（因为移除的元素为0，所以直接覆盖即可）
        for (int i = slowIdx; i < len; i++) {
            nums[i] = 0;
        }
    }
}
