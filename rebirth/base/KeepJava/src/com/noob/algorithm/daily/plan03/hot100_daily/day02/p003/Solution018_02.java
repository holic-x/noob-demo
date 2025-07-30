package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🟡 018 四数之和 - https://leetcode.cn/problems/4sum/description/
 */
public class Solution018_02 {

    /**
     * 思路分析：寻找满足a+b+c+d=target的四元组且不重复
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);

        // 定义结果集合
        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;

        // 类似三元组思路求解
        for (int a = 0; a < n - 3; a++) {
            for (int b = a + 1; b < n - 2; b++) {
                // left、right 指针处理
                int left = b + 1, right = n - 1;
                while (left < right) {
                    // 校验当前四元组之和与target
                    int curSum = nums[a] + nums[b] + nums[left] + nums[right];
                    if (curSum == target) {
                        // 记录满足条件的四元组
                        List<Integer> temp = Arrays.asList(nums[a], nums[b], nums[left], nums[right]);
                        if (!ans.contains(temp)) { // todo 去重优化
                            ans.add(temp);
                        }
                        // 校验完成，移动指针
                        left++;
                        right--;
                    } else if (curSum < target) {
                        // curSum 比 target 小，需要让其变大以接近目标值
                        left++;
                    } else if (curSum > target) {
                        // curSum 比 target 大，需要让其变小以接近目标值
                        right--;
                    }
                }
            }
        }

        // 返回构建的结果集合
        return ans;
    }


}
