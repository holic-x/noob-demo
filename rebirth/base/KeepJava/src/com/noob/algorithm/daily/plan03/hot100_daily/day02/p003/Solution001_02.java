package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.HashMap;

/**
 * 🟢 001 两数之和 - https://leetcode.cn/problems/two-sum/description/
 */
public class Solution001_02 {
    /**
     * 思路分析：双层循环暴力破解
     */
    public int[] twoSum(int[] nums, int target) {

        int n = nums.length;

        // 计算[i,j]范围内子数组的元素和
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int curSum = nums[i] + nums[j];
                // 校验curSum与target
                if (curSum == target) {
                    // 找到目标元素
                    return new int[]{i, j};
                }
            }
        }

        // 无满足条件数据
        return new int[]{-1, -1};
    }
}
