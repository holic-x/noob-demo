package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 018 四数之和 - https://leetcode.cn/problems/4sum/description/
 */
public class Solution018_01 {

    /**
     * 思路分析：寻找满足a+b+c+d=target的四元组且不重复
     * 思路：暴力思路
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);

        // 定义结果集合
        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;
        for (int a = 0; a < n - 3; a++) {
            for (int b = a + 1; b < n - 2; b++) {
                for (int c = b + 1; c < n - 1; c++) {
                    for (int d = c + 1; d < n; d++) {
                        int curSum = nums[a] + nums[b] + nums[c] + nums[d];
                        if (curSum == target) {
                            List<Integer> temp = Arrays.asList(nums[a], nums[b], nums[c], nums[d]);
                            if (!ans.contains(temp)) {
                                ans.add(temp);
                            }
                        }
                    }
                }
            }
        }

        // 返回构建的结果集合
        return ans;
    }

}
