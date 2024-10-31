package com.noob.algorithm.common150.q209;


/**
 * 209 长度最小的子数组
 */
public class Solution1 {

    /**
     * 暴力法
     */
    public int minSubArrayLen(int target, int[] nums) {

        // 定义满足条件的子数组的最小长度
        int min = Integer.MAX_VALUE;

        // 判断以每个元素为起点的子数组是否满足target，暴力遍历所有子数组
        for (int i = 0; i < nums.length; i++) {
            // 每次循环可以定义一个累加sum，不用每次都重复计算一遍
            int curSum = 0;
            for (int j = i ; j < nums.length; j++) {
                // 计算和
                curSum += nums[j];
                if (curSum >= target) {
                    min = Math.min(min, j - i+1); // 更新min
                }
            }
        }

        // 返回响应
        return min==Integer.MAX_VALUE?0:min ;
    }


    public static void main(String[] args) {
//        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int[] nums = new int[]{1,2,3,4,5};
        Solution1 solution1 = new Solution1();
        solution1.minSubArrayLen(7, nums);
    }
}
