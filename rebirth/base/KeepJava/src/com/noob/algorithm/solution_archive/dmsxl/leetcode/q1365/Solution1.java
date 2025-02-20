package com.noob.algorithm.solution_archive.dmsxl.leetcode.q1365;

/**
 * 🟢 1365 有多少小于当前数字的数字
 */
public class Solution1 {

    // 遍历检索
    public int[] smallerNumbersThanCurrent(int[] nums) {
        // 定义统计结果数组
        int[] res = new int[nums.length];

        // 外层确定nums[i]
        for (int i = 0; i < nums.length; i++) {
            // 内层遍历数组，并统计小于当前元素的元素个数
            int cnt = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    cnt++;
                }
            }
            // 统计完成，处理结果
            res[i] = cnt;
        }

        // 返回结果
        return res;
    }

}
