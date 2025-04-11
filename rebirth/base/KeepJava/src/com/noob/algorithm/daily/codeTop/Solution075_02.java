package com.noob.algorithm.daily.codeTop;

import java.util.Map;
import java.util.TreeMap;

/**
 * 🟡 075 颜色分类 -  https://leetcode.cn/problems/sort-colors/description/
 */
public class Solution075_02 {

    /**
     * 红白蓝(0、1、2)三色，按照红白蓝颜色排序,让相同颜色的元素相邻
     * 思路：排序算法
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }
}
