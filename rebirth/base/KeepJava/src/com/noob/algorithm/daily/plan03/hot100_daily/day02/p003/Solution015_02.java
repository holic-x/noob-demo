package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 015 三数之和 - https://leetcode.cn/problems/3sum/description/
 */
public class Solution015_02 {
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

            // 剪枝：如果nums[i]>0 则后面组合数得到的和只会更大
            if (nums[i] > 0) {
                break;
            }

            // 剪枝：去重处理
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

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
                    /*
                    if (!ans.contains(curPath)) {
                        ans.add(curPath);
                    }
                     */
                    ans.add(curPath); // 通过剪枝、去重校验来优化，不需要额外判断集合
                    // 本次操作处理完结，指针移动
                    left++;
                    right--;

                    // 剪枝处理：跳过left或者right连续相同的重复项
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
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
