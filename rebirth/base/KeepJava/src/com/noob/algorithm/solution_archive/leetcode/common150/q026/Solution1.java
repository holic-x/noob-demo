package com.noob.algorithm.solution_archive.leetcode.common150.q026;

import java.util.*;

/**
 * 26 删除有序数组的重复项
 */
public class Solution1 {
    /**
     * 基础思路：判断重复元素概念，将所有非重复元素加入Set集合（去重），然后转化为nums即可
     */
    public int removeDuplicates(int[] nums) {
        // set存储不重复元素
        HashSet<Integer> set = new HashSet<>();
        // 定义数组元素的存储位置
        int cur = 0;
        for(int i=0;i<nums.length;i++){
            if(!set.contains(nums[i])){
                set.add(nums[i]);
                nums[cur++] = nums[i]; // 直接覆盖值依次存储非重复元素
            }
        }
        return cur;
    }
}
