package com.noob.algorithm.daily.plan02.day07.p020;

import java.util.Arrays;

/**
 * 🟡 738 单调递增的数字 - https://leetcode.cn/problems/monotone-increasing-digits/description/
 */
public class Solution738_01 {

    /**
     * 思路分析：给定整数n，返回小于或等于n的最大数字（数字呈单调递增：从左到右相邻数位满足x<=y）
     */
    public int monotoneIncreasingDigits(int n) {
        char[] nums = String.valueOf(n).toCharArray();
        // ① 逆序遍历每个数字，如果当前的数字大于其后面一位的数字，则执行-1操作并记录该位置
        int idx = -1; // 332-322-222-299(处理9)
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                nums[i]--;
                idx = i;
            }
        }

        // ② 将[idx+1,n)范围内的元素全部改为9(基于idx存在的情况)
        if (idx != -1) {
            for (int i = idx + 1; i < nums.length; i++) {
                nums[i] = '9';
            }
        }

        // ③ 返回处理后的nums
        return Integer.valueOf(String.valueOf(nums));
    }

}
