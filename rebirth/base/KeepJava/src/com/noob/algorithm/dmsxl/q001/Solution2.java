package com.noob.algorithm.dmsxl.q001;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {

    // 哈希表
    public int[] twoSum(int[] nums, int target) {
        // 定义哈希表存储元素：key(nums[i]) value(i 对应数组下标)
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i}; // 遍历过程中校验是否现存满足两数之和为target的内容
            } else {
                map.put(nums[i], i); // 加入哈希集合
            }
        }
        // 没有满足两数之和为target的场景
        return new int[]{0, 0};
    }

}
