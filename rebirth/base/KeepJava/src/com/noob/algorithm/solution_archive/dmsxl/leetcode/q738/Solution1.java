package com.noob.algorithm.solution_archive.dmsxl.leetcode.q738;

/**
 * 738 单调递增的数字
 */
public class Solution1 {

    // 贪心：找到非单调递增的标记位
    public int monotoneIncreasingDigits(int n) {
        char[] nums = String.valueOf(n).toCharArray();
        // 逆序遍历每个数字,如果发现当前数字大于下一位数字,就将当前数字-1,记录这个位置
        int idx = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                nums[i]--;
                idx = i; // 记录这个位置
            }
        }

        // 如果idx存在，则将当前位置后面的所有位数设为9
        if (idx != -1) {
            for (int i = idx + 1; i < nums.length; i++) {
                nums[i] = '9';
            }
        }

        // 返回结果
        return Integer.valueOf(String.valueOf(nums));
    }

}