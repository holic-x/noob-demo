package com.noob.algorithm.daily.plan01.day06;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 344 反转字符串
 */
public class Solution344_02 {

    // 思路：双指针思路（两两交换）（原地算法）
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left <= right) {
            // 两两交换左右指针元素，交换完成双指针向中间靠拢
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            // 指针处理
            left++;
            right--;
        }
    }

    // 思路：双指针思路（两两交换）（原地算法）
    public void reverseString1(char[] s) {
        for (int left = 0, right = s.length - 1; left <= right; left++, right--) {
            // 两两交换左右指针元素，交换完成双指针向中间靠拢
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
    }
}
