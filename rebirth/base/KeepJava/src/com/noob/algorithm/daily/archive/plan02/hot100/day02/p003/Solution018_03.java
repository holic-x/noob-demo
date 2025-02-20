package com.noob.algorithm.daily.archive.plan02.hot100.day02.p003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 018 四数之和 - https://leetcode.cn/problems/4sum/description/
 */
public class Solution018_03 {

    /**
     * 思路分析：四数之和（求满足a+b+c+d=target的四元组，元素各不相同且元组不能重复）
     * 基于降维的思路，将四层数组转化为三层概念
     * 去重优化（在遍历过程中剪枝去重，而不采用集合去重，以提升效率）
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        // 对数组元素进行排序
        Arrays.sort(nums);

        // 外层确定a
        for (int i = 0; i < n - 3; i++) {
            // 去重剪枝①：如果出现连续重复的a则跳过
            if (i >= 1 && nums[i - 1] == nums[i]) continue;

            // 第2层确定b（三元组处理）
            for (int j = i + 1; j < n - 2; j++) {

                // 去重剪枝②：如果出现连续重复的b则跳过
                if (j > i + 1 && nums[j - 1] == nums[j]) continue;

                // 第3层确定c、d（双指针思路）
                int left = j + 1, right = n - 1;
                while (left < right) {
                    int a = nums[i], b = nums[j], c = nums[left], d = nums[right];
                    int curSum = a + b + c + d;
                    // 根据curSum确定指针移动方向
                    if (curSum == target) {
                        // 满足条件，去重载入结果集(采用剪枝去重处理)
                        res.add(new ArrayList<>(Arrays.asList(a, b, c, d)));

                        // 指针移动
                        left++;
                        right--;

                        // 去重剪枝③：如果出现连续重复的c或者d则跳过
                        while (left < right && nums[left - 1] == nums[left]) left++;
                        while (left < right && nums[right + 1] == nums[right]) right--;
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
