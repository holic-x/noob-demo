package com.noob.algorithm.daily.plan02.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 015 三数之和 - https://leetcode.cn/problems/3sum/description/
 */
public class Solution015_01 {
    /**
     * 思路分析：判断是否存在三元组满足nums[i]+nums[j]+nums[k]=0（x+y+z=0）
     * 哈希法：固定1位（x），双指针对向遍历确定y,z
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        // 对数组元素进行排序
        Arrays.sort(nums);
        // 遍历
        for (int i = 0; i < n - 2; i++) {
            // 外层循环确定x
            int x = nums[i];

            // 双指针确定y，z
            int left = i + 1, right = n - 1;
            while (left < right) {
                int y = nums[left], z = nums[right];
                int curSum = x + y + z;
                // 根据curSum与target的关系确定指针移动方向
                if (curSum == 0) {
                    // 处理结果集
                    List<Integer> temp = new ArrayList<>();
                    temp.add(x);
                    temp.add(y);
                    temp.add(z);
                    if (!res.contains(temp)) {
                        res.add(temp);
                    }
                    // 指针后移
                    left++;
                    right--;
                } else if (curSum < 0) {
                    // 需要让curSum变大
                    left++;
                } else if (curSum > 0) {
                    right--;
                }
            }
        }
        // 返回结果集
        return res;
    }

}
