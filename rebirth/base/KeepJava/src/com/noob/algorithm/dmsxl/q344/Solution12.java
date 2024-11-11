package com.noob.algorithm.dmsxl.q344;

/**
 * 344 反转字符串
 */
public class Solution12 {

    // 思路1：双指针（元素交换）原地修改且空间复杂度满足O（1）
    public void reverseString(char[] s) {
        // 分别从头尾遍历数组元素，依次交换
        for (int start = 0, end = s.length - 1; start < end; start++, end--) {
            // 交换两头的元素
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
        }
    }

}
