package com.noob.algorithm.dmsxl.leetcode.q344;

/**
 * 344 反转字符串
 */
public class Solution1 {

    // 思路1：双指针（元素交换）原地修改且空间复杂度满足O（1）
    public void reverseString(char[] s) {
        int left = 0,right = s.length-1;
        while(left<right){
            // 交换左右元素
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            // 交换完毕，双指针向中间靠拢继续交换下一组元素
            left++;
            right--;
        }
    }

}
