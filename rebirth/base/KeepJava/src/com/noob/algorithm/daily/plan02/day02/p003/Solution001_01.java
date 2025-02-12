package com.noob.algorithm.daily.plan02.day02.p003;

import java.util.HashMap;
import java.util.Map;

/**
 * 🟢 001 两数之和 - https://leetcode.cn/problems/happy-number/submissions/598388261/
 */
public class Solution001_01 {
    /**
     * 思路分析：找出数组中满足a+b=target的数组元素对应下标
     * 哈希表思路
     */
    public int[] twoSum(int[] nums, int target) {
        // 定义map存储元素及对应下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        // 不存在
        return new int[]{-1, -1};
    }
}
