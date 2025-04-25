package com.noob.algorithm.daily.plan03.hot100_random.day02.p003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 018 四数之和 - https://leetcode.cn/problems/4sum/description/
 */
public class Solution018_02 {

    /**
     * 思路分析：
     * 求满足a+b+c+d=target的不重复的四元组
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        int n = nums.length;

        // 定义结果集合
        List<List<Integer>> ans = new ArrayList<>();

        // 对数组进行排序，便于去重处理
        Arrays.sort(nums);

        for (int a = 0; a < n; a++) {

            // 剪枝
            if (nums[a] > target) {
                continue;
            }

            // 去重：连续重复跳过
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }

            // 固定a 继续筛选[b,c,d]
            for (int b = a + 1; b < n; b++) {
                // 剪枝
                if (nums[a] + nums[b] > target) {
                    continue;
                }

                // 去重：连续重复跳过
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }

                // 固定b 继续筛选[c,d] （双指针）
                int c = b + 1, d = n - 1;
                while (c < d) {
                    // 筛选[c,d]
                    int curSum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (curSum == target) {
                        // 载入结果集合
                        List<Integer> list = Arrays.asList(nums[a], nums[b], nums[c], nums[d]);
                        ans.add(list); // 去重在选择元素过程中处理

                        // 去重：连续重复跳过
                        while (c < d && nums[c] == nums[c + 1]) {
                            c++;
                        }

                        while (c < d && nums[d] == nums[d - 1]) {
                            d--;
                        }

                        // 指针移动继续寻找下一个组合
                        c++;
                        d--;


                    } else if (curSum < target) {
                        // 让curSum变大，以接近target
                        c++;
                    } else if (curSum > target) {
                        // 让curSum变小，以接近target
                        d--;
                    }
                }
            }
        }

        // 返回结果集
        return ans;

    }
}
