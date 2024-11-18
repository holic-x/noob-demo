package com.noob.algorithm.dmsxl.twoPointers;

/**
 * 151 反转字符串中的单词
 */
public class Solution151 {

    public String reverseWords(String s) {
        // 1.取出元素首尾空格
        s = s.trim();
        // 2.切割字符串
        String[] strs = s.split("\\s+");
        // 3.交换字符串数组元素
        int left = 0,right = strs.length-1;
        while(left<right){
            // 交换元素
            String temp = strs[left];
            strs[left] = strs[right];
            strs[right] = temp;
            // 指针靠拢
            left++;
            right--;
        }
        // 4.将数组拼接
        return String.join(" ", strs);
    }
}
