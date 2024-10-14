package com.noob.algorithm.hot100.q001;

import java.util.HashMap;

/**
 * 001.两数之和
 */
public class Solution2 {

    public int[] twoSum(int[] nums, int target) {
        // 哈希法
        HashMap<Integer, Integer> map = new HashMap<>();
        // 一次遍历，看是否满足条件
        for (int i = 0; i < nums.length; i++) {
            // 在遍历的过程中判断是否存在满足的target
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            // 将元素加入哈希集合
            map.put(nums[i], i); // 此处设定了不能使用两次相同的元素，因此此处存储是key-数组元素，value-数组下标
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,3};
        Solution2 solution2 = new Solution2();
        solution2.twoSum(nums, 6);
    }
}
