package com.noob.algorithm.daily.plan01.archive.day23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 🟢 001 两数之和 - https://leetcode.cn/problems/two-sum/
 */
public class Solution001_01 {

    /**
     * 哈希表思路
     */
    public int[] twoSum(int[] nums, int target) {
        // 构建哈希表存储元素
        HashMap<Integer, Integer> map = new HashMap<>();
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
