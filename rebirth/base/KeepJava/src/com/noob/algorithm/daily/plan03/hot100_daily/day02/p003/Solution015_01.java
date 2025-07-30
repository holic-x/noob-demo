package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 015 三数之和 - https://leetcode.cn/problems/3sum/description/
 */
public class Solution015_01 {
    /**
     * 思路分析：排序 + 双指针思路
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 对数组元素进行排序
        Arrays.sort(nums);

        // 定义结果集
        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;
        // 遍历数组元素，处理满足条件的三元组（和为0且不重复）

        // int curSum = a+b+c
        for (int i = 0; i < n - 2; i++) {
            // 定义双指针
            int left = i + 1, right = n - 1;
            // 指针相遇则循环结束，且不能取相同的数
            while (left < right) {
                int curSum = nums[i] + nums[left] + nums[right];
                if (curSum == 0) {
                    List<Integer> curPath = new ArrayList<>();
                    curPath.add(nums[i]);
                    curPath.add(nums[left]);
                    curPath.add(nums[right]);
                    if (!ans.contains(curPath)) {
                        ans.add(curPath);
                    }
                    // 本次操作处理完结，指针移动
                    left++;
                    right--;
                } else if (curSum < 0) {
                    // curSum<0 需要让目标值变大，则需left指针右移
                    left++;
                } else {
                    // curSum>0 需要让目标值变小，则需right指针左移
                    right--;
                }
            }
        }

        // 返回构建结果
        return ans;
    }

}
