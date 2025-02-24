package com.noob.algorithm.solution_archive.leetcode.common150.q136;

import java.util.HashSet;
import java.util.Set;

/**
 * 136 只出现1次的数字
 */
public class Solution1 {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet();
        for(int i = 0;i<nums.length;i++){
            int cur = nums[i];
            if(set.contains(cur)){
                set.remove(cur);
            }else{
                set.add(cur);
            }
        }
        return set.iterator().next();
    }
}
