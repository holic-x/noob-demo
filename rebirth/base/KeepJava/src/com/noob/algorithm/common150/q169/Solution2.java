package com.noob.algorithm.common150.q169;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 169 多数元素
 * 多数元素指在数组中出现次数大于n/2的元素
 */
public class Solution2 {

    // 排序法
    public int majorityElement1(int[] nums) {
        // 思路：对数组元素进行排序，然后选择最中间
         Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void main(String[] args) {
        Solution2 solution1 = new Solution2();
        System.out.println(solution1.majorityElement1(new int[]{3,2,3}));
    }
}
