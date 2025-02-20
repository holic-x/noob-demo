package com.noob.algorithm.leetcode.common150.q001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 001 两数之和
 */
public class Solution2 {
    // 哈希表
    public int[] twoSum(int[] nums, int target) {
        // 定义哈希表存储元素（key：元素 value：元素对应下标索引）
        Map<Integer,Integer> map = new HashMap<>();

        // 遍历元素，依次加入元素并校验
        for(int i=0;i<nums.length;i++){
            // 满足条件则返回
            if(map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }
            // 将元素加入集合
            map.put(nums[i],i);
        }

        return new int[]{};
    }
}
