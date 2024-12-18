package com.noob.algorithm.dmsxl.leetcode.q922;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 922 按照奇偶排序数组II
 */
public class Solution1 {

    // 分类遍历法
    public int[] sortArrayByParityII(int[] nums) {
        int len = nums.length;
        // 分别定义两个集合存储奇数、偶数列表
        List<Integer> oddNums = new ArrayList<>();
        List<Integer> evenNums = new ArrayList<>();

        // 遍历数组封装奇数、偶数
        for (int num : nums) {
            if (num % 2 == 1) {
                oddNums.add(num);
            } else if (num % 2 == 0) {
                evenNums.add(num);
            }
        }

        // 封装结果集
        int[] res = new int[len];
        // 分别填充奇数、偶数
        for (int i = 0; i < len / 2; i++) { // 奇数偶数对半
            res[2 * i] = evenNums.get(i);
            res[2 * i + 1] = oddNums.get(i);
        }
        // 返回结果
        return res;
    }
}
