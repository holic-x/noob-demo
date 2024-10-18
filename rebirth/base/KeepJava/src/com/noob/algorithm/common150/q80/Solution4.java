package com.noob.algorithm.common150.q80;

public class Solution4 {

    /**
     * 原地法：类似双指针概念
     */
    public int removeDuplicates(int[] nums) {

        int len = nums.length;

        // 如果nums长度小于等于2，直接返回
        if(len <= 2) return len;

        // 如果nums长度超出2，则需要遍历数组进行校验
        int cur = 2;
        for(int i=2;i<len;i++) {
            /**
             * 数组本身有序，因此此处引入快慢指针的思路进行覆盖
             * cur 慢指针：指向当前数组元素可覆盖的位置
             * i 快指针：指向当前遍历的元素，判断它是否要放到前面的位置（它始终和其前两个元素进行比较，如果不相同则进行置换）
             */
            if(nums[i]!=nums[cur-2]){
                // 对元素进行覆盖
                nums[cur++] = nums[i];
            }
        }
        return cur;
    }
}
