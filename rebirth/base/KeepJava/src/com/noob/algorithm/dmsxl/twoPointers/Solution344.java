package com.noob.algorithm.dmsxl.twoPointers;

/**
 * 344 反转字符串
 */
public class Solution344 {

    /**
     * 双指针：left、right分别指向头尾元素，依次相互置换，置换完成指针向中间靠拢
     */
    public void reverseString(char[] s) {
        // 定义双指针
        int left = 0, right = s.length - 1;
        while (left < right) {
            // 交换元素
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            // 指针靠拢
            left++;
            right--;
        }
    }

}
