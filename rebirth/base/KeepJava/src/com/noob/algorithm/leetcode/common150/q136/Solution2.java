package com.noob.algorithm.leetcode.common150.q136;

import java.util.HashSet;
import java.util.Set;

/**
 * 136 只出现1次的数字
 */
public class Solution2 {
    public int singleNumber(int[] nums) {
        /**
         * 异或思路：
         * 1. 0 和 任何数异或都等于它本身
         * 2. 任何数 和 其本身异或等于0
         * 3.满足交换律
         */
        int res = 0;
        for(int num : nums){
            res ^= num;
        }
        return res;
    }
}
