package com.noob.algorithm.dmsxl.q977;

/**
 * 977 有序数组的平方
 */
public class Solution1 {

    // 计算平方 + 直接排序
    public int[] sortedSquares(int[] nums) {
        // idx 指向当前覆盖位置
        int idx = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[idx]*nums[idx] > nums[i] * nums[i]){
                // 如果idx指向的平方和比较大，则覆盖
                int temp = nums[idx];
                nums[idx] = nums[i];
                nums[i] = temp;
                // idx 往前移动
                idx ++;
            }
        }
    }

}
