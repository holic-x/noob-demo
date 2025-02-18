package com.noob.algorithm.daily.archive.plan02.day02.p003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 018 四数之和 - https://leetcode.cn/problems/4sum/description/
 */
public class Solution018_02 {

    /**
     * 思路分析：四数之和（求满足a+b+c+d=target的四元组，元素各不相同且元组不能重复）
     * 基于降维的思路，将四层数组转化为三层概念
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        // 对数组元素进行排序
        Arrays.sort(nums);

        // 外层确定a
        for (int i = 0; i < n - 3; i++) {
            int a = nums[i];

            // 第2层确定b
            for (int j = i + 1; j < n - 2; j++) {
                int b = nums[j];
                // 第3层确定c、d（双指针思路）
                int left = j + 1, right = n - 1;
                while (left < right) {
                    int c = nums[left], d = nums[right];
                    int curSum = a + b + c + d;
                    // 根据curSum确定指针移动方向
                    if (curSum == target) {
                        // 满足条件，去重载入结果集
                        List<Integer> temp = new ArrayList<>();
                        temp.add(a);
                        temp.add(b);
                        temp.add(c);
                        temp.add(d);
                        if (!res.contains(temp)) {
                            res.add(temp);
                        }
                        // 指针移动
                        left++;
                        right--;
                    } else if (curSum < target) {
                        // 要让curSum变大才可能接近target
                        left++;
                    } else if (curSum > target) {
                        // 要让curSum变小才可能接近target
                        right--;
                    }
                }
            }
        }
        // 返回结果集
        return res;
    }
}
