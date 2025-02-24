package com.noob.algorithm.solution_archive.leetcode.common150.q080;

import java.util.*;

/**
 * 80 删除数组中重复的元素II（允许重复次数超出2的可以最多出现两次）
 */
public class Solution1 {

    /**
     * 计数法
     */
    public int removeDuplicates(int[] nums) {
        // 定义map存储每个元素出现的次数
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            // 如果不存在则为0，存在则加1
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // 遍历Map集合，获取满足条件的元素长度
        int cur = 0;
        Set<Integer> keys = map.keySet();
        Iterator<Integer> iterator = keys.iterator();
        while (iterator.hasNext()) {
            int key = iterator.next();
            int value = map.get(key);
            // value最多为2，value超出2次设置为2
            value = value > 2 ? 2 : value;
            for (int i = 0; i < value; i++) {
                nums[cur++] = key;
            }
        }
        return cur;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        Solution1 solution = new Solution1();
        solution.removeDuplicates(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i + ":" + nums[i]);
        }
        System.out.println();
    }

}
