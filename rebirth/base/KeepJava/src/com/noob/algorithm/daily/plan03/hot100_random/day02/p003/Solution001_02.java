package com.noob.algorithm.daily.plan03.hot100_random.day02.p003;

import java.util.HashMap;

/**
 * 🟢 001 两数之和 - https://leetcode.cn/problems/two-sum/description/
 */
public class Solution001_02 {
    /**
     * 思路分析：
     * 暴力法（双层循环遍历）
     */
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        // 没有找到目标元素
        return new int[]{-1, -1};
    }
}
