package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.HashMap;

/**
 * 🟢 001 两数之和 - https://leetcode.cn/problems/two-sum/description/
 */
public class Solution001_01 {
    /**
     * 思路分析：哈希表思路
     */
    public int[] twoSum(int[] nums, int target) {
        // 定义哈希表存储已遍历元素 & 对应元素下标
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果存在元素满足a+b=target则返回响应
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            // 载入遍历元素
            map.put(nums[i], i);//修正错误的代码
        }
        // 无满足条件数据
        return new int[]{-1, -1};
    }
}
