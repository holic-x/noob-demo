package com.noob.algorithm.dmsxl.leetcode.q131;

import java.util.List;

/**
 * 131 分割回文串
 */
public class Solution1 {

    // todo 思路错误，此处不是要找满足回文串的子集，而是要确定切割方案，确保切割后得到的字符串都满足回文串要求

    // 思路：暴力法（双层循环确定子串起点、终点 + 判断子串是否回文）
    public List<List<String>> partition(String s) {
        // 定义结果集
//        List<String>

        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                String subStr = s.substring(i,j);
                if(isHuiWen(subStr)){

                }
            }
        }
        return null; //
    }

    public boolean isHuiWen(String str) {
        // 双指针判断回文
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // 左右不匹配，非回文，返回false
            }
        }
        return true;
    }

}
