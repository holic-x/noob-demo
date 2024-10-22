package com.noob.algorithm.common150.q027;

/**
 * 27 删除
 */
public class Solution3 {

    // 覆盖法：cur 指向当前可覆盖的位置
    public int removeDuplicates(int[] nums) {
        /**
         * 数组本身有序
         */
        int cur = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[cur-1]!=nums[i]){
                nums[cur++] = nums[i];
            }
        }
        return cur;
    }
}
