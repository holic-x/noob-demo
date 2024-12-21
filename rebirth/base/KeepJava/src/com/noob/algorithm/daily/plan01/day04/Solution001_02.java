package com.noob.algorithm.daily.plan01.day04;

import java.util.HashMap;

/**
 * 🟢 001 两数之和
 */
public class Solution001_02 {
    /**
     * 哈希法：将nums集合元素加入哈希表中，然后快速校验target-x是否存在
     * 拆分步骤此处分为两步：
     * ① 将nums数组元素加入哈希表map（Map<元素，索引>）
     * ② 再次遍历nums数组，在map寻找target-nums[i]
     * 也可简化为一次遍历集合，边比较边处理（简化版本写法）
     */
    public int[] twoSum(int[] nums, int target) {
        // 构建哈希表存储数组元素及关联下标
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }

        // 未找到满足条件的元素组合
        return new int[]{};
    }
}
