package com.noob.algorithm.dmsxl.leetcode.q941;

/**
 * 🟢 941 有效的山脉
 */
public class Solution3 {

    // 规律法校验：校验各种坡度
    public boolean validMountainArray(int[] arr) {
        int len = arr.length;
        if (len < 3) {
            return false; // 元素个数小于3无法构成山脉
        }

        


        // 满足条件
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Solution3 s = new Solution3();
        s.validMountainArray(nums);
    }
}
