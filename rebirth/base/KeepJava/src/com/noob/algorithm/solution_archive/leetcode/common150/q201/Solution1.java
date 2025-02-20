package com.noob.algorithm.solution_archive.leetcode.common150.q201;

/**
 * 数字范围按位与（201）
 */
public class Solution1 {

    public int rangeBitwiseAnd(int left, int right) {
        while(left<right){
            right = right & (right-1);
        }
        return right;
    }

}
