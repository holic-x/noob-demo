package com.noob.algorithm.daily.plan03.hot100_random.day02.p003;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 🟢 001 两数之和 - https://leetcode.cn/problems/two-sum/description/
 */
public class Solution001_01 {
    /**
     * 思路分析：
     */
    public int[] twoSum(int[] nums, int target) {

        // 定义Map存储已遍历元素和相关索引位置
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            // 加入已遍历元素
            map.put(nums[i], i);
        }

        // 没有找到目标元素
        return new int[]{-1, -1};
    }
}
