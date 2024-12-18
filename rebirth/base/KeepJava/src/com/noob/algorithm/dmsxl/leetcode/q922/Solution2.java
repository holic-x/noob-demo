package com.noob.algorithm.dmsxl.leetcode.q922;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 922 按照奇偶排序数组II
 */
public class Solution2 {
    // 指针法：双指针分别指向res用于指向存储奇数、偶数的下标位置，一个指针用于遍历元素判断奇偶
    public int[] sortArrayByParityII(int[] nums) {
        int len = nums.length;
        // 分别定义奇数、偶数存储的下标指针
        int oddIdx = 1;
        int evenIdx = 0;
        // 定义结果集
        int[] res = new int[len];
        // 遍历数组封装奇数、偶数
        for (int i = 0; i < len; i++) {
            // 遍历每个元素判奇偶，然后将其放置在对应的位置
            if (nums[i] % 2 == 0) {
                // 放置在偶数位置，随后更新evenIdx（指向下一个存放位置）
                res[evenIdx] = nums[i];
                evenIdx += 2; // 向前移动两位
            } else if (nums[i] % 2 == 1) {
                // 放置在奇数位置，随后更新oddIdx（指向下一个存放位置）
                res[oddIdx] = nums[i];
                oddIdx += 2;
            }
        }
        // 返回结果
        return res;
    }
}
