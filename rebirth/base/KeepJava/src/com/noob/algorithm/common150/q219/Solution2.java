package com.noob.algorithm.common150.q219;

import java.util.HashMap;
import java.util.Map;

/**
 * 219 给你一个整数数组 `nums` 和一个整数 `k` ，判断数组中是否存在两个 不同的索引 `i` 和 `j` ，
 * 满足 `nums[i] == nums[j]` 且 `abs(i - j) <= k` 。如果存在，返回 `true` ；否则，返回 `false`
 */
public class Solution2 {

    // 哈希表
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        // 构建哈希表存储元素及其对应数组下标索引
        Map<Integer, Integer> map = new HashMap<>();

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 判断是否符合匹配的条件
            if (map.containsKey(nums[i]) && Math.abs(i - map.get(nums[i])) <= k) {
                return true;
            }
            // 将元素加入集合
            map.put(nums[i], i);
        }
        // 没有找到，返回false
        return false;
    }
}
